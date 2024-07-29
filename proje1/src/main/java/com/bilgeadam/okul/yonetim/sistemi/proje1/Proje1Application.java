package com.bilgeadam.okul.yonetim.sistemi.proje1;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.Role;
import com.bilgeadam.okul.yonetim.sistemi.proje1.entity.UserEntity;
import com.bilgeadam.okul.yonetim.sistemi.proje1.repo.UserRepository;

@SpringBootApplication
public class Proje1Application {
	
	
	public static void main(String[] args) {
		SpringApplication.run(Proje1Application.class, args);
		
	}
	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}


	@Bean
	CommandLineRunner runner(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		
		UserEntity admin = new UserEntity();
		admin.setName("Ali");
		admin.setbDate(LocalDate.of(2000, 02, 15));
		admin.setEmail("ali@gmail.com");
		admin.setRol(Role.ROLE_ADMIN);
		
		String encodedPassword =passwordEncoder.encode("123");
		admin.setPassword(encodedPassword); // Changed setPASSWORD to setPassword
		admin.setlName("Veli");
		admin.setEnabled(true);
		admin.setInsertedUser(admin);
		
		return args ->{
			
			if(userRepo.existsByEmail(admin.getEmail()) == false)
							userRepo.save(admin);
				
			
		};
	
	}
}