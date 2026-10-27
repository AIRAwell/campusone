package com.campusone.campusone.repository;

import com.campusone.campusone.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStudentUsernameOrderByIdDesc(String studentUsername);
    List<LeaveRequest> findAllByOrderByIdDesc();
    long countByStatus(String status);
}
