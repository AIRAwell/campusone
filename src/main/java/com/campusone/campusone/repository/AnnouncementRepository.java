package com.campusone.campusone.repository;

import com.campusone.campusone.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findAllByOrderByIdDesc();
    List<Announcement> findTop3ByOrderByIdDesc();
    List<Announcement> findTop4ByOrderByIdDesc();
}
