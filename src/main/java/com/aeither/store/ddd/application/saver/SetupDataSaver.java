package com.aeither.store.ddd.application.saver;

import java.util.Map;

public interface SetupDataSaver {
    String getSaveKey(); // Matches the URL path variable, e.g., "category"

    void save(Map<String, String> requestData);
}
