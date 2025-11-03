package com.leansofx.qaserviceuser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leansofx.qaserviceuser.dto.response.DoctorUserResponse;
import com.leansofx.qaserviceuser.service.DoctorUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 医生用户数据验证测试
 * 验证数据库中的数据是否与前端JSON数据完全一致
 */
@SpringBootTest
@ActiveProfiles("test")
public class DoctorUserDataValidationTest {

    @Autowired
    private DoctorUserService doctorUserService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 验证数据库中的医生用户数据与前端JSON数据完全一致
     */
    @Test
    public void testDoctorUserDataConsistency() throws IOException {
        // 从JSON文件加载前端数据
        ClassPathResource resource = new ClassPathResource("../../web/qa-web/src/data/doctor-user-list.json");
        List<Map<String, Object>> jsonData = objectMapper.readValue(
                resource.getInputStream(),
                new TypeReference<List<Map<String, Object>>>() {}
        );

        // 从数据库获取所有医生用户
        List<DoctorUserResponse> dbDoctors = doctorUserService.getAllDoctorUsers();

        // 验证数量一致
        assertEquals(jsonData.size(), dbDoctors.size(), 
                "数据库中的医生用户数量与JSON数据不一致");

        // 验证每个医生用户的数据一致
        for (Map<String, Object> jsonDoctor : jsonData) {
            String id = (String) jsonDoctor.get("id");
            
            // 根据ID从数据库查找对应的医生
            DoctorUserResponse dbDoctor = dbDoctors.stream()
                    .filter(d -> d.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("找不到ID为 " + id + " 的医生用户"));

            // 验证各个字段
            assertEquals(jsonDoctor.get("username"), dbDoctor.getUsername(),
                    "医生 " + id + " 的用户名不一致");
            assertEquals(jsonDoctor.get("name"), dbDoctor.getName(),
                    "医生 " + id + " 的姓名不一致");
            assertEquals(jsonDoctor.get("title"), dbDoctor.getTitle(),
                    "医生 " + id + " 的职称不一致");
            assertEquals(jsonDoctor.get("department"), dbDoctor.getDepartment(),
                    "医生 " + id + " 的科室不一致");
            assertEquals(jsonDoctor.get("avatar"), dbDoctor.getAvatar(),
                    "医生 " + id + " 的头像URL不一致");
            assertEquals(jsonDoctor.get("experience"), dbDoctor.getExperience(),
                    "医生 " + id + " 的经验描述不一致");
            assertEquals(jsonDoctor.get("isActive"), dbDoctor.getIsActive(),
                    "医生 " + id + " 的激活状态不一致");

            // 验证专业特长
            @SuppressWarnings("unchecked")
            List<String> jsonSpecialties = (List<String>) jsonDoctor.get("specialties");
            assertEquals(jsonSpecialties.size(), dbDoctor.getSpecialties().size(),
                    "医生 " + id + " 的专业特长数量不一致");
            
            for (String specialty : jsonSpecialties) {
                assertTrue(dbDoctor.getSpecialties().contains(specialty),
                        "医生 " + id + " 缺少专业特长: " + specialty);
            }
        }
    }

    /**
     * 验证激活状态的医生用户数量
     */
    @Test
    public void testActiveDoctorCount() {
        List<DoctorUserResponse> activeDoctors = doctorUserService.getActiveDoctorUsers();
        
        // JSON数据中应该有4个激活的医生（除了doc004都是激活的）
        assertEquals(4, activeDoctors.size(), 
                "激活的医生用户数量应该是4个");

        // 验证所有激活的医生
        assertTrue(activeDoctors.stream().allMatch(DoctorUserResponse::getIsActive),
                "所有返回的医生用户应该都是激活状态");
    }

    /**
     * 验证按科室查询功能
     */
    @Test
    public void testFindByDepartment() {
        // 测试心内科
        List<DoctorUserResponse> cardiologists = doctorUserService.getDoctorUsersByDepartment("心内科");
        assertEquals(1, cardiologists.size(), "心内科应该有1个医生");
        assertEquals("张伟医生", cardiologists.get(0).getName());

        // 测试儿科
        List<DoctorUserResponse> pediatricians = doctorUserService.getDoctorUsersByDepartment("儿科");
        assertEquals(1, pediatricians.size(), "儿科应该有1个医生");
        assertEquals("李娜医生", pediatricians.get(0).getName());
    }

    /**
     * 验证搜索功能
     */
    @Test
    public void testSearchFunctionality() {
        // 按姓名搜索
        List<DoctorUserResponse> results = doctorUserService.searchDoctorUsers("张伟");
        assertEquals(1, results.size(), "搜索'张伟'应该返回1个结果");
        assertEquals("张伟医生", results.get(0).getName());

        // 按用户名搜索
        results = doctorUserService.searchDoctorUsers("dr-li-na");
        assertEquals(1, results.size(), "搜索'dr-li-na'应该返回1个结果");
        assertEquals("李娜医生", results.get(0).getName());
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

        // 错误的密码
        loginRequest.setPassword("wrong-password");
        loginResponse = doctorUserService.login(loginRequest);
        assertFalse(loginResponse.isPresent(), "错误的密码应该登录失败");

        // 禁用的用户
        loginRequest.setUsername("dr-liu-min");
        loginRequest.setPassword("123456");
        loginResponse = doctorUserService.login(loginRequest);
        assertFalse(loginResponse.isPresent(), "禁用的用户应该无法登录");
    }
}