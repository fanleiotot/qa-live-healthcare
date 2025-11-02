package com.leansoftx.qaserviceuser.service;

import com.leansoftx.qaserviceuser.model.DoctorUser;
import com.leansoftx.qaserviceuser.repository.DoctorUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorUserService {

    private final DoctorUserRepository doctorUserRepository;

    public DoctorUserService(DoctorUserRepository doctorUserRepository) {
        this.doctorUserRepository = doctorUserRepository;
    }

    public List<DoctorUser> getAllDoctors() {
        return doctorUserRepository.findAll();
    }

    public Optional<DoctorUser> getDoctorById(String id) {
        return doctorUserRepository.findById(id);
    }

    public DoctorUser saveDoctor(DoctorUser doctorUser) {
        return doctorUserRepository.save(doctorUser);
    }

    public DoctorUser updateDoctor(String id, DoctorUser doctorUser) {
        doctorUser.setId(id);
        return doctorUserRepository.save(doctorUser);
    }

    public void deleteDoctor(String id) {
        doctorUserRepository.deleteById(id);
    }
}