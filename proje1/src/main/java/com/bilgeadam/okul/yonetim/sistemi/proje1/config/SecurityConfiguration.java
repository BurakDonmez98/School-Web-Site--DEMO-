package com.bilgeadam.okul.yonetim.sistemi.proje1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
	  
  
   
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/index", "/user/yeni", "/css/**", "/js/**", "/img/**" , "/" , "/userkayit").permitAll() // Açık URL'ler
                .anyRequest().authenticated() // Diğer tüm URL'ler için kimlik doğrulaması gerekli
            )
            .formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/about", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            
           
            );

        return http.build();
    }
    
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	 @Bean
	    public SpringSecurityDialect securityDialect() {
	        return new SpringSecurityDialect();
	    }


}




   
