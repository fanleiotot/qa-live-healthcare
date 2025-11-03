package com.leansofx.qaserviceuser.dto.converter;

import com.leansofx.qaserviceuser.dto.request.DoctorUserCreateRequest;
import com.leansofx.qaserviceuser.dto.request.DoctorUserUpdateRequest;
import com.leansofx.qaserviceuser.dto.response.DoctorUserLoginResponse;
import com.leansofx.qaserviceuser.dto.response.DoctorUserResponse;
import com.leansofx.qaserviceuser.entity.DoctorUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DoctorUser实体与DTO之间的转换器
 */
public class DoctorUserConverter {

    /**
     * 将实体转换为响应DTO
     */
    public static DoctorUserResponse toResponse(DoctorUser entity) {
        if (entity == null) {
            return null;
        }

        DoctorUserResponse response = new DoctorUserResponse();
        response.setId(entity.getId());
        response.setUsername(entity.getUsername());
        response.setName(entity.getName());
        response.setTitle(entity.getTitle());
        response.setDepartment(entity.getDepartment());
        response.setAvatar(entity.getAvatar());
        response.setExperience(entity.getExperience());
        response.setSpecialties(entity.getSpecialties());
        response.setIsActive(entity.getIsActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());

        return response;
    }

    /**
     * 将实体列表转换为响应DTO列表
     */
    public static List<DoctorUserResponse> toResponseList(List<DoctorUser> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(DoctorUserConverter::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * 将实体转换为登录响应DTO
     */
    public static DoctorUserLoginResponse toLoginResponse(DoctorUser entity, String token) {
        if (entity == null) {
            return null;
        }

        DoctorUserLoginResponse response = new DoctorUserLoginResponse();
        response.setToken(token);
        response.setId(entity.getId());
        response.setUsername(entity.getUsername());
        response.setName(entity.getName());
        response.setTitle(entity.getTitle());
        response.setDepartment(entity.getDepartment());
        response.setAvatar(entity.getAvatar());
        response.setExperience(entity.getExperience());
        response.setSpecialties(entity.getSpecialties());
        response.setIsActive(entity.getIsActive());
        response.setLoginTime(LocalDateTime.now());

        return response;
    }

    /**
     * 将创建请求DTO转换为实体
     */
    public static DoctorUser toEntity(DoctorUserCreateRequest request) {
        if (request == null) {
            return null;
        }

        DoctorUser entity = new DoctorUser();
        entity.setId(request.getId());
        entity.setUsername(request.getUsername());
        entity.setPassword(request.getPassword());
        entity.setName(request.getName());
        entity.setTitle(request.getTitle());
        entity.setDepartment(request.getDepartment());
        entity.setAvatar(request.getAvatar());
        entity.setExperience(request.getExperience());
        entity.setSpecialties(request.getSpecialties());
        entity.setIsActive(request.getIsActive());

        return entity;
    }

    /**
     * 使用更新请求DTO更新实体
     */
    public static void updateEntityFromRequest(DoctorUser entity, DoctorUserUpdateRequest request) {
        if (entity == null || request == null) {
            return;
        }

        entity.setName(request.getName());
        entity.setTitle(request.getTitle());
        entity.setDepartment(request.getDepartment());
        entity.setAvatar(request.getAvatar());
        entity.setExperience(request.getExperience());
        entity.setSpecialties(request.getSpecialties());
        entity.setIsActive(request.getIsActive());
    }

    /**
     * 私有构造函数，防止实例化
     */
    private DoctorUserConverter() {
        throw new IllegalStateException("Utility class");
    }
}