package com.healthtracker.backend.dto;

public class BmiResponseDTO {

    private double bmi;
    private String category;

    public BmiResponseDTO(double bmi, String category) {
        this.bmi = bmi;
        this.category = category;
    }

    public double getBmi() {
        return bmi;
    }

    public String getCategory() {
        return category;
    }
}