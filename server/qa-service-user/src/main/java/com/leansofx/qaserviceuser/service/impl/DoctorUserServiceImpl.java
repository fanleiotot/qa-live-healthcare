package com.leansofx.qaserviceuser.service.impl;

import com.leansofx.qaserviceuser.dto.converter.DoctorUserConverter;
import com.leansofx.qaserviceuser.dto.request.DoctorUserCreateRequest;
import com.leansofx.qaserviceuser.dto.request.DoctorUserLoginRequest;
import com.leansofx.qaserviceuser.dto.request.DoctorUserUpdateRequest;
import com.leansofx.qaserviceuser.dto.response.DoctorUserLoginResponse;
import com.leansofx.qaserviceuser.dto.response.DoctorUserResponse;
import com.leansofx.qaserviceuser.entity.DoctorUser;
import com.leansofx.qaserviceuser.repository.DoctorUserRepository;
import com.leansofx.qaserviceuser.service.DoctorUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 医生用户服务实现类
 */
@Service
@Transactional
public class DoctorUserServiceImpl implements DoctorUserService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorUserServiceImpl.class);

    private final DoctorUserRepository doctorUserRepository;

    public DoctorUserServiceImpl(DoctorUserRepository doctorUserRepository) {
        this.doctorUserRepository = doctorUserRepository;
    }

    @Override
    public DoctorUserResponse createDoctorUser(DoctorUserCreateRequest request) {
        logger.info("创建医生用户: {}", request.getUsername());

        // 检查用户名是否已存在
        if (existsByUsername(request.getUsername())) {
            logger.warn("用户名已存在: {}", request.getUsername());
            throw new IllegalArgumentException("用户名已存在: " + request.getUsername());
        }

        // 如果ID为空，生成UUID
        if (request.getId() == null || request.getId().trim().isEmpty()) {
            request.setId(generateDoctorId());
        }

        DoctorUser entity = DoctorUserConverter.toEntity(request);
        DoctorUser savedEntity = doctorUserRepository.save(entity);
        
        logger.info("医生用户创建成功: {}", savedEntity.getId());
        return DoctorUserConverter.toResponse(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DoctorUserResponse> getDoctorUserById(String id) {
        logger.debug("根据ID查找医生用户: {}", id);
        return doctorUserRepository.findById(id)
                .map(DoctorUserConverter::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DoctorUserResponse> getDoctorUserByUsername(String username) {
        logger.debug("根据用户名查找医生用户: {}", username);
        return doctorUserRepository.findByUsername(username)
                .map(DoctorUserConverter::toResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoctorUserResponse> getAllDoctorUsers() {
        logger.debug("获取所有医生用户");
        List<DoctorUser> entities = doctorUserRepository.findAll();
        return DoctorUserConverter.toResponseList(entities);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoctorUserResponse> getActiveDoctorUsers() {
        logger.debug("获取所有激活的医生用户");
        List<DoctorUser> entities = doctorUserRepository.findByIsActiveTrue();
        return DoctorUserConverter.toResponseList(entities);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoctorUserResponse> getDoctorUsersByDepartment(String department) {
        logger.debug("根据科室查找医生用户: {}", department);
        List<DoctorUser> entities = doctorUserRepository.findByDepartment(department);
        return DoctorUserConverter.toResponseList(entities);
    }

    @Override
    public Optional<DoctorUserResponse> updateDoctorUser(String id, DoctorUserUpdateRequest request) {
        logger.info("更新医生用户: {}", id);

        Optional<DoctorUser> existingEntity = doctorUserRepository.findById(id);
        if (existingEntity.isEmpty()) {
            logger.warn("医生用户不存在: {}", id);
            return Optional.empty();
        }

        DoctorUser entity = existingEntity.get();
        DoctorUserConverter.updateEntityFromRequest(entity, request);
        
        DoctorUser updatedEntity = doctorUserRepository.save(entity);
        logger.info("医生用户更新成功: {}", id);
        
        return Optional.of(DoctorUserConverter.toResponse(updatedEntity));
    }

    @Override
    public boolean deleteDoctorUser(String id) {
        logger.info("删除医生用户: {}", id);

        if (!doctorUserRepository.existsById(id)) {
            logger.warn("医生用户不存在: {}", id);
            return false;
        }

        doctorUserRepository.deleteById(id);
        logger.info("医生用户删除成功: {}", id);
        return true;
    }

    @Override
    public Optional<DoctorUserLoginResponse> login(DoctorUserLoginRequest request) {
        logger.info("医生用户登录: {}", request.getUsername());

        Optional<DoctorUser> entity = doctorUserRepository.findByUsernameAndPassword(
                request.getUsername(), request.getPassword());

        if (entity.isEmpty()) {
            logger.warn("登录失败 - 用户名或密码错误: {}", request.getUsername());
            return Optional.empty();
        }

        DoctorUser doctorUser = entity.get();
        if (!doctorUser.getIsActive()) {
            logger.warn("登录失败 - 用户未激活: {}", request.getUsername());
            return Optional.empty();
        }

        // 生成简单的token（实际项目中应该使用JWT等更安全的方案）
        String token = generateToken(doctorUser.getUsername());
        
        logger.info("医生用户登录成功: {}", request.getUsername());
        return Optional.of(DoctorUserConverter.toLoginResponse(doctorUser, token));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return doctorUserRepository.existsByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoctorUserResponse> searchDoctorUsers(String keyword) {
        logger.debug("搜索医生用户: {}", keyword);
        List<DoctorUser> entities = doctorUserRepository.searchByNameOrUsername(keyword);
        return DoctorUserConverter.toResponseList(entities);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllDepartments() {
        logger.debug("获取所有科室列表");
        return doctorUserRepository.findAllDepartments();
    }

    @Override
    @Transactional(readOnly = true)
    public long countActiveDoctors() {
        return doctorUserRepository.countActiveDoctors();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DoctorUser> getEntityById(String id) {
        return doctorUserRepository.findById(id);
    }

    /**
     * 生成医生用户ID
     */
    private String generateDoctorId() {
        return "doc" + System.currentTimeMillis();
    }

    /**
     * 生成登录token（简化版）
     */
    private String generateToken(String username) {
        return UUID.randomUUID().toString() + "_" + username + "_" + System.currentTimeMillis();
    }
}