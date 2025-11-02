package com.leansoftx.qaserviceuser.repository;

import com.leansoftx.qaserviceuser.model.DoctorUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorUserRepository extends JpaRepository<DoctorUser, String> {
}