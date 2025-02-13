package com.healthree.healthree_back.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.healthree.healthree_back.common.utils.TokenUtil;
import com.healthree.healthree_back.config.jwt.JwtAuthorizationFilter;
import com.healthree.healthree_back.user.UserRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final UserRepository userRepository;
    private final CorsConfig corsConfig;
    private final TokenUtil tokenUtil;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .apply(new CustomDsl())
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/**", "admin/**", "/api/payment/deposit/callback",
                                "/api/alarm/secretary/**",
                                "/api/customerService")
                        .permitAll()
                        .requestMatchers("/api/**").authenticated());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/", "/swagger-ui.html", "/swagger-ui/**",
                "/swagger-resources/**", "/api-docs/**");
    }

    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(corsConfig.corsFilter())
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository, tokenUtil));
        }
    }
}