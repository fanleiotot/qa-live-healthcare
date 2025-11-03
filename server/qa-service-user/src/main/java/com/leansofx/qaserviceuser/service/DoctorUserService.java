package com.leansofx.qaserviceuser.service;

import com.leansofx.qaserviceuser.dto.request.DoctorUserCreateRequest;
import com.leansofx.qaserviceuser.dto.request.DoctorUserLoginRequest;
import com.leansofx.qaserviceuser.dto.request.DoctorUserUpdateRequest;
import com.leansofx.qaserviceuser.dto.response.DoctorUserLoginResponse;
import com.leansofx.qaserviceuser.dto.response.DoctorUserResponse;
import com.leansofx.qaserviceuser.entity.DoctorUser;

import java.util.List;
import java.util.Optional;

/**
 * 医生用户服务接口
 */
public interface DoctorUserService {

    /**
     * 创建医生用户
     *
     * @param request 创建请求
     * @return 医生用户响应
     */
    DoctorUserResponse createDoctorUser(DoctorUserCreateRequest request);

    /**
     * 根据ID查找医生用户
     *
     * @param id 用户ID
     * @return 医生用户响应
     */
    Optional<DoctorUserResponse> getDoctorUserById(String id);

    /**
     * 根据用户名查找医生用户
     *
     * @param username 用户名
     * @return 医生用户响应
     */
    Optional<DoctorUserResponse> getDoctorUserByUsername(String username);

    /**
     * 获取所有医生用户
     *
     * @return 医生用户响应列表
     */
    List<DoctorUserResponse> getAllDoctorUsers();

    /**
     * 获取所有激活的医生用户
     *
     * @return 激活的医生用户响应列表
     */
    List<DoctorUserResponse> getActiveDoctorUsers();

    /**
     * 根据科室查找医生用户
     *
     * @param department 科室名称
     * @return 医生用户响应列表
     */
    List<DoctorUserResponse> getDoctorUsersByDepartment(String department);

    /**
     * 更新医生用户信息
     *
     * @param id 用户ID
     * @param request 更新请求
     * @return 更新后的医生用户响应
     */
    Optional<DoctorUserResponse> updateDoctorUser(String id, DoctorUserUpdateRequest request);

    /**
     * 删除医生用户
     *
     * @param id 用户ID
     * @return 是否删除成功
     */
    boolean deleteDoctorUser(String id);

    /**
     * 医生用户登录
     *
     * @param request 登录请求
     * @return 登录响应（包含token）
     */
    Optional<DoctorUserLoginResponse> login(DoctorUserLoginRequest request);

    /**
     * 检查用户名是否存在
     *
     * @param username 用户名
     * @return 是否存在
     */
    boolean existsByUsername(String username);

    /**
     * 搜索医生用户（根据姓名或用户名）
     *
     * @param keyword 搜索关键词
     * @return 医生用户响应列表
     */
    List<DoctorUserResponse> searchDoctorUsers(String keyword);

    /**
     * 获取所有科室列表
     *
     * @return 科室列表
     */
    List<String> getAllDepartments();

    /**
     * 统计激活的医生用户数量
     *
     * @return 激活用户数量
     */
    long countActiveDoctors();

    /**
     * 根据ID获取实体（内部使用）
     *
     * @param id 用户ID
     * @return 医生用户实体
     */
    Optional<DoctorUser> getEntityById(String id);
}