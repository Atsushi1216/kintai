package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView postAttendance() {
		return new ModelAndView("/attendance");
	}
}
