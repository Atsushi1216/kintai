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

	// 出勤登録処理

	// 退勤登録処理

	// 休憩開始時間登録

	// 休憩終了時間登録

	// 編集のためID一件取得


}
