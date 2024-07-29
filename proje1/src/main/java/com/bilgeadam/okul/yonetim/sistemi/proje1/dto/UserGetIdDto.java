package com.bilgeadam.okul.yonetim.sistemi.proje1.dto;

import java.time.LocalDate;

import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Role;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.UserEntity;

import jakarta.persistence.ManyToOne;



public class UserGetIdDto {

	public UserGetIdDto() {
		// TODO Auto-generated constructor stub
	}

	private Long id;
	private String name;
	private String lName;
	private String EMAIL;
	private LocalDate bDate;
	private Role rol;
	@ManyToOne
	private UserEntity insertedUser;
	@ManyToOne
	private UserEntity lastUpdateUser;
	public UserGetIdDto(Long id, String name, String lName, String eMAIL, LocalDate bDate, Role rol,
			UserEntity insertedUser, UserEntity lastUpdateUser) {
		super();
		this.id = id;
		this.name = name;
		this.lName = lName;
		EMAIL = eMAIL;
		this.bDate = bDate;
		this.rol = rol;
		this.insertedUser = insertedUser;
		this.lastUpdateUser = lastUpdateUser;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public LocalDate getbDate() {
		return bDate;
	}
	public void setbDate(LocalDate bDate) {
		this.bDate = bDate;
	}
	public Role getRol() {
		return rol;
	}
	public void setRol(Role rol) {
		this.rol = rol;
	}
	public UserEntity getInsertedUser() {
		return insertedUser;
	}
	public void setInsertedUser(UserEntity insertedUser) {
		this.insertedUser = insertedUser;
	}
	public UserEntity getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(UserEntity lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	




	
}
