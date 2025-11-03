package com.leansofx.qaserviceuser.repository;

import com.leansofx.qaserviceuser.entity.DoctorUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 医生用户数据访问层接口
 */
@Repository
public interface DoctorUserRepository extends JpaRepository<DoctorUser, String> {

    /**
     * 根据用户名查找医生用户
     *
     * @param username 用户名
     * @return 医生用户实体
     */
    Optional<DoctorUser> findByUsername(String username);

    /**
     * 根据用户名和密码查找医生用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 医生用户实体
     */
    Optional<DoctorUser> findByUsernameAndPassword(String username, String password);

    /**
     * 查找所有激活的医生用户
     *
     * @return 激活的医生用户列表
     */
    List<DoctorUser> findByIsActiveTrue();

    /**
     * 根据科室查找医生用户
     *
     * @param department 科室名称
     * @return 医生用户列表
     */
    List<DoctorUser> findByDepartment(String department);

    /**
     * 根据科室和激活状态查找医生用户
     *
     * @param department 科室名称
     * @param isActive 激活状态
     * @return 医生用户列表
     */
    List<DoctorUser> findByDepartmentAndIsActive(String department, Boolean isActive);

    /**
     * 根据用户名判断用户是否存在
     *
     * @param username 用户名
     * @return 是否存在
     */
    boolean existsByUsername(String username);

    /**
     * 根据激活状态统计医生用户数量
     *
     * @param isActive 激活状态
     * @return 用户数量
     */
    long countByIsActive(Boolean isActive);

    /**
     * 根据科室统计医生用户数量
     *
     * @param department 科室名称
     * @return 用户数量
     */
    long countByDepartment(String department);

    /**
     * 模糊查询医生用户（根据姓名或用户名）
     *
     * @param keyword 查询关键词
     * @return 医生用户列表
     */
    @Query("SELECT d FROM DoctorUser d WHERE d.name LIKE %:keyword% OR d.username LIKE %:keyword%")
    List<DoctorUser> searchByNameOrUsername(@Param("keyword") String keyword);

    /**
     * 查找激活的医生用户数量
     *
     * @return 激活用户数量
     */
    @Query("SELECT COUNT(d) FROM DoctorUser d WHERE d.isActive = true")
    long countActiveDoctors();

    /**
     * 查找所有科室列表
     *
     * @return 科室列表
     */
    @Query("SELECT DISTINCT d.department FROM DoctorUser d ORDER BY d.department")
    List<String> findAllDepartments();
}