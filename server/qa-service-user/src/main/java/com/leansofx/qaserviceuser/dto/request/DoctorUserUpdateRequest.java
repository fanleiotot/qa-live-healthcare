package com.leansofx.qaserviceuser.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

/**
 * 医生用户更新请求DTO
 */
public class DoctorUserUpdateRequest {

    @NotBlank(message = "姓名不能为空")
    @Size(max = 100, message = "姓名长度不能超过100个字符")
    private String name;

    @NotBlank(message = "职称不能为空")
    @Size(max = 50, message = "职称长度不能超过50个字符")
    private String title;

    @NotBlank(message = "科室不能为空")
    @Size(max = 100, message = "科室长度不能超过100个字符")
    private String department;

    @Size(max = 500, message = "头像URL长度不能超过500个字符")
    private String avatar;

    @Size(max = 200, message = "经验描述长度不能超过200个字符")
    private String experience;

    private List<String> specialties;

    @NotNull(message = "激活状态不能为空")
    private Boolean isActive;

    // Constructors
    public DoctorUserUpdateRequest() {
    }

    public DoctorUserUpdateRequest(String name, String title, String department, String avatar, 
                                 String experience, List<String> specialties, Boolean isActive) {
        this.name = name;
        this.title = title;
        this.department = department;
        this.avatar = avatar;
        this.experience = experience;
        this.specialties = specialties;
        this.isActive = isActive;
    }

    // Getters and Setters
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

    @Override
    public String toString() {
        return "DoctorUserUpdateRequest{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", department='" + department + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}