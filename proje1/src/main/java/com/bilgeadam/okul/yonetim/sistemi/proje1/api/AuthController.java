package com.bilgeadam.okul.yonetim.sistemi.proje1.api;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bilgeadam.okul.yonetim.sistemi.proje1.dto.UserSaveDto;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Role;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.UserEntity;
import com.bilgeadam.okul.yonetim.sistemi.proje1.repo.UserRepository;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	public static final String REDIRECT_LOGIN = "redirect:/login";

	private UserRepository userRepository; 
	private final PasswordEncoder passwordEncoder;
	

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		
		this.userRepository = userRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return REDIRECT_LOGIN;
    }

    @GetMapping("/error")
    public String errorGet() {
        return REDIRECT_LOGIN;
    }

    @PostMapping("/error")
    public String errorPost() {
        return REDIRECT_LOGIN;
    }
	@GetMapping("/userkayit")
	public String userRegister(Model model) {

		model.addAttribute("user", new UserSaveDto());

		return "user/userkayit";
	}

	
	  @PostMapping("/userkayit")
	    public String userRegister(@Valid @ModelAttribute("user") UserSaveDto userSaveDto, BindingResult result,
	                               Model model) {
	        if (result.hasErrors()) {
	            model.addAttribute("errors", result.getAllErrors());
	            return "user/userkayit";
	        }

	        // Create User entity from UserSaveDto
	        UserEntity user = new UserEntity();
	        user.setName(userSaveDto.getName());
	        user.setlName(userSaveDto.getlName());
	        user.setEmail(userSaveDto.getEMAIL());
	        user.setbDate(userSaveDto.getbDate());
	        user.setRol(Role.ROLE_USER);
	        user.setPassword(passwordEncoder.encode(userSaveDto.getPASSWORD())); // Şifreyi şifrele
	        user.setEnabled(true);
	        
	        // Save user to the database
	        userRepository.save(user);

	        return "redirect:/login";
	    }
}
