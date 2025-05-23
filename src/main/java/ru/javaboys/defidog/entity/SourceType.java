package ru.javaboys.defidog.entity;

import io.jmix.core.metamodel.datatype.EnumClass;
import org.springframework.lang.Nullable;


public enum SourceType implements EnumClass<String> {

    GITHUB("GITHUB"),
    ETHERSCAN("ETHERSCAN"),
    SOURCIFY("SOURCIFY"),
    BYTECODE("BYTECODE");

    private final String id;

    SourceType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static SourceType fromId(String id) {
        for (SourceType at : SourceType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}