package com.leansofx.qaserviceuser.service.impl;

import com.leansofx.qaserviceuser.dto.DoctorUserDTO;
import com.leansofx.qaserviceuser.entity.DoctorUser;
import com.leansofx.qaserviceuser.repository.DoctorUserRepository;
import com.leansofx.qaserviceuser.service.DoctorUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorUserServiceImpl implements DoctorUserService {
    private final DoctorUserRepository doctorUserRepository;

    @Override
    public List<DoctorUserDTO> getAllDoctors() {
        return doctorUserRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorUserDTO getDoctorById(String id) {
        return doctorUserRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    private DoctorUserDTO convertToDTO(DoctorUser doctorUser) {
        DoctorUserDTO dto = new DoctorUserDTO();
        dto.setId(doctorUser.getId());
        dto.setUsername(doctorUser.getUsername());
        dto.setName(doctorUser.getName());
        dto.setTitle(doctorUser.getTitle());
        dto.setDepartment(doctorUser.getDepartment());
        dto.setAvatar(doctorUser.getAvatar());
        dto.setExperience(doctorUser.getExperience());
        dto.setSpecialties(doctorUser.getSpecialties());
        dto.setActive(doctorUser.isActive());
        return dto;
    }
}