package com.bilgeadam.okul.yonetim.sistemi.proje1.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;


@Entity
@Table(name="users")
public class UserEntity implements Serializable , UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	


	public static long getSerialversionuid() {
		return serialVersionUID;
	}











	public UserEntity() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@NotEmpty
	private String password;
	@Version
	private Timestamp version;
	@ManyToOne
	 @JsonIgnore
	private UserEntity insertedUser;
	@ManyToOne
	 @JsonIgnore
	private UserEntity lastUpdateUser;
	private boolean enabled;
	private String newPassword;



	public UserEntity(Long id, @NotEmpty String name, @NotEmpty String lName, @NotEmpty @Email String email,
			@Past LocalDate bDate, Role rol, @NotEmpty String password, Timestamp version, UserEntity insertedUser,
			UserEntity lastUpdateUser, boolean enabled, String newPassword, String confirmNewPassword) {
		super();
		this.id = id;
		this.name = name;
		this.lName = lName;
		this.email = email;
		this.bDate = bDate;
		this.rol = rol;
		this.password = password;
		this.version = version;
		this.insertedUser = insertedUser;
		this.lastUpdateUser = lastUpdateUser;
		this.enabled = enabled;
		this.newPassword = newPassword;
	}















	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
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





	
	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public Timestamp getVersion() {
		return version;
	}





	public void setVersion(Timestamp version) {
		this.version = version;
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





	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}





	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}





	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}





	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}






	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority(rol.name()));

		return authorities;
	}
	
	

	
}


