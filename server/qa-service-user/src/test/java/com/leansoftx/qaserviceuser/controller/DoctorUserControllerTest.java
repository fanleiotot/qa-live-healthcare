package com.leansoftx.qaserviceuser.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leansoftx.qaserviceuser.model.Doctor;
import com.leansoftx.qaserviceuser.service.DoctorUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DoctorUserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DoctorUserService doctorUserService;

    @InjectMocks
    private DoctorUserController doctorUserController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(doctorUserController).build();

        // Initialize mock service
        Doctor doctor1 = new Doctor();
        doctor1.setId("doc001");
        doctor1.setUsername("dr-zhang-wei");
        doctor1.setPassword("123456");
        doctor1.setName("张伟医生");
        doctor1.setTitle("主任医师");
        doctor1.setDepartment("心内科");
        doctor1.setAvatar("avatar1.jpg");
        doctor1.setExperience("15年临床经验");
        doctor1.setSpecialties(Arrays.asList("高血压", "冠心病"));
        doctor1.setActive(true);
        doctor1.setActive(true);

        Doctor doctor2 = new Doctor();
        doctor2.setId("doc002");
        doctor2.setUsername("dr-li-na");
        doctor2.setPassword("123456");
        doctor2.setName("李娜医生");
        doctor2.setTitle("副主任医师");
        doctor2.setDepartment("儿科");
        doctor2.setAvatar("avatar2.jpg");
        doctor2.setExperience("10年临床经验");
        doctor2.setSpecialties(Arrays.asList("儿童感冒", "疫苗接种"));
        doctor2.setActive(true);

        List<Doctor> doctors = Arrays.asList(doctor1, doctor2);

        when(doctorUserService.getAllDoctors()).thenReturn(doctors);
        when(doctorUserService.getDoctorById(anyString())).thenReturn(doctor1);
        when(doctorUserService.getActiveDoctors()).thenReturn(doctors);
        when(doctorUserService.addDoctor(any(Doctor.class))).thenReturn(doctor1);
        when(doctorUserService.updateDoctor(anyString(), any(Doctor.class))).thenAnswer(invocation -> {
            Doctor updatedDoctor = invocation.getArgument(1);
            return updatedDoctor;
        });
        doNothing().when(doctorUserService).deleteDoctor(anyString());
    }

    @Test
    void getAllDoctors_ShouldReturnAllDoctors() throws Exception {
        mockMvc.perform(get("/api/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value("doc001"))
                .andExpect(jsonPath("$[1].id").value("doc002"));
    }

    @Test
    void getDoctorById_ShouldReturnDoctor() throws Exception {
        mockMvc.perform(get("/api/doctors/doc001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("doc001"))
                .andExpect(jsonPath("$.name").value("张伟医生"));
    }

    @Test
    void getActiveDoctors_ShouldReturnActiveDoctors() throws Exception {
        mockMvc.perform(get("/api/doctors/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].isActive").value(true))
                .andExpect(jsonPath("$[1].isActive").value(true));
    }

    @Test
    void addDoctor_ShouldReturnCreatedDoctor() throws Exception {
        Doctor newDoctor = new Doctor();
        newDoctor.setId("doc003");
        newDoctor.setUsername("dr-wang-qiang");
        newDoctor.setPassword("123456");
        newDoctor.setName("王强医生");
        newDoctor.setTitle("主治医师");
        newDoctor.setDepartment("骨科");
        newDoctor.setAvatar("avatar3.jpg");
        newDoctor.setExperience("8年临床经验");
        newDoctor.setSpecialties(Arrays.asList("骨折", "关节炎"));
        newDoctor.setActive(true);

        mockMvc.perform(post("/api/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newDoctor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("doc001"));
    }

    @Test
    void updateDoctor_ShouldReturnUpdatedDoctor() throws Exception {
        Doctor updatedDoctor = new Doctor();
        updatedDoctor.setId("doc001");
        updatedDoctor.setUsername("dr-zhang-wei");
        updatedDoctor.setPassword("123456");
        updatedDoctor.setName("张伟医生");
        updatedDoctor.setTitle("主任医师");
        updatedDoctor.setDepartment("心内科");
        updatedDoctor.setAvatar("new-avatar.jpg");
        updatedDoctor.setExperience("16年临床经验");
        updatedDoctor.setSpecialties(Arrays.asList("高血压", "冠心病", "心律失常"));
        updatedDoctor.setActive(true);

        mockMvc.perform(put("/api/doctors/doc001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDoctor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.avatar").value("new-avatar.jpg"))
                .andExpect(jsonPath("$.experience").value("16年临床经验"));
    }

    @Test
    void deleteDoctor_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/doctors/doc001"))
                .andExpect(status().isOk());
    }
}