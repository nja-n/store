package com.aeither.store.assests.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.aeither.store.common.application.SetupDataDeleter;
import com.aeither.store.common.application.SetupDataProvider;
import com.aeither.store.common.application.SetupDataSaver;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SetupFacade {

    private final List<SetupDataProvider> providers;
    private final List<SetupDataSaver> savers;
    private final List<SetupDataDeleter> deleters;

    public void populateModel(Model model) {
        for (SetupDataProvider provider : providers) {
            model.addAttribute(provider.getInitialStateKey(), provider.getInitialState());
            model.addAttribute(provider.getKey(), provider.getList());
        }
    }

    public void handleSave(String type, Map<String, String> data) {
        for (SetupDataSaver saver : savers) {
            if (saver.getSaveKey().equals(type)) {
                saver.save(data);
                return;
            }
        }
        throw new IllegalArgumentException("No saver found for type: " + type);
    }

    public void handleDelete(String type, Long id) {
        for (SetupDataDeleter deleter : deleters) {
            if (deleter.getDeleteKey().equals(type)) {
                deleter.delete(id);
                return;
            }
        }
        throw new IllegalArgumentException("No deleter found for type: " + type);
    }
}
