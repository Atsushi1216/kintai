package com.example.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AttendanceRepository;

@Service
public class AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepository;

	@Autowired
	HttpSession session;


}
