package com.leansoftx.qaserviceuser.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leansoftx.qaserviceuser.model.DoctorUser;
import com.leansoftx.qaserviceuser.service.DoctorUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    
    private DoctorUser doctorUser1;
    private DoctorUser doctorUser2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(doctorUserController).build();

        // Initialize test data
        doctorUser1 = new DoctorUser();
        doctorUser1.setId("doc001");
        doctorUser1.setUsername("dr-zhang-wei");
        doctorUser1.setPassword("123456");
        doctorUser1.setName("张伟医生");
        doctorUser1.setTitle("主任医师");
        doctorUser1.setDepartment("心内科");
        doctorUser1.setAvatar("avatar1.jpg");
        doctorUser1.setExperience("15年临床经验");
        doctorUser1.setSpecialties("[\"高血压\", \"冠心病\"]"); // JSON string format
        doctorUser1.setActive(true);
        doctorUser1.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        doctorUser1.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        doctorUser2 = new DoctorUser();
        doctorUser2.setId("doc002");
        doctorUser2.setUsername("dr-li-na");
        doctorUser2.setPassword("123456");
        doctorUser2.setName("李娜医生");
        doctorUser2.setTitle("副主任医师");
        doctorUser2.setDepartment("儿科");
        doctorUser2.setAvatar("avatar2.jpg");
        doctorUser2.setExperience("10年临床经验");
        doctorUser2.setSpecialties("[\"儿童感冒\", \"疫苗接种\"]"); // JSON string format
        doctorUser2.setActive(true);
        doctorUser2.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        doctorUser2.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        List<DoctorUser> doctors = Arrays.asList(doctorUser1, doctorUser2);

        // Mock service methods
        when(doctorUserService.getAllDoctors()).thenReturn(doctors);
        when(doctorUserService.getDoctorById("doc001")).thenReturn(Optional.of(doctorUser1));
        when(doctorUserService.getDoctorById("doc002")).thenReturn(Optional.of(doctorUser2));
        when(doctorUserService.getDoctorById("nonexistent")).thenReturn(Optional.empty());
        when(doctorUserService.saveDoctor(any(DoctorUser.class))).thenReturn(doctorUser1);
        when(doctorUserService.updateDoctor(anyString(), any(DoctorUser.class))).thenAnswer(invocation -> {
            DoctorUser updatedDoctor = invocation.getArgument(1);
            updatedDoctor.setId(invocation.getArgument(0));
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
    void getDoctorById_ShouldReturnNotFoundWhenNotExists() throws Exception {
        mockMvc.perform(get("/api/doctors/nonexistent"))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveDoctor_ShouldReturnCreatedDoctor() throws Exception {
        DoctorUser newDoctor = new DoctorUser();
        newDoctor.setId("doc003");
        newDoctor.setUsername("dr-wang-qiang");
        newDoctor.setPassword("123456");
        newDoctor.setName("王强医生");
        newDoctor.setTitle("主治医师");
        newDoctor.setDepartment("骨科");
        newDoctor.setAvatar("avatar3.jpg");
        newDoctor.setExperience("8年临床经验");
        newDoctor.setSpecialties("[\"骨折\", \"关节炎\"]");
        newDoctor.setActive(true);

        mockMvc.perform(post("/api/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newDoctor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("doc001"));
    }

    @Test
    void updateDoctor_ShouldReturnUpdatedDoctor() throws Exception {
        DoctorUser updatedDoctor = new DoctorUser();
        updatedDoctor.setUsername("dr-zhang-wei");
        updatedDoctor.setPassword("123456");
        updatedDoctor.setName("张伟医生");
        updatedDoctor.setTitle("主任医师");
        updatedDoctor.setDepartment("心内科");
        updatedDoctor.setAvatar("new-avatar.jpg");
        updatedDoctor.setExperience("16年临床经验");
        updatedDoctor.setSpecialties("[\"高血压\", \"冠心病\", \"心律失常\"]");
        updatedDoctor.setActive(true);

        mockMvc.perform(put("/api/doctors/doc001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDoctor)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("doc001"))
                .andExpect(jsonPath("$.avatar").value("new-avatar.jpg"))
                .andExpect(jsonPath("$.experience").value("16年临床经验"));
    }

    @Test
    void deleteDoctor_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/doctors/doc001"))
                .andExpect(status().isOk());
        
        verify(doctorUserService, times(1)).deleteDoctor("doc001");
    }
}