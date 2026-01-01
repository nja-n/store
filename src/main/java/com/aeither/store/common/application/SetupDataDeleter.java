package com.aeither.store.common.application;

public interface SetupDataDeleter {
    String getDeleteKey();

    void delete(Long id);
}
