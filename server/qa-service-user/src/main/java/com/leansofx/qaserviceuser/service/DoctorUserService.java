package com.leansofx.qaserviceuser.service;

import com.leansofx.qaserviceuser.dto.DoctorUserDTO;
import com.leansofx.qaserviceuser.entity.DoctorUser;

import java.util.List;

public interface DoctorUserService {
    List<DoctorUserDTO> getAllDoctors();
    DoctorUserDTO getDoctorById(String id);
}