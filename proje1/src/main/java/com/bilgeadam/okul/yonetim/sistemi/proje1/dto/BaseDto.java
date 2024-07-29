package com.bilgeadam.okul.yonetim.sistemi.proje1.dto;

public abstract class BaseDto<T> {
    protected T id;

    public BaseDto(T id) {
        this.id = id;
    }


	public T getId() {
		return id;
	}
	public void setId(T id) {
		this.id = id;
	}

	public BaseDto(T id, boolean isActive) {
		super();
		this.id = id;
		
	}

}
