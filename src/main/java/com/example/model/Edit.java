package com.example.model;
import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Edit {



	private int id;


	@NotBlank(message="名前を入力してください")
	@Size(max=20,message="名前は20文字以内で入力してください")
	private String name;


	@NotBlank(message="アカウントを入力してください")
	@Size(max=20,message="アカウントは20文字以内で入力してください")
	private String account;




	private String password;

	@Pattern(regexp="^[1-9]$", message="年齢を選択してください")
	private String age;

	@Pattern(regexp="^[1-9]$", message="性別を選択してください")
	private String gender;



	private Timestamp createdDate;


	private Timestamp updatedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}



}





















/*
package com.example.demo.model;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SignUp {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@NotBlank
	@Size(max=20)
	private String name;

	@Column
	@NotBlank
	@Size(max=20)
	private String account;

	@Column
	@NotBlank
	@Min(6)
	@Max(20)
	@Pattern(regexp="(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-z0-9]{6,20}")
	private String password;

	@Column
	@NotBlank(message = "Users.age.NotBlank")
	private int age;

	@Column
	@NotBlank(message= "Users.gender.NotBlank")
	private int gender;

	@Column(name = "created_date", insertable = false, updatable = false)
	private Timestamp createdDate;

	@Column(name = "updated_date", insertable = false, updatable = true)
	private Timestamp updatedDate;















}
*/
