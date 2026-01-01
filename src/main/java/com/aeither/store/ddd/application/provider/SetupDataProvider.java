package com.aeither.store.ddd.application.provider;

import java.util.List;

public interface SetupDataProvider {
    String getKey(); // The key used in the model for the list of items

    Object getInitialState(); // The empty object for the form

    String getInitialStateKey(); // The key for the empty object form

    List<?> getList(); // The list of existing items
}
