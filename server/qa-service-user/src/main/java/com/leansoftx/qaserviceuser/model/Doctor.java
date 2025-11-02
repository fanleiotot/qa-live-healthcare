package com.leansoftx.qaserviceuser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Doctor {
    private String id;
    private String username;
    private String password;
    private String name;
    private String title;
    private String department;
    private String avatar;
    private String experience;
    private List<String> specialties;
    @JsonProperty("isActive")
    private boolean isActive;
}