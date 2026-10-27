package com.campusone.campusone.repository;

import com.campusone.campusone.model.TimetableEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimetableRepository extends JpaRepository<TimetableEntry, Long> {
    List<TimetableEntry> findByEntryDate(String entryDate);
    List<TimetableEntry> findByDayOfWeek(String dayOfWeek);
    List<TimetableEntry> findByFacultyName(String facultyName);
}
