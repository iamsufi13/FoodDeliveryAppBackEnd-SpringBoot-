//package com.whizFortuneRestaurant.Config;
//
//import com.whizFortuneRestaurant.Security.JwtAuthenticationEntryPoint;
//import com.whizFortuneRestaurant.Security.JwtAuthenticationFilter;
//import com.whizFortuneRestaurant.Security.JwtHelper;
//import com.whizFortuneRestaurant.Users.CustomUserDetailsService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//public class SpringSecurityConfig {
//
//    private final JwtAuthenticationEntryPoint point;
//    private final JwtHelper jwtHelper;
//    private final CustomUserDetailsService userDetailsService;
//    private final PasswordEncoder passwordEncoder;
//
//    @Lazy
//    public SpringSecurityConfig(JwtAuthenticationEntryPoint point, JwtHelper jwtHelper, CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
//        this.point = point;
//        this.jwtHelper = jwtHelper;
//        this.userDetailsService = userDetailsService;
//        this.passwordEncoder= passwordEncoder;
//    }
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf -> csrf.disable())
//                .authorizeRequests(authorizeRequests -> authorizeRequests
//                        .requestMatchers("/test").authenticated()
//                        .requestMatchers("api/auth/login").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
//        return builder.getAuthenticationManager();
//    }
//
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() {
//        return new JwtAuthenticationFilter(jwtHelper, userDetailsService);
//    }
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//            DaoAuthenticationProvider daoAuthenticationProvider =new DaoAuthenticationProvider();
//            daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//            daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
//            return daoAuthenticationProvider;
//    }
//}

package com.whizFortuneRestaurant.Config;

import com.whizFortuneRestaurant.Security.JwtAuthenticationEntryPoint;
import com.whizFortuneRestaurant.Security.JwtAuthenticationFilter;
import com.whizFortuneRestaurant.Security.JwtHelper;
import com.whizFortuneRestaurant.Users.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SpringSecurityConfig {

    private final JwtAuthenticationEntryPoint point;
    private final JwtHelper jwtHelper;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Lazy
    public SpringSecurityConfig(JwtAuthenticationEntryPoint point, JwtHelper jwtHelper, CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.point = point;
        this.jwtHelper = jwtHelper;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/user/number").permitAll() // Allow unauthenticated access
                        .requestMatchers("/api/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtHelper, userDetailsService);
    }
}

