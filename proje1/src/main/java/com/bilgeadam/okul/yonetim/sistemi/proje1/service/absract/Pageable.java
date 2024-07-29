package com.bilgeadam.okul.yonetim.sistemi.proje1.service.absract;

import org.springframework.data.domain.Page;

import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.OgretmenDto;

public interface Pageable<T> {
	Page<T> getPage(int sayfaSayisi, int kayitSayisi, String sortBy, String direction);

	Page<T> getPage(int sayfaSayisi, int kayitSayisi, String sortBy, String direction, String keyword);


}

