
package com.example.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {

			System.out.println("★ログインチェック");
			// セッションが存在しない場合NULLを返す ※引数がtrue：セッションが開始されてない場合、新しいセッションを返す
			HttpSession session = ((HttpServletRequest)request).getSession(false);
			List<String> errorMessages = new ArrayList<>();

			if (session != null) {
				System.out.println("★何かしらセッションがある");
				// 何かしらセッションがある
				Object loginCheck = session.getAttribute("loginUser");

				if (loginCheck == null) {
					System.out.println("------------------");
					System.out.println("★loginCheckセッションがNull");
					//loginCheckがnull→ログイン画面へ飛ばす
					errorMessages.add("サインインしてください");
					session.setAttribute("errorMessages", errorMessages);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/signin");
					dispatcher.forward(request, response);

					return;
				} else {
					// loginCheckがある→通常どおり遷移
					chain.doFilter(request, response);
				}
			} else {
				 //セッションが何もない→ログイン画面へ飛ばす
				System.out.println("★セッションがNull");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/resignin");
				dispatcher.forward(request, response);
				return;
			}
		}
		catch(ServletException se) {
		}
		catch(IOException e){
		}
	}

	 @Bean
	    public FilterRegistrationBean<LoginFilter> filter()
	    {
	        FilterRegistrationBean<LoginFilter> bean = new FilterRegistrationBean<>();

	        bean.setFilter(new LoginFilter());
	        bean.addUrlPatterns("/");
	        bean.addUrlPatterns("/edit");
	        bean.addUrlPatterns("/edit_check");
	        bean.addUrlPatterns("/edit_confirm");
	        bean.addUrlPatterns("/new_start");
	        bean.addUrlPatterns("/new_end");
	        bean.addUrlPatterns("/new_rest_start");
	        bean.addUrlPatterns("/new_rest_end");
	        bean.addUrlPatterns("/edit_attendance/");
	        bean.addUrlPatterns("/test/");



	        //または、 `setUrlPatterns()`を使用します

	        return bean;
	    }


	@Override
	public void destroy() {

	}
}
