package org.springboot.samples.ff4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] WHITE_LIST = {
            "/login",
            "/logout",
            "/actuator/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers(HttpMethod.POST, "/ff4j-web-console/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/ff4j-web-console/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())  //Authorization: Basic YWRtaW46MTIzNDU2
                .csrf(csrf-> csrf
                        .ignoringRequestMatchers("/api/ff4j/**", "/ff4j-web-console/**"))
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("123456"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

}
