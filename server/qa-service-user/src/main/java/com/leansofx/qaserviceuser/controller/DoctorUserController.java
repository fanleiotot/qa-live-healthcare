package com.leansofx.qaserviceuser.controller;

import com.leansofx.qaserviceuser.dto.request.DoctorUserCreateRequest;
import com.leansofx.qaserviceuser.dto.request.DoctorUserLoginRequest;
import com.leansofx.qaserviceuser.dto.request.DoctorUserUpdateRequest;
import com.leansofx.qaserviceuser.dto.response.DoctorUserLoginResponse;
import com.leansofx.qaserviceuser.dto.response.DoctorUserResponse;
import com.leansofx.qaserviceuser.service.DoctorUserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 医生用户控制器
 * 处理医生用户相关的HTTP请求
 */
@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorUserController {

    private static final Logger logger = LoggerFactory.getLogger(DoctorUserController.class);

    private final DoctorUserService doctorUserService;

    public DoctorUserController(DoctorUserService doctorUserService) {
        this.doctorUserService = doctorUserService;
    }

    /**
     * 创建医生用户
     */
    @PostMapping
    public ResponseEntity<DoctorUserResponse> createDoctorUser(@Valid @RequestBody DoctorUserCreateRequest request) {
        logger.info("创建医生用户请求: {}", request.getUsername());
        try {
            DoctorUserResponse response = doctorUserService.createDoctorUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            logger.error("创建医生用户失败: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            logger.error("创建医生用户异常", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 获取所有医生用户
     */
    @GetMapping
    public ResponseEntity<List<DoctorUserResponse>> getAllDoctorUsers() {
        logger.info("获取所有医生用户请求");
        List<DoctorUserResponse> doctors = doctorUserService.getAllDoctorUsers();
        return ResponseEntity.ok(doctors);
    }

    /**
     * 获取所有激活的医生用户
     */
    @GetMapping("/active")
    public ResponseEntity<List<DoctorUserResponse>> getActiveDoctorUsers() {
        logger.info("获取激活的医生用户请求");
        List<DoctorUserResponse> doctors = doctorUserService.getActiveDoctorUsers();
        return ResponseEntity.ok(doctors);
    }

    /**
     * 根据ID获取医生用户
     */
    @GetMapping("/{id}")
    public ResponseEntity<DoctorUserResponse> getDoctorUserById(@PathVariable String id) {
        logger.info("根据ID获取医生用户请求: {}", id);
        return doctorUserService.getDoctorUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 根据用户名获取医生用户
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<DoctorUserResponse> getDoctorUserByUsername(@PathVariable String username) {
        logger.info("根据用户名获取医生用户请求: {}", username);
        return doctorUserService.getDoctorUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 根据科室获取医生用户
     */
    @GetMapping("/department/{department}")
    public ResponseEntity<List<DoctorUserResponse>> getDoctorUsersByDepartment(@PathVariable String department) {
        logger.info("根据科室获取医生用户请求: {}", department);
        List<DoctorUserResponse> doctors = doctorUserService.getDoctorUsersByDepartment(department);
        return ResponseEntity.ok(doctors);
    }

    /**
     * 更新医生用户信息
     */
    @PutMapping("/{id}")
    public ResponseEntity<DoctorUserResponse> updateDoctorUser(
            @PathVariable String id,
            @Valid @RequestBody DoctorUserUpdateRequest request) {
        logger.info("更新医生用户请求: {}", id);
        return doctorUserService.updateDoctorUser(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * 删除医生用户
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorUser(@PathVariable String id) {
        logger.info("删除医生用户请求: {}", id);
        boolean deleted = doctorUserService.deleteDoctorUser(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * 医生用户登录
     */
    @PostMapping("/login")
    public ResponseEntity<DoctorUserLoginResponse> login(@Valid @RequestBody DoctorUserLoginRequest request) {
        logger.info("医生用户登录请求: {}", request.getUsername());
        return doctorUserService.login(request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    /**
     * 搜索医生用户
     */
    @GetMapping("/search")
    public ResponseEntity<List<DoctorUserResponse>> searchDoctorUsers(@RequestParam String keyword) {
        logger.info("搜索医生用户请求: {}", keyword);
        List<DoctorUserResponse> doctors = doctorUserService.searchDoctorUsers(keyword);
        return ResponseEntity.ok(doctors);
    }

    /**
     * 获取所有科室列表
     */
    @GetMapping("/departments")
    public ResponseEntity<List<String>> getAllDepartments() {
        logger.info("获取所有科室列表请求");
        List<String> departments = doctorUserService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    /**
     * 统计激活的医生用户数量
     */
    @GetMapping("/stats/active-count")
    public ResponseEntity<Map<String, Long>> getActiveDoctorCount() {
        logger.info("获取激活医生用户数量统计请求");
        long count = doctorUserService.countActiveDoctors();
        return ResponseEntity.ok(Map.of("count", count));
    }

    /**
     * 检查用户名是否存在
     */
    @GetMapping("/exists/{username}")
    public ResponseEntity<Map<String, Boolean>> existsByUsername(@PathVariable String username) {
        logger.info("检查用户名是否存在请求: {}", username);
        boolean exists = doctorUserService.existsByUsername(username);
        return ResponseEntity.ok(Map.of("exists", exists));
    }
}