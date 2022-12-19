package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	// レコード追加
	public void saveUser(User user) {
		userRepository.save(user);
	}

	// ユーザログイン
	public User findLoginUser(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
}
