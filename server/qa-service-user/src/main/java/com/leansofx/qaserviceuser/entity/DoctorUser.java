package com.leansofx.qaserviceuser.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

/**
 * 医生用户实体类
 * 对应数据库中的医生用户表
 */
@Entity
@Table(name = "doctor_users")
public class DoctorUser {

    @Id
    @Column(name = "id", length = 50, nullable = false)
    private String id;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 50, message = "用户名长度必须在3-50个字符之间")
    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 100, message = "密码长度必须在6-100个字符之间")
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @NotBlank(message = "姓名不能为空")
    @Size(max = 100, message = "姓名长度不能超过100个字符")
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotBlank(message = "职称不能为空")
    @Size(max = 50, message = "职称长度不能超过50个字符")
    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @NotBlank(message = "科室不能为空")
    @Size(max = 100, message = "科室长度不能超过100个字符")
    @Column(name = "department", length = 100, nullable = false)
    private String department;

    @Size(max = 500, message = "头像URL长度不能超过500个字符")
    @Column(name = "avatar", length = 500)
    private String avatar;

    @Size(max = 200, message = "经验描述长度不能超过200个字符")
    @Column(name = "experience", length = 200)
    private String experience;

    @ElementCollection
    @CollectionTable(name = "doctor_specialties", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "specialty", length = 100)
    private List<String> specialties = new ArrayList<>();

    @NotNull(message = "激活状态不能为空")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * 构造函数
     */
    public DoctorUser() {
    }

    /**
     * 在持久化之前执行的回调方法
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    /**
     * 在更新之前执行的回调方法
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and Setters

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Utility methods

    @Override
    public String toString() {
        return "DoctorUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", department='" + department + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}