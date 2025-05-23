package ru.javaboys.defidog.repositories;

import io.jmix.core.DataManager;
import io.jmix.core.FetchPlans;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.javaboys.defidog.entity.Cryptocurrency;
import ru.javaboys.defidog.entity.SmartContract;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CryptocurrencyRepository {

    private final DataManager dataManager;
    private final FetchPlans fetchPlans;

    public List<SmartContract> findContracts(UUID cryptoId) {
        Cryptocurrency crypto = dataManager.load(Cryptocurrency.class)
                .id(cryptoId)
                .fetchPlan(fetchPlans.builder(Cryptocurrency.class).add("contracts").build())
                .optional()
                .orElse(null);

        return crypto != null ? crypto.getContracts() : Collections.emptyList();
    }

    public Optional<String> findNameById(UUID protocolId) {
        return dataManager.loadValue(
                        "select c.name from Cryptocurrency c where c.id = :protocolId", String.class)
                .parameter("protocolId", protocolId)
                .optional();
    }

    public Cryptocurrency findById(UUID protocolId) {
        return dataManager.load(Cryptocurrency.class).id(protocolId).optional().orElse(null);
    }

    public Optional<String> findDescriptionById(UUID protocolId) {
        return dataManager.loadValue(
                        "select c.description from Cryptocurrency c where c.id = :protocolId", String.class)
                .parameter("protocolId", protocolId)
                .optional();
    }
}
