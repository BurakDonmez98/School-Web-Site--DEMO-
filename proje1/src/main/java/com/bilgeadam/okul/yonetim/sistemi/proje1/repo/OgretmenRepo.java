package com.bilgeadam.okul.yonetim.sistemi.proje1.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Ogretmen;

public interface OgretmenRepo extends JpaRepository<Ogretmen , Long>{

	void deleteById(long id);

	


	List<Ogretmen> findByEnabledAndId(boolean enabled, Long id);

	List<Ogretmen> findByAdContainingIgnoreCase(String ad);



	  @Query("SELECT o FROM Ogrenci o WHERE o.ad LIKE %:keyword% OR o.soyad LIKE %:keyword%")
	    Page<Ogretmen> findByKeyword(String keyword, Pageable pageable);

	


}
