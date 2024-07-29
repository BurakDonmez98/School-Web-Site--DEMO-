package com.bilgeadam.okul.yonetim.sistemi.proje1.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {

    @GetMapping("/index")
    public String home() {
        return "index";
    }
    @GetMapping("/about")
    public String about(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Kullanıcı oturum açmışsa, ilgili kullanıcı bilgilerini model'e ekleyebilirsiniz
            model.addAttribute("username", authentication.getName());
            // Oturum açılmışsa, hedef sayfayı döndürün
            return "about";
        } else {
            // Oturum açılmamışsa, giriş sayfasına yönlendirin
            return "redirect:/login"; // Eğer giriş sayfanızın URL'i "/login" ise
        }
    }
    @GetMapping("/dersler")
    public String dersler() {
        return "dersler"; // dersler.html dosyasını döndürür
    }
   
//    @GetMapping("/ogretmen/ogretmenlistesi")
//    public String ogretmenlistesi() {
//    	return "/ogretmen/ogretmenlistesi";
//    }
//    @GetMapping("/ogretmen")
//    public String ogretmenlistesi()
//    {
//    	return"/ogretmen/ogretmenlistesi";
//    	
    }
