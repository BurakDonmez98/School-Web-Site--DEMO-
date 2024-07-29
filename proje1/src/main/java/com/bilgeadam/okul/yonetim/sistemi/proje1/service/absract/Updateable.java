package com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract;

import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.OgrenciDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.OgretmenDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.UserEntity;

public interface Updateable<T> {
	boolean merge(T obj, long id);



	

	UserEntity getCurrentUser();






	
	
}
