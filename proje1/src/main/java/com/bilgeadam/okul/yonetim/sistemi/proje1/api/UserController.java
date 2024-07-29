package com.bilgeadam.okul.yonetim.sistemi.proje1.api;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.UserListDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.UserSaveDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.UserUpdateDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Role;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.UserEntity;
import com.bilgeadam.okul.yonetim.sistemi.proje1.repo.UserRepository;
import com.bilgeadam.okul.yonetim.sistemi.proje1.service.impl.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	private final PasswordEncoder passwordEncoder;

	private UserRepository userRepository;
	private ModelMapper modelMapper;
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	public UserController(UserService userService, ModelMapper modelMapper, UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;

	}

	@GetMapping("/hesabim")
	public String hesabim(Model model) {

		List<UserListDto> list = userService.getList();

		model.addAttribute("users", list);

		return "user/hesabim";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/userlistesi")
	public String list(Model model) {

		List<UserListDto> list = userService.getList();

		model.addAttribute("users", list);

		return "user/userlistesi";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/userekle")
	public String yeniKullaniciEkle(Model model) {

		model.addAttribute("user", new UserSaveDto());

		return "user/userekle";
	}

	
	@PostMapping("/userekle")
	public String yeniKullaniciEkle(@Valid @ModelAttribute("user") UserSaveDto user, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			return "/user/userekle";
		}

		user.setEnabled(true);
		userService.add(user);

		return "redirect:/user/userlistesi";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/userduzenle/{id}")
	public String userDuzenle(@PathVariable Long id, Model model) {
		Optional<UserEntity> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new RuntimeException("Kullanıcı bulunamadı");
		}

		UserEntity userEntity = userOptional.get();
		UserUpdateDto userUpdateDto = modelMapper.map(userEntity, UserUpdateDto.class);
		model.addAttribute("user", userUpdateDto);

		return "user/userduzenle";
	}

	@PostMapping("/userduzenle/{id}")
	public String updateUser(@PathVariable long id,
	        @ModelAttribute("user") @Valid UserUpdateDto userUpdateDto,
	        BindingResult bindingResult, Model model) {

	    if (bindingResult.hasErrors()) {
	        logger.error("Kullanıcı güncelleme işlemi sırasında hata oluştu: " + bindingResult.getAllErrors());
	        return "user/userduzenle"; // Hatalı validasyon durumunda doğru template ismini döndür
	    }

	    // Kullanıcı nesnesini veritabanından çek
	    Optional<UserEntity> userOptional = userRepository.findById(id);
	    if (!userOptional.isPresent()) {
	        throw new RuntimeException("Kullanıcı bulunamadı");
	    }

	    UserEntity updateUser = userOptional.get();

	    // Şifre alanı boş bırakıldıysa, eski şifreyi koru
	    if (userUpdateDto.getPassword() == null || userUpdateDto.getPassword().isEmpty()) {
	        userUpdateDto.setPassword(updateUser.getPassword());
	    } else {
	        // Yeni şifreleri kodla
	        String encodedPassword = passwordEncoder.encode(userUpdateDto.getPassword());
	        userUpdateDto.setPassword(encodedPassword);
	    }

	    // ModelMapper ile alanları kopyala
	    modelMapper.map(userUpdateDto, updateUser);

	    // Kullanıcıyı güncelle
	    userRepository.save(updateUser);

	    return "redirect:/user/userlistesi"; // Başarılı güncelleme durumunda doğru yönlendirme yap
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/pasifuserlistesi")
	public String pasifuserlistesi(Model model) {

		List<UserListDto> list = userService.getList();

		model.addAttribute("users", list);

		return "user/pasifuserlistesi";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/usersil/{id}")
	public String usersil(@PathVariable long id, RedirectAttributes redirectAttributes) {
		UserEntity silinecekUser = userService.getById(id);
		if (silinecekUser == null) {
			// Öğretmen bulunamazsa hata mesajı ekleyip yönlendirme yap
			redirectAttributes.addFlashAttribute("error", "Öğretmen bulunamadı!");
			return "redirect:/ogretmenlistesi";
		}
		// Veritabanından öğretmen sil
		userService.delete(id);

		// Başarı mesajı ekleyip yönlendirme yap
		redirectAttributes.addFlashAttribute("success", "Öğretmen başarıyla silindi!");
		return "redirect:/user/userlistesi";

	}

	@GetMapping("/useraktifet/{id}")
	public String useraktifet(@PathVariable long id, RedirectAttributes redirectAttributes) {
		UserEntity aktifEdilecekUser = userService.getById(id);
		if (aktifEdilecekUser == null) {
			// Öğretmen bulunamazsa hata mesajı ekleyip yönlendirme yap
			redirectAttributes.addFlashAttribute("error", "Öğretmen bulunamadı!");
			return "redirect:/pasifuserlistesi";
		}
		// Veritabanından öğretmen sil
		userService.userAktifEt(id);

		// Başarı mesajı ekleyip yönlendirme yap
		redirectAttributes.addFlashAttribute("success", "Öğretmen başarıyla silindi!");
		return "redirect:/user/pasifuserlistesi";
	}

	@GetMapping("/userayarlari")
	public String showProfile(Model model, @RequestParam(name = "id", required = false) Long id) {
		UserEntity userEntity;
		if (id != null) {
			Optional<UserEntity> userOptional = userRepository.findById(id);
			if (userOptional.isPresent()) {
				userEntity = userOptional.get();
			} else {
				throw new RuntimeException("Kullanıcı bulunamadı");
			}
		} else {
			userEntity = userService.getCurrentUser();
		}

		// UserEntity'yi UserUpdateDto'ya dönüştür
		UserUpdateDto userUpdateDto = modelMapper.map(userEntity, UserUpdateDto.class);

		// DTO'yu modele ekle
		model.addAttribute("user", userUpdateDto);
		return "user/userayarlari"; // Profil sayfasını temsil eden bir şablon adı
	}

	@PostMapping("/userayarlari")
	public String updateUserSettings(@ModelAttribute("user") UserUpdateDto userUpdateDto, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			logger.error("Kullanıcı güncelleme işlemi sırasında hata oluştu: " + bindingResult.getAllErrors());
			return "user/userayarlari"; // Hatalı validasyon durumunda doğru template ismini döndür
		}

		// Kullanıcı nesnesini veritabanından çek
		Optional<UserEntity> userOptional = userRepository.findById(userUpdateDto.getId());
		if (!userOptional.isPresent()) {
			throw new RuntimeException("Kullanıcı bulunamadı");
		}

		UserEntity updateUser = userOptional.get();
		Role existingRole = updateUser.getRol();

		// Şifre alanı boş bırakıldıysa, eski şifreyi koru
		if (userUpdateDto.getPassword() == null || !userUpdateDto.getPassword().isEmpty()) {
			userUpdateDto.setPassword(updateUser.getPassword());
		}
		// ModelMapper ile alanları kopyala
		modelMapper.map(userUpdateDto, updateUser);
		updateUser.setEnabled(true);
		updateUser.setRol(existingRole);

		// Kullanıcıyı güncelle
		userRepository.save(updateUser);

		return "redirect:/login?logout"; // Başarılı güncelleme durumunda doğru yönlendirme yap
	}

	@GetMapping("/usersifredegistir")
	public String updateUserPassword(Model model) {
		// Mevcut kullanıcıyı al
		   model.addAttribute("user", new UserUpdateDto());
		    return "user/usersifredegistir";

	}


    @PostMapping("/usersifredegistir")
    public String updateUserPassword(
            @RequestParam("password") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmNewPassword") String confirmNewPassword,
            Model model) {

        // Mevcut kullanıcıyı al
        UserEntity currentUser = userService.getCurrentUser();

        // Şu anki şifre ile kullanıcının girdiği şifreyi kontrol et
        if (passwordEncoder.matches(currentPassword, currentUser.getPassword())) {
            // Yeni şifrelerin eşleşip eşleşmediğini kontrol et
            if (newPassword.equals(confirmNewPassword)) {
                // Yeni şifreleri kodla
                String encodedPassword = passwordEncoder.encode(newPassword);

                // Yeni şifreyi kullanıcıya ata ve veritabanına kaydet
                currentUser.setPassword(encodedPassword);
                userService.save(currentUser);

                // Başarılı güncelleme durumunda yönlendirme yap
                return "redirect:/login?logout"; // Profil sayfasına yönlendirme yapılabilir
            } else {
                // Yeni şifreler eşleşmiyorsa hata mesajı döndür
                model.addAttribute("error", "Yeni şifreler eşleşmiyor.");
                return "user/usersifredegistir"; // Şifre değiştirme sayfasına geri dön
            }
        } else {
            // Şu anki şifre yanlışsa hata mesajı döndür
            model.addAttribute("error", "Mevcut şifrenizi yanlış girdiniz.");
            return "user/usersifredegistir"; // Şifre değiştirme sayfasına geri dön
        }
    }
	


}
