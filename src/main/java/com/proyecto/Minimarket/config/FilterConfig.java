package com.proyecto.Minimarket.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.proyecto.Minimarket.filter.JwtValidationFilder;





@Configuration
public class FilterConfig {
    @Bean
    FilterRegistrationBean<JwtValidationFilder> jwtFilter(JwtValidationFilder jwtValidationFilter){
    FilterRegistrationBean<JwtValidationFilder> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(jwtValidationFilter);
    registrationBean.addUrlPatterns("/*");
    registrationBean.setOrder(1);
    return registrationBean;    
    }
    
}
