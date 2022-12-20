package com.example.model;
import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
/**
 * ユーザー情報
 */
@Data
public class AttendanceData implements Serializable {

	private int id;

	private int userId;

	private int categoryId;

	private String content;

	private String startDate;

	private String endDate;

	private String restStartDate;

	private String restEndDate;

	private Timestamp borderDate;

	private Timestamp updatedDate;

	private Timestamp createdDate;
}
