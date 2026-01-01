package com.aeither.store.administration.web;

import com.aeither.store.administration.application.AdministrationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/admin/setup")
@RequiredArgsConstructor
public class AdministrationController {

    private final AdministrationFacade administrationFacade;

    @GetMapping("")
    public String showSetupPage(Model model) {
        administrationFacade.populateModel(model);
        return "admin/setup";
    }

    @PostMapping("/{type}")
    public String saveEntity(@PathVariable String type, @RequestParam Map<String, String> allParams) {
        administrationFacade.handleSave(type, allParams);
        return "redirect:/admin/setup";
    }

    @PostMapping("/{type}/delete/{id}")
    public String deleteEntity(@PathVariable String type, @PathVariable Long id) {
        administrationFacade.handleDelete(type, id);
        return "redirect:/admin/setup";
    }

}
