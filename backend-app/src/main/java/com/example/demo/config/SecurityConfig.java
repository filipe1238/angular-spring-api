//package com.example.demo.config;
//
//import lombok.AllArgsConstructor;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@AllArgsConstructor
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//        //in memory users
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder.encode("user"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder.encode("admin"))
//                .roles("USER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                //enable h2 integration on security
//                .csrf().ignoringRequestMatchers(PathRequest.toH2Console()).and()
//                .headers().frameOptions().sameOrigin().and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated().and()
//                .formLogin();
//        return http.build();
//    }
//    public HttpSecurity requestParam(HttpSecurity http) throws Exception {
//         return http.authorizeHttpRequests(configurer->
//                configurer
//                        .requestMatchers(HttpMethod.GET, "/api/**").hasRole("USER")
//                        .requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/**").hasRole("USER"));
//    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
////        return encoder;
////    }
//
//}
//
