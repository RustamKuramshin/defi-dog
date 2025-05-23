package ru.javaboys.defidog.asyncjobs.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.stereotype.Service;
import ru.javaboys.defidog.asyncjobs.dto.AbiCandidate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class GitRepositoryService {

    /**
     * Получает текущее содержимое исходников из HEAD git-репозитория.
     * Склеивает все .sol/.vy/.js/.ts/.json — в зависимости от целей.
     */
    public String getFullSourceCode(File repositoryDir) throws IOException {
        File gitDir = new File(repositoryDir, ".git");
        if (!gitDir.exists()) {
            throw new IllegalArgumentException("Не найден git-репозиторий в директории: " + repositoryDir);
        }

        // Собираем все исходники (например .sol) кроме .git и node_modules
        try (Stream<Path> files = Files.walk(repositoryDir.toPath())) {
            return files
                    .filter(path -> !path.toFile().isDirectory())
                    .filter(path -> !path.toString().contains(File.separator + ".git" + File.separator))
                    .filter(path -> !path.toString().contains(File.separator + "node_modules" + File.separator))
                    .filter(path -> path.toString().endsWith(".sol") || path.toString().endsWith(".vy")) // расширяем по мере нужды
                    .sorted()
                    .map(path -> {
                        try {
                            String relative = repositoryDir.toPath().relativize(path).toString();
                            String content = Files.readString(path);
                            return "// ===== " + relative + " =====\n" + content + "\n";
                        } catch (IOException e) {
                            log.warn("Ошибка чтения файла {}", path, e);
                            return "";
                        }
                    })
                    .collect(Collectors.joining("\n"));
        }
    }

    public Repository openRepository(File repositoryDir) throws IOException {
        return new FileRepositoryBuilder()
                .setGitDir(new File(repositoryDir, ".git"))
                .readEnvironment()
                .findGitDir()
                .build();
    }


    public List<AbiCandidate> extractAbiInfo(File repositoryDir) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<AbiCandidate> result = new ArrayList<>();

        try (Stream<Path> files = Files.walk(repositoryDir.toPath())) {
            files
                    .filter(path -> !path.toFile().isDirectory())
                    .filter(path -> !path.toString().contains(File.separator + ".git" + File.separator))
                    .filter(path -> !path.toString().contains(File.separator + "node_modules" + File.separator))
                    .filter(path -> path.toString().toLowerCase().endsWith(".json"))
                    .forEach(path -> {
                        try {
                            JsonNode json = objectMapper.readTree(path.toFile());

                            if (json.isArray() && isAbiArray(json)) {
                                result.add(new AbiCandidate(json.toPrettyString(), repositoryDir.toPath().relativize(path).toString()));
                            }

                            if (json.has("abi") && json.get("abi").isArray() && isAbiArray(json.get("abi"))) {
                                result.add(new AbiCandidate(json.get("abi").toPrettyString(), repositoryDir.toPath().relativize(path).toString()));
                            }

                        } catch (Exception e) {
                            log.warn("Ошибка разбора JSON {}: {}", path, e.getMessage());
                        }
                    });
        }

        return result;
    }

    private boolean isAbiArray(JsonNode array) {
        for (JsonNode item : array) {
            if (item.has("type") && (item.has("name") || item.has("inputs"))) {
                return true;
            }
        }
        return false;
    }

    public Git openGit(File repositoryDir) throws IOException {
        return new Git(openRepository(repositoryDir));
    }
}
