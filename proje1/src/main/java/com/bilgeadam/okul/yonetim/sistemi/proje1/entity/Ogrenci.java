package com.bilgeadam.okul.yonetim.sistemi.proje1.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "ogrenciler")
public class Ogrenci extends Kisi<Long> implements Serializable{
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	    private Long id;
	@NotNull
	private int ogrenciNo;
	@ManyToOne
	private UserEntity insertedUser;
	@ManyToOne
	private UserEntity lastUpdateUser;
private boolean enabled;






public Ogrenci(@Size(min = 3, max = 30) @NotBlank(message = "Ad boş olamaz") String ad,
		@Size(min = 2, max = 30) @NotBlank(message = "Soyad numarası boş olamaz") String soyad,
		@NotNull(message = "Doğum Tarihi boş olamaz") @Past(message = "Doğum tarihi geçmiş zaman olmalı") LocalDate dogumTarihi,
		@Size(min = 11, max = 11) @NotBlank(message = "Tc Kimlik numarası boş olamaz") String tcKimlikNo,
		@Size(min = 11, max = 11) @NotBlank(message = "Telefon numarası boş olamaz") String telNo, Long id,
		@NotNull int ogrenciNo, UserEntity insertedUser, UserEntity lastUpdateUser, boolean enabled) {
	super(ad, soyad, dogumTarihi, tcKimlikNo, telNo);
	this.id = id;
	this.ogrenciNo = ogrenciNo;
	this.insertedUser = insertedUser;
	this.lastUpdateUser = lastUpdateUser;
	this.enabled = enabled;
}


public Ogrenci() {
	// TODO Auto-generated constructor stub
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public int getOgrenciNo() {
	return ogrenciNo;
}


public void setOgrenciNo(int ogrenciNo) {
	this.ogrenciNo = ogrenciNo;
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



public void add(Ogrenci ogrenci) {
	// TODO Auto-generated method stub
	
}















	
}
