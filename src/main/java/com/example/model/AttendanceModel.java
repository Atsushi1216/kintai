package com.example.model;

import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class AttendanceModel {

	private int id;

	private int userId;

	private int categoryId;

	private String content;

	@Pattern(regexp="(?=.*[a-zA-Z])", message="パスワードは英数字混合の6文字以上20文字以内で入力してください")
	private String startDate;

	private String endDate;

	private String restStartDate;

	private String restEndDate;

	private String borderDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRestStartDate() {
		return restStartDate;
	}

	public void setRestStartDate(String restStartDate) {
		this.restStartDate = restStartDate;
	}

	public String getRestEndDate() {
		return restEndDate;
	}

	public void setRestEndDate(String restEndDate) {
		this.restEndDate = restEndDate;
	}

	public String getBorderDate() {
		return borderDate;
	}

	public void setBorderDate(String borderDate) {
		this.borderDate = borderDate;
	}

}
