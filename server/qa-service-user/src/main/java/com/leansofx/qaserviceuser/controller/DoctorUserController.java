package com.leansofx.qaserviceuser.controller;

import com.leansofx.qaserviceuser.dto.DoctorUserDTO;
import com.leansofx.qaserviceuser.service.DoctorUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorUserController {
    private final DoctorUserService doctorUserService;

    @GetMapping
    public List<DoctorUserDTO> getAllDoctors() {
        return doctorUserService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public DoctorUserDTO getDoctorById(@PathVariable String id) {
        return doctorUserService.getDoctorById(id);
    }
}