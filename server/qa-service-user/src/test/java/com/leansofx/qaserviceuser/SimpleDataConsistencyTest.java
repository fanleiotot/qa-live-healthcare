package com.leansofx.qaserviceuser;

import com.leansofx.qaserviceuser.dto.response.DoctorUserResponse;
import com.leansofx.qaserviceuser.service.DoctorUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 简单的数据一致性验证测试
 * 验证数据库初始化数据与前端JSON数据的一致性
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class SimpleDataConsistencyTest {

    @Autowired
    private DoctorUserService doctorUserService;

    /**
     * 验证数据库中的医生用户数量与前端JSON数据一致
     */
    @Test
    public void testDoctorUserCount() {
        List<DoctorUserResponse> allDoctors = doctorUserService.getAllDoctorUsers();
        
        // 前端JSON文件中有5个医生用户
        assertEquals(5, allDoctors.size(), "数据库中的医生用户数量应该是5个");
        System.out.println("✅ 医生用户数量验证通过: " + allDoctors.size() + " 个");
    }

    /**
     * 验证激活状态的医生用户数量
     */
    @Test
    public void testActiveDoctorCount() {
        List<DoctorUserResponse> activeDoctors = doctorUserService.getActiveDoctorUsers();
        
        // JSON数据中应该有4个激活的医生（除了doc004都是激活的）
        assertEquals(4, activeDoctors.size(), "激活的医生用户数量应该是4个");
        System.out.println("✅ 激活医生数量验证通过: " + activeDoctors.size() + " 个");
    }

    /**
     * 验证具体的医生用户信息
     */
    @Test
    public void testSpecificDoctorData() {
        // 验证张伟医生（doc001）
        var zhangDoctor = doctorUserService.getDoctorUserById("doc001");
        assertTrue(zhangDoctor.isPresent(), "应该能找到doc001医生");
        assertEquals("张伟医生", zhangDoctor.get().getName());
        assertEquals("dr-zhang-wei", zhangDoctor.get().getUsername());
        assertEquals("主任医师", zhangDoctor.get().getTitle());
        assertEquals("心内科", zhangDoctor.get().getDepartment());
        assertEquals("15年临床经验", zhangDoctor.get().getExperience());
        assertTrue(zhangDoctor.get().getIsActive());
        assertEquals(3, zhangDoctor.get().getSpecialties().size());
        System.out.println("✅ 张伟医生数据验证通过");

        // 验证刘敏医生（doc004）- 禁用状态
        var liuDoctor = doctorUserService.getDoctorUserById("doc004");
        assertTrue(liuDoctor.isPresent(), "应该能找到doc004医生");
        assertEquals("刘敏医生", liuDoctor.get().getName());
        assertEquals("dr-liu-min", liuDoctor.get().getUsername());
        assertEquals("主任医师", liuDoctor.get().getTitle());
        assertEquals("妇产科", liuDoctor.get().getDepartment());
        assertEquals("18年临床经验", liuDoctor.get().getExperience());
        assertFalse(liuDoctor.get().getIsActive()); // 应该是禁用状态
        assertEquals(3, liuDoctor.get().getSpecialties().size());
        System.out.println("✅ 刘敏医生数据验证通过");
    }

    /**
     * 验证按科室查询功能
     */
    @Test
    public void testDepartmentQuery() {
        // 测试心内科
        var cardiologists = doctorUserService.getDoctorUsersByDepartment("心内科");
        assertEquals(1, cardiologists.size(), "心内科应该有1个医生");
        assertEquals("张伟医生", cardiologists.get(0).getName());
        System.out.println("✅ 心内科查询验证通过");

        // 测试儿科
        var pediatricians = doctorUserService.getDoctorUsersByDepartment("儿科");
        assertEquals(1, pediatricians.size(), "儿科应该有1个医生");
        assertEquals("李娜医生", pediatricians.get(0).getName());
        System.out.println("✅ 儿科查询验证通过");

        // 测试骨科
        var orthopedists = doctorUserService.getDoctorUsersByDepartment("骨科");
        assertEquals(1, orthopedists.size(), "骨科应该有1个医生");
        assertEquals("王强医生", orthopedists.get(0).getName());
        System.out.println("✅ 骨科查询验证通过");
    }

    /**
     * 验证搜索功能
     */
    @Test
    public void testSearchFunctionality() {
        // 按姓名搜索
        var results = doctorUserService.searchDoctorUsers("张伟");
        assertEquals(1, results.size(), "搜索'张伟'应该返回1个结果");
        assertEquals("张伟医生", results.get(0).getName());
        System.out.println("✅ 按姓名搜索验证通过");

        // 按用户名搜索
        results = doctorUserService.searchDoctorUsers("dr-li-na");
        assertEquals(1, results.size(), "搜索'dr-li-na'应该返回1个结果");
        assertEquals("李娜医生", results.get(0).getName());
        System.out.println("✅ 按用户名搜索验证通过");
    }

    /**
     * 验证登录功能
     */
    @Test
    public void testLoginFunctionality() {
        // 正确的登录凭据
        var loginRequest = new com.leansofx.qaserviceuser.dto.request.DoctorUserLoginRequest();
        loginRequest.setUsername("dr-zhang-wei");
        loginRequest.setPassword("123456");

        var loginResponse = doctorUserService.login(loginRequest);
        assertTrue(loginResponse.isPresent(), "正确的凭据应该登录成功");
        assertEquals("张伟医生", loginResponse.get().getName());
        System.out.println("✅ 正确凭据登录验证通过");

        // 错误的密码
        loginRequest.setPassword("wrong-password");
        loginResponse = doctorUserService.login(loginRequest);
        assertFalse(loginResponse.isPresent(), "错误的密码应该登录失败");
        System.out.println("✅ 错误密码验证通过");

        // 禁用的用户
        loginRequest.setUsername("dr-liu-min");
        loginRequest.setPassword("123456");
        loginResponse = doctorUserService.login(loginRequest);
        assertFalse(loginResponse.isPresent(), "禁用的用户应该无法登录");
        System.out.println("✅ 禁用用户登录验证通过");
    }
}