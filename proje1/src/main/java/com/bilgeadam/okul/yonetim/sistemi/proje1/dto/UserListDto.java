package com.bilgeadam.okul.yonetim.sistemi.proje1.dto;

import java.time.LocalDate;

import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Role;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.UserEntity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;



public class UserListDto {

	private Long id;
	private String name;
	private String lName;
	private String email;
	private LocalDate bDate;
	private Role rol;
	@ManyToOne
	private UserEntity insertedUser;
	@ManyToOne
	private UserEntity lastUpdateUser;
	private boolean enabled;
	
	

	
	public UserListDto(Long id, String name, String lName, String email, LocalDate bDate, Role rol,
			UserEntity insertedUser, UserEntity lastUpdateUser, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.lName = lName;
		this.email = email;
		this.bDate = bDate;
		this.rol = rol;
		this.insertedUser = insertedUser;
		this.lastUpdateUser = lastUpdateUser;
		this.enabled = enabled;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	


}
