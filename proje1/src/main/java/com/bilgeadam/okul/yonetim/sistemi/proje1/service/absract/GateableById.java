package com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract;

import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.OgretmenDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Ogretmen;

public interface GateableById<T> {
	T getById(long id);

	
	
}
