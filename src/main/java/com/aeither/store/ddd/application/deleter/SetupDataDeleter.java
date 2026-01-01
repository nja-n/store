package com.aeither.store.ddd.application.deleter;

public interface SetupDataDeleter {
    String getDeleteKey(); // Matches the URL path variable, e.g., "category"

    void delete(Long id);
}
