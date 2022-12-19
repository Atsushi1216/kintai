package com.example.controller;

import java.sql.Timestamp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.Attendance;

@Controller
public class AttendanceController {
	// 勤怠管理画面表示
	@GetMapping("/attendance")
	public ModelAndView attendance() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}

	// 勤怠登録処理
	@PostMapping("/postAttendance")
	public ModelAndView postAttendance(@ModelAttribute("attendanceForm") Attendance attendance,
			@RequestParam(name="strat_time") Timestamp startTime,
			@RequestParam(name="end_time") Timestamp endTime,
			@RequestParam(name="rest_start_time") Timestamp restStartTime,
			@RequestParam(name="rest_end_time") Timestamp restEndTime) {



		return new ModelAndView("/attendance");
	}
}
