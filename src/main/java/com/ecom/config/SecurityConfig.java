package com.ecom.config;

import com.ecom.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder) { //UserService implements UserDetailsService — required by Spring Security for custom user loading.
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    //DaoAuthenticationProvider = DAO-based (Database Access Object).
    public DaoAuthenticationProvider authenticationProvider() { //Authenticates users using data from the database (via UserDetailsService).
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); // UserService implements UserDetailsService
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Bean
    //Spring Boot 3 requires manually exposing AuthenticationManager as a bean.Used internally during login authentication.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authenticationProvider(authenticationProvider())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/registration**", "/js/**", "/css/**", "/img/**", "/webjars/**",
                    "/register/**", "/verify", "/forgot-password", "/reset-password", "/login", "/"
                ).permitAll()
                .requestMatchers("/seller/**").hasAuthority("SELLER")
                .requestMatchers("/buyer/**").hasAuthority("BUYER")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login") //Defines a custom login page at /login
                .successHandler(customAuthenticationSuccessHandler())//On success, uses a custom handler to redirect based on role
                .permitAll()
            )
            .logout(logout -> logout   //Handles logout at /logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            HttpSession session = request.getSession();
            String email = authentication.getName();
            session.setAttribute("email", email); //// logged-in user's email

            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("SELLER"))) {
                response.sendRedirect("/seller/dashboard");
            } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("BUYER"))) {
                response.sendRedirect("/buyer/dashboard");
            } else {
                response.sendRedirect("/");
            }
        };
    }

   
}
