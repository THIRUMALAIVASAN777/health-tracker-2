package com.healthtracker.backend.service;

import com.healthtracker.backend.dto.MacroResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class MacroService {

    public MacroResponseDTO calculateMacros(
            int age,
            double weight,
            double height,
            String gender,
            double activityLevel) {

        double bmr;

        // BMR calculation
        if (gender.equalsIgnoreCase("male")) {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) + 5;
        } else {
            bmr = (10 * weight) + (6.25 * height) - (5 * age) - 161;
        }

        // Daily calorie requirement
        double calories = bmr * activityLevel;

        // Macro calculation
        double protein = weight * 1.6;
        double fat = weight * 0.8;

        double proteinCalories = protein * 4;
        double fatCalories = fat * 9;

        double carbsCalories =
                calories - proteinCalories - fatCalories;

        double carbs = carbsCalories / 4;

        return new MacroResponseDTO(
                round(calories),
                round(protein),
                round(carbs),
                round(fat)
        );
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
