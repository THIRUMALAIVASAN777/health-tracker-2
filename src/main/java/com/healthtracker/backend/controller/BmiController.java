package com.healthtracker.backend.controller;

import com.healthtracker.backend.dto.BmiResponseDTO;
import com.healthtracker.backend.service.BmiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bmi")
public class BmiController {

    private final BmiService bmiService;

    public BmiController(BmiService bmiService) {
        this.bmiService = bmiService;
    }

    @GetMapping
    public BmiResponseDTO calculateBmi(
            @RequestParam double weight,
            @RequestParam double height) {

        return bmiService.calculateBmi(weight, height);
    }
}
