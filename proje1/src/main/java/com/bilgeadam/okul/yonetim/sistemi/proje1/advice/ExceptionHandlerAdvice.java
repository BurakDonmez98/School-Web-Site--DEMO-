package com.bilgeadam.okul.yonetim.sistemi.proje1.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
    
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handleAccessDeniedException(final AccessDeniedException ex) {
        logger.error("Kullanıcı yetkisi olmayan veya var olmayan bir adrese erişim yapmaya çalıştı.", ex);
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", "Yetkisiz erişim: " + ex.getMessage());
        modelAndView.setViewName("error"); // Hata sayfasının adı
        
        return modelAndView;
    }
}