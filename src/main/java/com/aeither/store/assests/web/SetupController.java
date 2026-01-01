package com.aeither.store.assests.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aeither.store.assests.application.SetupFacade;

import java.util.Map;

@Controller
@RequestMapping("/setup")
@RequiredArgsConstructor
public class SetupController {

    private final SetupFacade setupFacade;

    @GetMapping("")
    public String showSetupPage(Model model) {
        setupFacade.populateModel(model);
        return "setup/setup";
    }

    @PostMapping("/{type}")
    public String saveEntity(@PathVariable String type, @RequestParam Map<String, String> allParams) {
        setupFacade.handleSave(type, allParams);
        return "redirect:/setup";
    }

    @PostMapping("/{type}/delete/{id}")
    public String deleteEntity(@PathVariable String type, @PathVariable Long id) {
        setupFacade.handleDelete(type, id);
        return "redirect:/setup";
    }

}
