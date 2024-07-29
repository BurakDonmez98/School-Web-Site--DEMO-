package com.bilgeadam.okul.yonetim.sistemi.proje1.api;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bilgeadam.okul.yonetim.sistemi.proje1.service.impl.SearchService;

@Controller
public class SearchController {

    private final SearchService searchService;

    
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("query") String query) {
        try {
            // SearchService üzerinden arama yapılır ve sonuçlar alınır
            Map<String, String> results = searchService.search(query);

            // HTML içeriği response olarak döndürülür
            String htmlResponse = "<html><body>" +
                                  "<h2>Kayıtlı Hesaplar</h2>" + results.get("user") +
                                  "<h2>Kayıtlı Öğretmenler</h2>" + results.get("ogretmen") +
                                  "<h2>Kayıtlı Öğrenciler</h2>" + results.get("ogrenci") +
                                  "</body></html>";

            return ResponseEntity.ok(htmlResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Arama işlemi sırasında bir hata oluştu: " + e.getMessage());
        }
    }
}