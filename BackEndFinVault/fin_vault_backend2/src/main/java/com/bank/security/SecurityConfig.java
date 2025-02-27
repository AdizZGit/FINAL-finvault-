package com.bank.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private PasswordEncoder encoder;
    private CustomJwtAuthenticationFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF
            .cors(cors -> {}) // Enable CORS
        
            .authorizeHttpRequests(auth -> auth
              
                .requestMatchers("/").permitAll() // Allow all requests
                
                // Allow OPTIONS requests (for CORS preflight)
                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                
              
                .requestMatchers(HttpMethod.GET, "/home", "/v*/api-docs/", "/swagger-ui/", "/swagger-ui/index.html#/").permitAll()
                .requestMatchers(HttpMethod.POST, "/users/signup", "/users/signin").permitAll()
                .requestMatchers("/otp/*").permitAll()

                // Any other request must be authenticated (for now everything is permitted)
                .anyRequest().permitAll()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session for JWT
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}














//package com.bank.security;
//
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
////import com.bank.Config;
//
//import lombok.AllArgsConstructor;
//
//@EnableWebSecurity
//@Configuration
//@AllArgsConstructor
//public class SecurityConfig {
//
//    private PasswordEncoder encoder;
//    private CustomJwtAuthenticationFilter jwtFilter;
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
//        System.out.println("httppasss=:"+http);
//        http.csrf(csrf -> csrf.disable()) // Disable CSRF
//            .cors(cors -> {}) // Enable CORS
//        
////        http.csrf(csrf -> csrf.disable()) // Disable CSRF
////        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//        
//            .authorizeHttpRequests(auth -> auth
//                // Publicly accessible endpoints
//                .requestMatchers(HttpMethod.POST, "/users/signup", "/users/signin").permitAll()
//                
//                
//                
//                .requestMatchers("/otp/*").permitAll()
//                
//                .requestMatchers(HttpMethod.GET, "/home", "/v*/api-docs/", "/swagger-ui/","/swagger-ui/index.html#/","/swagger-ui/index.html#/").permitAll()
//                
//                // Allow OPTIONS requests (for CORS preflight)
//                .requestMatchers(HttpMethod.OPTIONS).permitAll()
//                
//
//                // Transactions - USER authority required
//                .requestMatchers(HttpMethod.GET, "/transactions/").hasAuthority("USER")
//                .requestMatchers(HttpMethod.POST, "/transactions/").hasAuthority("USER")
//                .requestMatchers(HttpMethod.PUT, "/transactions/").hasAuthority("USER")
//                .requestMatchers(HttpMethod.DELETE, "/transactions/").hasAuthority("USER")
//                .requestMatchers(HttpMethod.POST, "/account-requests").hasRole("USER")
////                .requestMatchers(HttpMethod.POST, "/transactions/").hasAuthority("USER")
//
//                // Loans - USER authority required
//                .requestMatchers(HttpMethod.GET, "/loans/").hasAuthority("USER")
//                .requestMatchers(HttpMethod.POST, "/loans/").hasAuthority("USER")
//                .requestMatchers(HttpMethod.PUT, "/loans/").hasAuthority("USER")
//                .requestMatchers(HttpMethod.DELETE, "/loans/").hasAuthority("USER")
//
//                // Cards - USER authority required
//                .requestMatchers(HttpMethod.GET, "/cards/").hasAuthority("USER")
//                .requestMatchers(HttpMethod.POST, "/cards/").hasAuthority("USER")
//                .requestMatchers(HttpMethod.PUT, "/cards/").hasAuthority("USER")
//                .requestMatchers(HttpMethod.DELETE, "/cards/").hasAuthority("USER")
//
//                // Employees - EMPLOYEE authority required
//                .requestMatchers(HttpMethod.GET, "/employees/").hasAuthority("EMPLOYEE")
//                .requestMatchers(HttpMethod.POST, "/employees/").hasAuthority("EMPLOYEE")
//                .requestMatchers(HttpMethod.PUT, "/employees/").hasAuthority("EMPLOYEE")
//                .requestMatchers(HttpMethod.DELETE, "/employees/").hasAuthority("EMPLOYEE")
//                .requestMatchers(HttpMethod.GET, "/account-requests/**").hasRole("EMPLOYEE")
//                .requestMatchers(HttpMethod.POST, "/account-requests/**").hasRole("EMPLOYEE")
//                .requestMatchers(HttpMethod.POST, "/accounts/create/").hasRole("EMPLOYEE")
//                
////                .requestMatchers(HttpMethod.GET, "/admin/dashboard").hasRole("ADMIN")
////                .requestMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN")
////                .requestMatchers(HttpMethod.POST,"/admin/employees/assign/", "/admin/**").hasRole("ADMIN")
//                
//                
//                //.requestMatchers("/admin/employees/assign/", "/admin/**").hasRole("ADMIN")
//               // .requestMatchers(HttpMethod.PUT,"/admin/employees/").hasRole("ADMIN")
//              //  .requestMatchers(HttpMethod.PUT, "/admin/employees/**").hasRole("ADMIN")
//
//                
////                .requestMatchers(HttpMethod.GET, "/admin/**").permitAll()
////                .requestMatchers(HttpMethod.POST, "/admin/employees/assign/", "/admin/**").permitAll()
//                
//                
//
//                
//                
//                
//               // .requestMatchers(HttpMethod.POST, "/contact/send").hasAnyAuthority("USER", "EMPLOYEE")
//                .requestMatchers("/contact/send").permitAll()
//
//                 //Admin - ADMIN authority required
////                .requestMatchers(HttpMethod.GET, "/admin/").hasAuthority("ADMIN")
////                .requestMatchers(HttpMethod.POST, "/admin/").hasAuthority("ADMIN")
////                .requestMatchers(HttpMethod.PUT, "/admin/").hasAuthority("ADMIN")
////                .requestMatchers(HttpMethod.DELETE, "/admin/").hasAuthority("ADMIN")
//                
//                
//              //  .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN") // Ensures all admin endpoints require the same role
//               
//                .requestMatchers("/admin/**").permitAll()
//
//                // Any other request must be authenticated
//                .anyRequest().authenticated()
//            )
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session for JWT
//            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//	//    @Bean
//	//    public CorsConfigurationSource corsConfigurationSource() {
//	//        CorsConfiguration configuration = new CorsConfiguration();
//	//        configuration.setAllowedOrigins(List.of("*")); // Allow all origins
//	//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow all methods
//	//        configuration.setAllowedHeaders(List.of("*")); // Allow all headers
//	//        configuration.setAllowCredentials(true); // Allow credentials
//	//
//	//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	//        source.registerCorsConfiguration("/**", configuration);
//	//        return source;
//	//    }
//}
//	
//
//
////    @Bean
////    public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
////       http.csrf(customizer -> customizer.disable())
////           .authorizeHttpRequests(request -> request
////                        .requestMatchers("/home", "/users/signup", "/users/signin", "/v*/api-doc*/**", "/swagger-ui/**")
////                       .permitAll()
////                       	.requestMatchers(HttpMethod.OPTIONS).permitAll()
////                        .requestMatchers("/test/admin").hasRole("ADMIN")
////                        .requestMatchers("/users/**", "/transactions/**").hasRole("USER")
////                        .requestMatchers("/employees/**").hasRole("EMPLOYEE")
////                       // .requestMatchers("/employees/**").permitAll()
////                        .requestMatchers("/admin/**").hasRole("ADMIN")
////                    
////                        .anyRequest().authenticated())
////                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////    }
////
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
////        return config.getAuthenticationManager();
////    }
////}
//
//    
//    
    
    
    
    
    


//package com.bank.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import lombok.AllArgsConstructor;
//
//@EnableWebSecurity
//@Configuration
//@AllArgsConstructor
//public class SecurityConfig {
//
//    private PasswordEncoder encoder;
//    private CustomJwtAuthenticationFilter jwtFilter;
//
//    @Bean
//    public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
//        http
//            .csrf(csrf -> csrf.disable()) // Disable CSRF
//            .authorizeHttpRequests(request -> request
//                .requestMatchers("/**").permitAll() // ✅ Allow all requests
//                .requestMatchers(HttpMethod.OPTIONS).permitAll()
//                .anyRequest().permitAll()) // ✅ Allow everything
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//        
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//}
//
