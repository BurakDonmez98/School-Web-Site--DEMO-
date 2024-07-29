package com.bilgeadam.okul.yonetim.sistemi.proje1.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Ogrenci;
@Repository
public interface OgrenciRepo extends JpaRepository<Ogrenci, Long> {

	List<Ogrenci> findByAdContainingIgnoreCase(String ad);	

	  @Query("SELECT o FROM Ogrenci o WHERE o.ad LIKE %:keyword% OR o.soyad LIKE %:keyword%")
	    Page<Ogrenci> findByKeyword(String keyword, Pageable pageable);


}
