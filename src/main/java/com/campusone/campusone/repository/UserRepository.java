package com.campusone.campusone.repository;

import com.campusone.campusone.model.Role;
import com.campusone.campusone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByRole(Role role);
    long countByRole(Role role);
}
