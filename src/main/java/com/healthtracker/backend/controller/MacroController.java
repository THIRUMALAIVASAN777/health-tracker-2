package com.healthtracker.backend.controller;

import com.healthtracker.backend.dto.MacroResponseDTO;
import com.healthtracker.backend.service.MacroService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/macros")
@CrossOrigin(origins = "*")
public class MacroController {

    private final MacroService macroService;

    public MacroController(MacroService macroService) {
        this.macroService = macroService;
    }

    @GetMapping
    public MacroResponseDTO calculateMacros(
            @RequestParam int age,
            @RequestParam double weight,
            @RequestParam double height,
            @RequestParam String gender,
            @RequestParam double activityLevel) {

        return macroService.calculateMacros(
                age,
                weight,
                height,
                gender,
                activityLevel
        );
    }
}