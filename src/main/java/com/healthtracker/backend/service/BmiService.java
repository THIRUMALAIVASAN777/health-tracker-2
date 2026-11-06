package com.healthtracker.backend.service;

import com.healthtracker.backend.dto.BmiResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class BmiService {

    public BmiResponseDTO calculateBmi(double weight, double height) {

        double bmi = weight / (height * height);

        String category;

        if (bmi < 18.5) {
            category = "Underweight";
        } else if (bmi < 25) {
            category = "Normal";
        } else if (bmi < 30) {
            category = "Overweight";
        } else {
            category = "Obese";
        }

        return new BmiResponseDTO(
                Math.round(bmi * 100.0) / 100.0,
                category
        );
    }
}