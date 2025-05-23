package ru.javaboys.defidog.integrations.blockchain;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetCode;

import java.math.BigInteger;

// https://docs.web3j.io/
// https://docs.alchemy.com/reference/chain-apis-overview
@Service
@RequiredArgsConstructor
@Slf4j
public class BlockchainServiceImpl implements BlockchainService {

    private final Web3j web3j;

    @Override
    @SneakyThrows
    public BigInteger getLastBlockNumber() {
        EthBlockNumber latestBlock = web3j.ethBlockNumber().send();
        return latestBlock.getBlockNumber();
    }

    @Override
    @SneakyThrows
    public String getBytecode(String address) {
        EthGetCode code = web3j.ethGetCode(address, DefaultBlockParameterName.LATEST).send();
        return code.getCode();
    }
}
