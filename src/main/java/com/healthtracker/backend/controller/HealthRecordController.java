package com.healthtracker.backend.controller;

import com.healthtracker.backend.entity.HealthRecord;
import com.healthtracker.backend.service.HealthRecordService;
import org.springframework.web.bind.annotation.*;
import com.healthtracker.backend.dto.HealthRecordResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/health")
public class HealthRecordController {

    private final HealthRecordService healthRecordService;

    public HealthRecordController(HealthRecordService healthRecordService) {
        this.healthRecordService = healthRecordService;
    }

    @PostMapping
    public HealthRecord createHealthRecord(
            @RequestBody  @Valid HealthRecord healthRecord,
            @RequestParam String email) {

        return healthRecordService.createHealthRecord(
                healthRecord,
                email
        );
    }

    @GetMapping
    public List<HealthRecord> getAllHealthRecords() {
        return healthRecordService.getAllHealthRecords();
    }

    @GetMapping("/user")
    public List<HealthRecordResponseDTO> getHealthRecordsByEmail(
            @RequestParam String email) {

        return healthRecordService.getHealthRecordsByEmail(email);
    }

    @PutMapping("/{id}")
    public HealthRecord updateHealthRecord(
            @PathVariable Long id,
            @RequestBody HealthRecord healthRecord) {

        return healthRecordService.updateHealthRecord(id, healthRecord);
    }

    @DeleteMapping("/{id}")
    public String deleteHealthRecord(@PathVariable Long id) {

        healthRecordService.deleteHealthRecord(id);

        return "Health record deleted successfully";
    }
}
