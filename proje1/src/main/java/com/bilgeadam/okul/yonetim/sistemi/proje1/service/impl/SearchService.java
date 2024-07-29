package com.bilgeadam.okul.yonetim.sistemi.proje1.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Ogrenci;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Ogretmen;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.UserEntity;
import com.bilgeadam.okul.yonetim.sistemi.proje1.repo.OgrenciRepo;
import com.bilgeadam.okul.yonetim.sistemi.proje1.repo.OgretmenRepo;
import com.bilgeadam.okul.yonetim.sistemi.proje1.repo.UserRepository;

@Service
public class SearchService {

	 private final UserRepository userRepository;
	    private final OgretmenRepo teacherRepository;
	    private final OgrenciRepo studentRepository;

	 
	    public SearchService(UserRepository userRepository, OgretmenRepo teacherRepository, OgrenciRepo studentRepository) {
	        this.userRepository = userRepository;
	        this.teacherRepository = teacherRepository;
	        this.studentRepository = studentRepository;
	    }

	    public Map<String, String> search(String query) {
	        List<UserEntity> users = userRepository.findByNameContainingIgnoreCase(query);
	        List<Ogretmen> ogretmenler = teacherRepository.findByAdContainingIgnoreCase(query);
	        List<Ogrenci> ogrenciler = studentRepository.findByAdContainingIgnoreCase(query);

	        // HTML içeriği oluştur
	        String userHtml = buildHtmlList(users);
	        String ogretmenHtml = buildHtmlList(ogretmenler);
	        String ogrenciHtml = buildHtmlList(ogrenciler);

	        // Sonuçları bir Map içinde döndür
	        Map<String, String> results = new HashMap<>();
	        results.put("user", userHtml);
	        results.put("ogretmen", ogretmenHtml);
	        results.put("ogrenci", ogrenciHtml);

	        return results;
	    }

	    // Her bir entity için HTML listesi oluşturan yardımcı metod
	    private <T> String buildHtmlList(List<T> items) {
	        StringBuilder sb = new StringBuilder("<ul>");
	        for (T item : items) {
	            sb.append("<li>");
	            if (item instanceof UserEntity) {
	                UserEntity user = (UserEntity) item;
	                sb.append("User: ").append(user.getName()).append(",<br>")
	                .append("User Soyadı: ").append(user.getlName()).append(",<br>")
	                .append("User Email: ").append(user.getEmail()).append("<br>");
	            } else if (item instanceof Ogretmen) {
	                Ogretmen ogretmen = (Ogretmen) item;
	                sb.append("Öğretmen: ")
	                .append("Adı: ").append(ogretmen.getAd()).append(",<br>")
	                .append("Soyadı: ").append(ogretmen.getSoyad()).append(",<br>")
	                .append("Branş: ").append(ogretmen.getBrans()).append("<br>");

	            } else if (item instanceof Ogrenci) {
	                Ogrenci ogrenci = (Ogrenci) item;
	                sb.append("Öğrenci: ")
	                .append("Adı: ").append(ogrenci.getAd()).append(",<br>")
	                .append("Soyadı: ").append(ogrenci.getSoyad()).append(",<br>")
	                .append("Ogrenci No: ").append(ogrenci.getOgrenciNo()).append("<br>");
	            } else {
	                sb.append(item.toString());
	            }
	            sb.append("</li>");
	        }
	        sb.append("</ul>");
	        return sb.toString();
	    }
}
