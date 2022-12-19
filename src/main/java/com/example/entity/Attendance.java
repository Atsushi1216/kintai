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
	private String userId;

	@Column(name = "start_time", insertable = false, updatable = true)
	private Timestamp startTime;

	@Column(name = "end_time", insertable = false, updatable = true)
	private Timestamp endTime;

	@Column(name = "rest_start_time", insertable = false, updatable = true)
	private Timestamp restStartTime;

	@Column(name = "rest_end_time", insertable = false, updatable = true)
	private Timestamp restEndTime;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
