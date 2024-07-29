package com.bilgeadam.okul.yonetim.sistemi.proje1.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public class BaseEntity<T extends Number> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private T id; 
	private LocalDateTime insertedDateTime;
	private LocalDateTime lastUpdateDateTime;
	public T getId() {
		return id;
	}
	public void setId(T id) {
		this.id = id;
	}
	public LocalDateTime getInsertedDateTime() {
		return insertedDateTime;
	}
	public void setInsertedDateTime(LocalDateTime insertedDateTime) {
		this.insertedDateTime = insertedDateTime;
	}
	public LocalDateTime getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}
	public void setLastUpdateDateTime(LocalDateTime lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
