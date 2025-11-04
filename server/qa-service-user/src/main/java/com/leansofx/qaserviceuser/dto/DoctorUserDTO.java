package com.leansofx.qaserviceuser.dto;

import lombok.Data;

import java.util.List;

@Data
public class DoctorUserDTO {
    private String id;
    private String username;
    private String name;
    private String title;
    private String department;
    private String avatar;
    private String experience;
    private List<String> specialties;
    private boolean isActive;
}