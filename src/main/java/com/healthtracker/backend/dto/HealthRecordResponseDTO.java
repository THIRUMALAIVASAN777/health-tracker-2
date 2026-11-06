package com.healthtracker.backend.dto;

public class HealthRecordResponseDTO {

    private Long id;
    private Integer steps;
    private Double weight;
    private Integer calories;
    private Double water;
    private Double sleepHours;
    private Integer heartRate;
    private Long userId;
    private String userName;

    public HealthRecordResponseDTO(
            Long id,
            Integer steps,
            Double weight,
            Integer calories,
            Double water,
            Double sleepHours,
            Integer heartRate,
            Long userId,
            String userName) {

        this.id = id;
        this.steps = steps;
        this.weight = weight;
        this.calories = calories;
        this.water = water;
        this.sleepHours = sleepHours;
        this.heartRate = heartRate;
        this.userId = userId;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public Integer getSteps() {
        return steps;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getCalories() {
        return calories;
    }

    public Double getWater() {
        return water;
    }

    public Double getSleepHours() {
        return sleepHours;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
