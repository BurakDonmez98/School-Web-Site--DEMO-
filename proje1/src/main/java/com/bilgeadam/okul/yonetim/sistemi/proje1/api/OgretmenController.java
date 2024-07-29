package com.bilgeadam.okul.yonetim.sistemi.proje1.api;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.OgretmenDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Ogretmen;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.impl.OgretmenService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ogretmen")
public class OgretmenController {
	static final String NAME = "name";
	private final OgretmenService ogretmenService;
	



	public OgretmenController(OgretmenService ogretmenService) {
		super();
		this.ogretmenService = ogretmenService;
		
		
	}
	@GetMapping("/ogretmenlistesi")
	public String ogretmenListesi(
	        @RequestParam(value = "sayfaSayisi", defaultValue = "0") int sayfaSayisi,
	        @RequestParam(value = "kayitSayisi", defaultValue = "10") int kayitSayisi,
	        @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
	        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
	        Model model) {

	    Page<Ogretmen> ogretmenPage = ogretmenService.getPage(sayfaSayisi, kayitSayisi, sortBy, direction);

	    model.addAttribute("ogretmenPage", ogretmenPage);
	    model.addAttribute("currentPage", sayfaSayisi);
	    model.addAttribute("totalPages", ogretmenPage.getTotalPages());
	    model.addAttribute("totalItems", ogretmenPage.getTotalElements());
	    model.addAttribute("sortBy", sortBy);
	    model.addAttribute("direction", direction);

	    return "ogretmen/ogretmenlistesi";
	}



	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/ogretmenduzenle/{id}")
	public String ogretmenGuncelleForm(@PathVariable long id, Model model) {
	    Ogretmen ogretmenDto = ogretmenService.getById(id);
	    model.addAttribute("ogretmen", ogretmenDto);
	    return "ogretmen/ogretmenduzenle";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/ogretmenduzenle/{id}")
	public String ogretmenGuncelle(@PathVariable long id,
	                               @RequestParam(value = "enabled", required = false) boolean enabled,
	                               @Valid @ModelAttribute("ogretmen") OgretmenDto ogretmenDto,
	                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
	    if (bindingResult.hasErrors()) {
	        return "ogretmen/ogretmenduzenle";
	    }

	    ogretmenDto.setId(id); // Set the ID to ensure correct entity update
	    ogretmenService.merge(ogretmenDto);

	    return "redirect:/ogretmen/ogretmenlistesi";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/ogretmenekle")
	public String yeniOgretmenFormu(Model model) {
	    model.addAttribute("ogretmen", new OgretmenDto());
	    return "ogretmen/ogretmenekle";
	}

	@PostMapping("/ogretmenekle")
	public String yeniOgretmenEkle(@Valid @ModelAttribute("ogretmen") OgretmenDto ogretmenDto,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			// Doğrulama hatası varsa, form sayfasına geri dön
			return "ogretmen/ogretmenekle";
		}
		// enabled durumunu true olarak ayarla ve ogretmeni kaydet
		ogretmenService.add(ogretmenDto);
		return "redirect:/ogretmen/ogretmenlistesi";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/ogretmensil/{id}")
	public String ogretmenSil(@PathVariable long id, RedirectAttributes redirectAttributes) {
		Ogretmen silinecekOgretmen = ogretmenService.getById(id);
		if (silinecekOgretmen == null) {
			// Öğretmen bulunamazsa hata mesajı ekleyip yönlendirme yap
			redirectAttributes.addFlashAttribute("error", "Öğretmen bulunamadı!");
			return "redirect:/ogretmenlistesi";
		}
		// Veritabanından öğretmen sil
		ogretmenService.delete(id);

		// Başarı mesajı ekleyip yönlendirme yap
		redirectAttributes.addFlashAttribute("success", "Öğretmen başarıyla silindi!");
		return "redirect:/ogretmen/ogretmenlistesi";

	}

	@GetMapping("/pasifogretmenlistesi")
	public String pasifOgrenciListesi(
	        @RequestParam(value = "sayfaSayisi", defaultValue = "0") int sayfaSayisi,
	        @RequestParam(value = "kayitSayisi", defaultValue = "10") int kayitSayisi,
	        @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
	        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
	        Model model) {

	    Page<Ogretmen> ogretmenPage = ogretmenService.getPage(sayfaSayisi, kayitSayisi, sortBy, direction);

	    model.addAttribute("ogretmenPage", ogretmenPage); // Correct attribute name: ogretmenPage

	    model.addAttribute("currentPage", sayfaSayisi);
	    model.addAttribute("totalPages", ogretmenPage.getTotalPages());
	    model.addAttribute("totalItems", ogretmenPage.getTotalElements());
	    model.addAttribute("sortBy", sortBy);
	    model.addAttribute("direction", direction);

	    return "ogretmen/pasifogretmenlistesi";
	}
	@GetMapping("/ogretmenaktifet/{id}")
	public String ogretmenAktifEt (@PathVariable long id, RedirectAttributes redirectAttributes) {
		Ogretmen aktifEdilecekOgretmen = ogretmenService.getById(id);
		if (aktifEdilecekOgretmen == null) {
			// Öğretmen bulunamazsa hata mesajı ekleyip yönlendirme yap
			redirectAttributes.addFlashAttribute("error", "Öğretmen bulunamadı!");
			return "redirect:/pasifogretmenlistesi";
		}
		// Veritabanından öğretmen sil
		ogretmenService.ogretmenAktifEt(id);

		// Başarı mesajı ekleyip yönlendirme yap
		redirectAttributes.addFlashAttribute("success", "Öğretmen başarıyla silindi!");
		return "redirect:/ogretmen/pasifogretmenlistesi";
	}
}
