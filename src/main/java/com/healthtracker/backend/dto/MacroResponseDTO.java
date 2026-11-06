package com.healthtracker.backend.dto;

public class MacroResponseDTO {

    private double calories;
    private double protein;
    private double carbs;
    private double fat;

    public MacroResponseDTO(
            double calories,
            double protein,
            double carbs,
            double fat) {

        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFat() {
        return fat;
    }
}
