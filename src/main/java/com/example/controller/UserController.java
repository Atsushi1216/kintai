package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.User;
import com.example.service.UserService;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;


@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	LoginController loginController;

	@Autowired
	HttpSession session;

	@GetMapping("/top")
	public ModelAndView top() {
		ModelAndView mav = new ModelAndView();
		return mav;
	}

	// ユーザ登録画面の表示
	@GetMapping("/signup")
	public ModelAndView signup() {
		// 空のインスタンスを生成
		ModelAndView mav = new ModelAndView();
		User user = new User();

		Object errorMessages = session.getAttribute("errorMessages");
		Object reUser = session.getAttribute("user");
		session.invalidate();

		if(reUser == null) {
			mav.addObject("formModel", user);
		} else {
			mav.addObject("formModel", reUser);
		}

		mav.addObject("errorMessages", errorMessages);
		mav.setViewName("/signup");

		return mav;
	}


	// ユーザ登録
	@PostMapping("/postSignup")
	public ModelAndView postSignup(@ModelAttribute("formModel") User user, @RequestParam(name="password2") String password2) {

		List<String> errorMessages = new ArrayList<String>();
		if (!isValid(user, errorMessages, password2)) {
			session.setAttribute("errorMessages", errorMessages);
			session.setAttribute("user", user);
			return new ModelAndView("redirect:/signup");
		}

		// Hash化のパスワード作成
		String rawPassword = user.getPassword();

		Hasher hasher = Hashing.sha256().newHasher();
		hasher.putString(rawPassword, Charsets.UTF_8);
		HashCode sha256 = hasher.hash();

		String strSha256 = String.valueOf(sha256);

		user.setPassword(strSha256);

		// ユーザ情報の保存
		userService.saveUser(user);
		return new ModelAndView("redirect:/top");
	}

	private boolean isValid(User user, List<String> errorMessages, String password2) {

		String userPass = user.getPassword();

		if (!StringUtils.hasText(user.getUsername())) {
			errorMessages.add("アカウント名を入力してください");
		} else if (10 < user.getUsername().length()) {
			errorMessages.add("アカウント名は10文字以下で入力してください");
		}

		if (!(user.getPassword().equals(password2))) {
			errorMessages.add("パスワードが一致しません");
		}

		if (!StringUtils.hasText(userPass)) {
			errorMessages.add("パスワードを入力してください");
		} else if (32 < userPass.length()) {
			errorMessages.add("パスワードは32文字以下で入力してください");
		} else if (6 > userPass.length()) {
			errorMessages.add("パスワードは6文字以上で入力してください");
		}

		if (errorMessages.size() != 0) {
			return false;
		}
		return true;
	}
}
