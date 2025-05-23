package ru.javaboys.defidog.asyncjobs.updater;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.javaboys.defidog.asyncjobs.service.LocalRepositoryIntegratorService;
import ru.javaboys.defidog.asyncjobs.util.CommonUtils;
import ru.javaboys.defidog.asyncjobs.util.SourceStorageService;
import ru.javaboys.defidog.entity.BlockchainNetwork;
import ru.javaboys.defidog.entity.SmartContract;
import ru.javaboys.defidog.entity.SourceCode;
import ru.javaboys.defidog.entity.SourceType;
import ru.javaboys.defidog.integrations.sourcify.SourcifyService;
import ru.javaboys.defidog.integrations.sourcify.dto.GetContract200Response;
import ru.javaboys.defidog.integrations.sourcify.dto.GetContract200ResponseAllOfSourcesValue;

@Slf4j
@Component
@RequiredArgsConstructor
public class SourcifySourceCodeUpdater implements SourceCodeUpdater, TypedUpdater {

    private final SourcifyService sourcifyService;
    private final SourceStorageService storageService;
    private final LocalRepositoryIntegratorService integratorService;
    private final ObjectMapper objectMapper; // для сериализации ABI

    @Override
    public SourceType getSupportedSourceType() {
        return SourceType.SOURCIFY;
    }

    @Override
    public String update(SourceCode sourceCode) throws Exception {
        StringBuilder jobLog = new StringBuilder();
        Path repoPath = storageService.getSourceDirectory(sourceCode);
        File repoDir = repoPath.toFile();

        jobLog.append("Источник: SOURCIFY\n");

        List<SmartContract> contracts = sourceCode.getSmartContracts();
        if (contracts == null || contracts.isEmpty()) {
            throw new IllegalStateException("Нет связанных SmartContract-ов для SourceCode ID=" + sourceCode.getId());
        }

        if (!repoDir.exists()) {
            jobLog.append("Создание нового git-репозитория...\n");
            repoDir.mkdirs();
            Git.init().setDirectory(repoDir).call();
        }

        for (SmartContract contract : contracts) {
            BlockchainNetwork network = contract.getNetwork();
            String address = contract.getAddress();
            String chainId = CommonUtils.getChainIdByNetworkName(network);

            jobLog.append("Контракт ").append(address).append("\n");

            GetContract200Response response;
            try {
                response = sourcifyService.getContract(chainId, address, "all", null);
            } catch (Exception e) {
                jobLog.append("⚠️ Не удалось получить контракт: ").append(e.getMessage()).append("\n");
                continue;
            }

            // --- Исходники
            Map<String, GetContract200ResponseAllOfSourcesValue> sources = response.getSources();
            if (sources == null || sources.isEmpty()) {
                jobLog.append("⚠️ Исходный код отсутствует для адреса ").append(address).append("\n");
                continue;
            }

            for (Map.Entry<String, GetContract200ResponseAllOfSourcesValue> entry : sources.entrySet()) {
                String filename = entry.getKey();
                String content = entry.getValue().getContent();
                if (content != null && !content.isBlank()) {
                    File file = new File(repoDir, filename);
                    FileUtils.writeStringToFile(file, content, StandardCharsets.UTF_8);
                }
            }

            // --- ABI
            List<Object> abiList = response.getAbi();
            if (abiList != null && !abiList.isEmpty()) {
                File abiFile = new File(repoDir, "Contract-" + address + ".abi.json");
                String abiJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(abiList);
                FileUtils.writeStringToFile(abiFile, abiJson, StandardCharsets.UTF_8);
            }

            jobLog.append("Файлы для ").append(address).append(" записаны.\n");
        }

        // Git commit
        Git git = Git.open(repoDir);
        git.add().addFilepattern(".").call();
        git.commit().setMessage("Fetched from Sourcify").call();

        String integrationLog = integratorService.integrateAndProcess(sourceCode, repoPath);
        jobLog.append(integrationLog);

        return jobLog.toString();
    }
}


