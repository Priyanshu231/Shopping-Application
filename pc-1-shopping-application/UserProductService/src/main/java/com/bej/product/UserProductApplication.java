package com.bej.product;

import com.bej.product.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProductApplication.class, args);
    }
    @Bean
    public FilterRegistrationBean jwtFilterBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/api/v1/customer/*");
        return filterRegistrationBean;
    }
}
