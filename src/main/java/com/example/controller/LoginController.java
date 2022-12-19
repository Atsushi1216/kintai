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
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	HttpSession session;

	// ログイン画面
	@GetMapping("/login")
	public ModelAndView login() {
		// 空のインスタンスを作成
		ModelAndView mav = new ModelAndView();
		User user = new User();

		Object errorMessages = session.getAttribute("errorMessages");
		session.invalidate();

		mav.addObject("errorMessages", errorMessages);
		mav.addObject("user", user);
		mav.setViewName("login");

		// 画面表示のため、mavへ返す
		return mav;
	}

	@PostMapping("/postLogin")
	public ModelAndView postLogin(@ModelAttribute("loginUser") User user, @RequestParam(name="password") String password) {
		ModelAndView mav = new ModelAndView();
		List<String> errorMessages = new ArrayList<String>();

		if (!isValid(user, errorMessages)) {
			session.setAttribute("errorMessages", errorMessages);
			return new ModelAndView("redirect:/login");
		}

		String rawPassword = user.getPassword();

		Hasher hasher = Hashing.sha256().newHasher();
		hasher.putString(rawPassword, Charsets.UTF_8);
		HashCode sha256 = hasher.hash();

		String strSha256 = String.valueOf(sha256);


		user = userService.findLoginUser(user.getUsername(), strSha256);

		if(user == null) {
			errorMessages.add("ユーザ名またはパスワードが誤っています");
			mav.addObject("errorMessages", errorMessages);
			mav.setViewName("/login");
			return mav;
		}

		session.setAttribute("loginUser", user);

		return new ModelAndView("redirect:/top");
	}

	//ログアウト処理
	@GetMapping("/logout")
	public ModelAndView logout() {
		// セッションの無効化
		session.invalidate();
		return new ModelAndView("redirect:/login");
	}

	private boolean isValid(User user, List<String> errorMessages) {

		String userPass = user.getPassword();

		if (!StringUtils.hasText(user.getUsername())) {
			errorMessages.add("ユーザー名を入力してください");
		}

		if (!StringUtils.hasText(userPass)) {
			errorMessages.add("パスワードを入力してください");
		}

		if (errorMessages.size() != 0) {
			return false;
		}
		return true;
	}


}
