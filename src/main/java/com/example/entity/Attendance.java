package com.example.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="attendances")
public class Attendance {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="user_id")
	private Integer userId;

	@Column(name="category_id")
	private Integer categoryId;

	@Column(name = "start_time", insertable = false, updatable = true)
	private Timestamp startTime;

	@Column(name = "end_time", insertable = false, updatable = true)
	private Timestamp endTime;

	@Column(name = "rest_start_time", insertable = false, updatable = true)
	private Timestamp restStartTime;

	@Column(name = "rest_end_time", insertable = false, updatable = true)
	private Timestamp restEndTime;

	@Column(name = "border_date", insertable = false, updatable = false)
	private Timestamp borderDate;

	@Column(name = "created_time", insertable = false, updatable = false)
	private Timestamp createdTime;

	@Column(name = "updated_time", insertable = false, updatable = false)
	private Timestamp updatedTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getRestStartTime() {
		return restStartTime;
	}

	public void setRestStartTime(Timestamp restStartTime) {
		this.restStartTime = restStartTime;
	}

	public Timestamp getRestEndTime() {
		return restEndTime;
	}

	public void setRestEndTime(Timestamp restEndTime) {
		this.restEndTime = restEndTime;
	}

	public Timestamp getBorderDate() {
		return borderDate;
	}

	public void setBorderDate(Timestamp borderDate) {
		this.borderDate = borderDate;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

}
