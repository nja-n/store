package com.aeither.store.common.application;

import java.util.Map;

public interface SetupDataSaver {
    String getSaveKey();

    void save(Map<String, String> requestData);
}
