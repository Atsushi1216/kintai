package com.example.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer>{
	List<Attendance> findByUserIdAndCategoryIdAndStartTimeBetweenOrderByEndTimeDesc(Integer id, Integer categoryId, Timestamp startTime, Timestamp endTime);
}
