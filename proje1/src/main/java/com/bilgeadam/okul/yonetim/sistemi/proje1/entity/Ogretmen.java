package com.bilgeadam.okul.yonetim.sistemi.proje1.entity;



import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ogretmenler")
public class Ogretmen extends Kisi<Long> implements Serializable, UserDetails{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String brans;
	private int ogretmenKodu;
	@Min(value = 0, message = "Not 0'dan küçük olamaz")
    @Max(value = 100, message = "Not 100'den büyük olamaz")
	@NotNull(message = "Not bilgisi boş olamaz")
	private byte ogretmenNotu;
	@ManyToOne
	private UserEntity insertedUser;
	@ManyToOne
	private UserEntity lastUpdateUser;
private boolean enabled;

	
	
	public Ogretmen() {
		// TODO Auto-generated constructor stub
	}


	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}







	public Ogretmen(@Size(min = 3, max = 30) @NotBlank(message = "Ad boş olamaz") String ad,
			@Size(min = 2, max = 30) @NotBlank(message = "Soyad numarası boş olamaz") String soyad,
			@NotNull(message = "Doğum Tarihi boş olamaz") @Past(message = "Doğum tarihi geçmiş zaman olmalı") LocalDate dogumTarihi,
			@Size(min = 11, max = 11) @NotBlank(message = "Tc Kimlik numarası boş olamaz") String tcKimlikNo,
			@Size(min = 11, max = 11) @NotBlank(message = "Telefon numarası boş olamaz") String telNo, String brans,
			int ogretmenKodu,
			@Min(value = 0, message = "Not 0'dan küçük olamaz") @Max(value = 100, message = "Not 100'den büyük olamaz") @NotNull(message = "Not bilgisi boş olamaz") byte ogretmenNotu,
			UserEntity insertedUser, UserEntity lastUpdateUser, boolean enabled) {
		super(ad, soyad, dogumTarihi, tcKimlikNo, telNo);
		this.brans = brans;
		this.ogretmenKodu = ogretmenKodu;
		this.ogretmenNotu = ogretmenNotu;
		this.insertedUser = insertedUser;
		this.lastUpdateUser = lastUpdateUser;
		this.enabled = enabled;
		
	}






	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getBrans() {
		return brans;
	}


	public void setBrans(String brans) {
		this.brans = brans;
	}


	public int getOgretmenKodu() {
		return ogretmenKodu;
	}


	public void setOgretmenKodu(int ogretmenKodu) {
		this.ogretmenKodu = ogretmenKodu;
	}


	public byte getOgretmenNotu() {
		return ogretmenNotu;
	}


	public void setOgretmenNotu(byte ogretmenNotu) {
		this.ogretmenNotu = ogretmenNotu;
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







	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
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


	

	
}
