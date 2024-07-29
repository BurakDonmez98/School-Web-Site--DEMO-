package com.bilgeadam.okul.yonetim.sistemi.proje1.api;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.OgrenciDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Ogrenci;
import com.bilgeadam.okul.yonetim.sistemi.proje1.repo.OgrenciRepo;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.impl.OgrenciService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/ogrenci")
public class OgrenciController {
 
	private final OgrenciService ogrenciService;
    private final ModelMapper modelMapper;

    public OgrenciController(OgrenciService ogrenciService, ModelMapper modelMapper) {
        this.ogrenciService = ogrenciService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/ogrencilistesi")
    public String getOgrenciListesi(
            @RequestParam(name = "sayfaSayisi", required = false, defaultValue = "0") int sayfaSayisi,
            @RequestParam(name = "kayitSayisi", required = false, defaultValue = "10") int kayitSayisi,
            @RequestParam(name = "sortBy", required = false, defaultValue = "ad") String sortBy,
            @RequestParam(name = "direction", required = false, defaultValue = "asc") String direction,
            @RequestParam(name = "keyword", required = false) String keyword,
            Model model) {

        Page<Ogrenci> ogrenciPage = ogrenciService.getPage(sayfaSayisi, kayitSayisi, sortBy, direction, keyword);
        int totalPages = ogrenciPage.getTotalPages();
        int currentPage = ogrenciPage.getNumber();
        long totalItems = ogrenciPage.getTotalElements();

        model.addAttribute("ogrenciPage", ogrenciPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);
        model.addAttribute("keyword", keyword);

        return "ogrenci/ogrencilistesi";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/ogrenciekle")
    public String yeniOgrenciFormu(Model model) {
        model.addAttribute("ogrenci", new OgrenciDto());
        return "ogrenci/ogrenciekle";
    }

    @PostMapping("/ogrenciekle")
    public String addOgrenci(@Valid @ModelAttribute("ogrenci") OgrenciDto ogrenciDto,
                             BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "ogrenci/ogrenciekle"; // Hatalı validasyon durumunda doğru template ismini döndür
        }

        // Call service to add the student
        ogrenciDto.setEnabled(true);
        ogrenciService.add(ogrenciDto);

        // Redirect to list page after successful addition
        redirectAttributes.addFlashAttribute("success", "Öğrenci başarıyla eklendi!");
        return "redirect:/ogrenci/ogrencilistesi";
    }

    @GetMapping("/pasifogrencilistesi")
    public String pasifOgrenciListesi(
            @RequestParam(value = "sayfaSayisi", defaultValue = "0") int sayfaSayisi,
            @RequestParam(value = "kayitSayisi", defaultValue = "10") int kayitSayisi,
            @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            Model model) {

        Page<Ogrenci> ogrenciPage = ogrenciService.getPage(sayfaSayisi, kayitSayisi, sortBy, direction);

        model.addAttribute("ogrenciPage", ogrenciPage);
        model.addAttribute("currentPage", sayfaSayisi);
        model.addAttribute("totalPages", ogrenciPage.getTotalPages());
        model.addAttribute("totalItems", ogrenciPage.getTotalElements());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("direction", direction);

        return "ogrenci/pasifogrencilistesi";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/ogrencisil/{id}")
    public String ogrenciSil(@PathVariable long id, RedirectAttributes redirectAttributes) {
        OgrenciDto silinecekOgrenci = ogrenciService.getById(id);
        if (silinecekOgrenci == null) {
            redirectAttributes.addFlashAttribute("error", "Öğrenci bulunamadı!");
            return "redirect:/ogrencilistesi";
        }

        ogrenciService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Öğrenci başarıyla silindi!");
        return "redirect:/ogrenci/ogrencilistesi";
    }

    @GetMapping("/ogrenciaktifet/{id}")
    public String ogrenciAktifEt(@PathVariable long id, RedirectAttributes redirectAttributes) {
        OgrenciDto aktifEdilecekOgrenci = ogrenciService.getById(id);
        if (aktifEdilecekOgrenci == null) {
            redirectAttributes.addFlashAttribute("error", "Öğrenci bulunamadı!");
            return "redirect:/ogrencilistesi";
        }

        ogrenciService.merge(id, true);
        redirectAttributes.addFlashAttribute("success", "Öğrenci başarıyla aktifleştirildi!");
        return "redirect:/ogrenci/ogrencilistesi";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/ogrenciduzenle/{id}")
    public String ogrenciDuzenleForm(@PathVariable long id, Model model) {
        OgrenciDto ogrenciDto = ogrenciService.getById(id);
        if (ogrenciDto == null) {
            return "redirect:/hata";
        }

        model.addAttribute("ogrenci", ogrenciDto);
        return "ogrenci/ogrenciduzenle"; // Template ismini doğru olarak döndür
    }

    @PostMapping("/ogrenciduzenle/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String ogrenciDuzenleForm(@PathVariable long id,
                                     @RequestParam(value = "enabled", required = false) boolean enabled,
                                     @Valid @ModelAttribute("ogrenci") OgrenciDto ogrenciDto,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "ogrenci/ogrenciduzenle"; // Hatalı validasyon durumunda doğru template ismini döndür
        }

        // Öğrenci verisini güncelle
        ogrenciDto.setId(id);
        ogrenciDto.setEnabled(enabled); // Enabled durumunu da güncellemek istiyorsanız ekleme yapabilirsiniz.

        ogrenciService.merge(ogrenciDto);

        return "redirect:/ogrenci/ogrencilistesi"; // Başarılı güncelleme durumunda doğru yönlendirme yap
    }
	
}
