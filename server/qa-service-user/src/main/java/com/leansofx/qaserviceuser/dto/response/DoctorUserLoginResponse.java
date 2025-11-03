package com.leansofx.qaserviceuser.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 医生用户登录响应DTO
 */
public class DoctorUserLoginResponse {

    private String token;
    private String id;
    private String username;
    private String name;
    private String title;
    private String department;
    private String avatar;
    private String experience;
    private List<String> specialties;
    private Boolean isActive;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginTime;

    // Constructors
    public DoctorUserLoginResponse() {
    }

    public DoctorUserLoginResponse(String token, String id, String username, String name, String title,
                                 String department, String avatar, String experience, List<String> specialties,
                                 Boolean isActive, LocalDateTime loginTime) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.name = name;
        this.title = title;
        this.department = department;
        this.avatar = avatar;
        this.experience = experience;
        this.specialties = specialties;
        this.isActive = isActive;
        this.loginTime = loginTime;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public List<String> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<String> specialties) {
        this.specialties = specialties;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "DoctorUserLoginResponse{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", department='" + department + '\'' +
                ", isActive=" + isActive +
                ", loginTime=" + loginTime +
                '}';
    }
}