package com.bilgeadam.okul.yonetim.sistemi.proje1.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;


public class UserSaveDto implements Serializable {


	public void setVersion(Timestamp version) {
		this.version = version;
	}

	public UserSaveDto() {
		// TODO Auto-generated constructor stub
	}

	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String lName;
	@NotEmpty
	@Email
	private String EMAIL;
	@Past
	@NotNull
	private LocalDate bDate;
	@Enumerated(EnumType.STRING)
	private Role rol;
	@NotEmpty
	private String PASSWORD;
	@Version
	private Timestamp version;
	private boolean enabled;

	



	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	

	

	public UserSaveDto(Long id, @NotEmpty String name, @NotEmpty String lName, @NotEmpty @Email String eMAIL,
			@Past @NotNull LocalDate bDate, Role rol, @NotEmpty String pASSWORD, Timestamp version, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.lName = lName;
		EMAIL = eMAIL;
		this.bDate = bDate;
		this.rol = rol;
		PASSWORD = pASSWORD;
		this.version = version;
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

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String PASSWORD) {
		this.PASSWORD = PASSWORD;
	}

	public Timestamp getVersion() {
		return version;
	}

	



}
