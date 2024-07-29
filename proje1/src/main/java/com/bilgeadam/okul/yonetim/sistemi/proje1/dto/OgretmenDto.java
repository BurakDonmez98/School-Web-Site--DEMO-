package com.bilgeadam.okul.yonetim.sistemi.proje1.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class OgretmenDto extends BaseDto<Long> implements Serializable  {
 public OgretmenDto() {
	// TODO Auto-generated constructor stub
	 super(null);
}

	@Size(min = 3, max = 30)
	@NotBlank(message = "Ad boş olamaz")
	private String ad;
	@Size(min = 2, max = 30)
	@NotBlank(message = "Soyad numarası boş olamaz")
	private String soyad;
	@NotNull(message = "Doğum Tarihi boş olamaz")
	@Past(message = "Doğum Tarihi geçmiş bir tarih olmalıdır")
	private LocalDate dogumTarihi;
	@Size(min = 11, max = 11)
	@NotBlank(message = "Tc Kimlik numarası boş olamaz")
	private String tcKimlikNo;
	@Size(min = 11, max = 11)
	@NotBlank(message = "Telefon numarası boş olamaz")
	private String telNo;
	private String brans;
	private int ogretmenKodu;
	@Min(value = 0, message = "Not 0'dan küçük olamaz")
    @Max(value = 100, message = "Not 100'den büyük olamaz")
	@NotNull(message = "Not bilgisi boş olamaz")
	private byte ogretmenNotu;
	private boolean enabled;
	
	

	public OgretmenDto(Long id, @Size(min = 3, max = 30) @NotBlank(message = "Ad boş olamaz") String ad,
			@Size(min = 2, max = 30) @NotBlank(message = "Soyad numarası boş olamaz") String soyad,
			@NotNull(message = "Doğum Tarihi boş olamaz") @Past(message = "Doğum Tarihi geçmiş bir tarih olmalıdır") LocalDate dogumTarihi,
			@Size(min = 11, max = 11) @NotBlank(message = "Tc Kimlik numarası boş olamaz") String tcKimlikNo,
			@Size(min = 11, max = 11) @NotBlank(message = "Telefon numarası boş olamaz") String telNo, String brans,
			int ogretmenKodu,
			@Min(value = 0, message = "Not 0'dan küçük olamaz") @Max(value = 100, message = "Not 100'den büyük olamaz") @NotNull(message = "Not bilgisi boş olamaz") byte ogretmenNotu,
			boolean enabled) {
		super(id);
		this.ad = ad;
		this.soyad = soyad;
		this.dogumTarihi = dogumTarihi;
		this.tcKimlikNo = tcKimlikNo;
		this.telNo = telNo;
		this.brans = brans;
		this.ogretmenKodu = ogretmenKodu;
		this.ogretmenNotu = ogretmenNotu;
		this.enabled = enabled;
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
	
	
}
