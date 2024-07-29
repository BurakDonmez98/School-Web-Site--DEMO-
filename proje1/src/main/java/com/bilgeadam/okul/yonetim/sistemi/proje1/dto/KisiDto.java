package com.bilgeadam.okul.yonetim.sistemi.proje1.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class KisiDto<T extends Number> extends BaseDto<T> {

	

	@Size(min = 3, max = 30)
	@NotBlank(message = "Ad boş olamaz")
	private String ad;
	@Size(min = 2, max = 30)
	@NotBlank(message = "Soyad numarası boş olamaz")
	private String soyad;
	@NotNull(message = "Doğum Tarihi boş olamaz")
	@Past(message = "Dogum Tarihi geçmiş tarih olmalıdır")
	private LocalDate dogumTarihi;
	@Size(min = 11, max = 11)
	@NotBlank(message = "Tc Kimlik numarası boş olamaz")
	private String tcKimlikNo;
	@Size(min = 11, max = 11)
	@NotBlank(message = "Telefon numarası boş olamaz")
	private String telNo;




	

	public KisiDto(T id, @Size(min = 3, max = 30) @NotBlank(message = "Ad boş olamaz") String ad,
			@Size(min = 2, max = 30) @NotBlank(message = "Soyad numarası boş olamaz") String soyad,
			@NotNull(message = "Doğum Tarihi boş olamaz") @Past(message = "Dogum Tarihi geçmiş tarih olmalıdır") LocalDate dogumTarihi,
			@Size(min = 11, max = 11) @NotBlank(message = "Tc Kimlik numarası boş olamaz") String tcKimlikNo,
			@Size(min = 11, max = 11) @NotBlank(message = "Telefon numarası boş olamaz") String telNo) {
		super(id);
		this.ad = ad;
		this.soyad = soyad;
		this.dogumTarihi = dogumTarihi;
		this.tcKimlikNo = tcKimlikNo;
		this.telNo = telNo;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}


	public LocalDate getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(LocalDate dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}

	public String getTcKimlikNo() {
		return tcKimlikNo;
	}

	public void setTcKimlikNo(String tcKimlikNo) {
		this.tcKimlikNo = tcKimlikNo;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	
	
	

	
	
}
