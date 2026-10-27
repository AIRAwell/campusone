package com.campusone.campusone.repository;

import com.campusone.campusone.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByStatus(String status);
    long countByStatus(String status);
}
