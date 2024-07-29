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
import jakarta.validation.constraints.Past;



public class UserUpdateDto implements Serializable {





	public UserUpdateDto() {
		// TODO Auto-generated constructor stub
	}
	
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String lName;
	@NotEmpty
	@Email
	private String email;
	@Past
	private LocalDate bDate;
	@Enumerated(EnumType.STRING)
	private Role rol;
	@Version
	private Timestamp version;
	private boolean enabled;
	private String password;
	    private String newPassword;
	   
	




	

	









	public Timestamp getVersion() {
			return version;
		}









	public UserUpdateDto(Long id, @NotEmpty String name, @NotEmpty String lName, @NotEmpty @Email String email,
				@Past LocalDate bDate, Role rol, Timestamp version, boolean enabled, String password,
				 String newPassword, String confirmNewPassword) {
			super();
			this.id = id;
			this.name = name;
			this.lName = lName;
			this.email = email;
			this.bDate = bDate;
			this.rol = rol;
			this.version = version;
			this.enabled = enabled;
			this.password = password;
			
			this.newPassword = newPassword;
			
		}

	
	



	


	public String getNewPassword() {
		return newPassword;
	}



	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}








	public boolean isEnable() {
		return enabled;
	}

	public void setEnable(boolean enable) {
		this.enabled = enable;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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


	public void setVersion(Timestamp version) {
		this.version = version;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}





}
