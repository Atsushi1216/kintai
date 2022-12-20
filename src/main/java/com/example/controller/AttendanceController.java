package com.example.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.Attendance;
import com.example.entity.User;
import com.example.repository.AttendanceRepository;

@Controller
public class AttendanceController {

	@Autowired
	AttendanceRepository attendanceRepository;

	@Autowired
	HttpSession session;

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


		ModelAndView mav = new ModelAndView();

		// 初期値nullを設定することで未記入の状態
		Timestamp timestampStartTime = null;
		Timestamp timestampEndTime = null;

		User user = (User) session.getAttribute("loginUser");

		// 開始時間と終了時間をそれぞれ定義
		String dateStartTime = startTime + " 00:00:00";
		String dateEndTime = endTime + "23:59:59";

		// string型をTimestamp型へ変換
		timestampStartTime = Timestamp.valueOf(dateStartTime);
		timestampEndTime = Timestamp.valueOf(dateEndTime);

		attendance.setUserId(user.getId());
		attendance.setStartTime(timestampStartTime);
		attendance.setEndTime(timestampEndTime);

		return new ModelAndView("redirect:/attendance");
	}
}
