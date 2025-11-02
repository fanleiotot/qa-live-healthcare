package com.leansoftx.qaserviceuser.service;

import com.leansoftx.qaserviceuser.model.Doctor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorUserService {

    private List<Doctor> loadDoctorsFromJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(
                    new ClassPathResource("data/doctor-user-list.json").getFile(),
                    new TypeReference<List<Doctor>>() {}
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to load doctors data", e);
        }
    }

    public List<Doctor> getAllDoctors() {
        return loadDoctorsFromJson();
    }

    public Doctor getDoctorById(String id) {
        return loadDoctorsFromJson().stream()
                .filter(doctor -> doctor.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public List<Doctor> getActiveDoctors() {
        return loadDoctorsFromJson().stream()
                .filter(Doctor::isActive)
                .collect(Collectors.toList());
    }

    public Doctor addDoctor(Doctor doctor) {
        List<Doctor> doctors = loadDoctorsFromJson();
        doctors.add(doctor);
        return doctor;
    }

    public Doctor updateDoctor(String id, Doctor updatedDoctor) {
        List<Doctor> doctors = loadDoctorsFromJson();
        Optional<Doctor> existingDoctor = doctors.stream()
                .filter(doctor -> doctor.getId().equals(id))
                .findFirst();

        if (existingDoctor.isPresent()) {
            Doctor doctor = existingDoctor.get();
            doctor.setUsername(updatedDoctor.getUsername());
            doctor.setName(updatedDoctor.getName());
            doctor.setTitle(updatedDoctor.getTitle());
            doctor.setDepartment(updatedDoctor.getDepartment());
            doctor.setAvatar(updatedDoctor.getAvatar());
            doctor.setExperience(updatedDoctor.getExperience());
            doctor.setSpecialties(updatedDoctor.getSpecialties());
            doctor.setActive(updatedDoctor.isActive());
            return doctor;
        } else {
            throw new RuntimeException("Doctor not found");
        }
    }

    public void deleteDoctor(String id) {
        List<Doctor> doctors = loadDoctorsFromJson();
        doctors.removeIf(doctor -> doctor.getId().equals(id));
    }
}