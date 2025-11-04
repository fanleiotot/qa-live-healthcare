package com.leansofx.qaserviceuser.repository;

import com.leansofx.qaserviceuser.entity.DoctorUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorUserRepository extends JpaRepository<DoctorUser, String> {
}