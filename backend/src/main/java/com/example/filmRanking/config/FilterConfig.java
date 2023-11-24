package com.example.filmRanking.config;

import com.example.filmRanking.filter.AuthenticationFilter;
import com.example.filmRanking.repository.UserRepository;
import com.example.filmRanking.service.UserService.UserService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public AuthenticationFilter authenticationFilter(UserService userService, UserRepository userRepository) {
        return new AuthenticationFilter(userService, userRepository);
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean(AuthenticationFilter authenticationFilter) {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(authenticationFilter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}