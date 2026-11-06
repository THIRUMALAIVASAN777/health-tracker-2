package com.healthtracker.backend.service;

import com.healthtracker.backend.dto.HealthRecordResponseDTO;
import com.healthtracker.backend.entity.HealthRecord;
import com.healthtracker.backend.entity.User;
import com.healthtracker.backend.repository.HealthRecordRepository;
import com.healthtracker.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthRecordService {

    private final HealthRecordRepository healthRecordRepository;
    private final UserRepository userRepository;

    public HealthRecordService(
            HealthRecordRepository healthRecordRepository,
            UserRepository userRepository) {

        this.healthRecordRepository = healthRecordRepository;
        this.userRepository = userRepository;
    }

    public HealthRecord createHealthRecord(
            HealthRecord healthRecord,
            String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        healthRecord.setUser(user);

        return healthRecordRepository.save(healthRecord);
    }

    public List<HealthRecord> getAllHealthRecords() {
        return healthRecordRepository.findAll();
    }

    public List<HealthRecordResponseDTO> getHealthRecordsByEmail(String email) {

        List<HealthRecord> records =
                healthRecordRepository.findByUserEmail(email);

        return records.stream()
                .map(record -> new HealthRecordResponseDTO(
                        record.getId(),
                        record.getSteps(),
                        record.getWeight(),
                        record.getCalories(),
                        record.getWater(),
                        record.getSleepHours(),
                        record.getHeartRate(),
                        record.getUser().getId(),
                        record.getUser().getName()
                ))
                .toList();
    }

    public HealthRecord updateHealthRecord(Long id, HealthRecord updatedRecord) {

        HealthRecord existingRecord = healthRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Health record not found"));

        existingRecord.setSteps(updatedRecord.getSteps());
        existingRecord.setWeight(updatedRecord.getWeight());
        existingRecord.setCalories(updatedRecord.getCalories());
        existingRecord.setWater(updatedRecord.getWater());
        existingRecord.setSleepHours(updatedRecord.getSleepHours());
        existingRecord.setHeartRate(updatedRecord.getHeartRate());

        return healthRecordRepository.save(existingRecord);
    }

    public void deleteHealthRecord(Long id) {

        HealthRecord existingRecord = healthRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Health record not found"));

        healthRecordRepository.delete(existingRecord);
    }
}

