package com.demo.jobprep.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class Secuirtyconfig {
    


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf->csrf.disable())
            .authorizeHttpRequests(auth->auth
                .requestMatchers("/admin/**").hasRole("ADMIN")  
                .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
            return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user=User.builder()
                        .username("Vasim")
                        .password(passwordEncoder().encode("1234"))
                        .roles("USER")
                        .build();
        
        UserDetails admin=User.builder()
                            .username("admin")
                            .password(passwordEncoder().encode("admin123"))
                            .roles("ADMIN")
                            .build();
        
        return new InMemoryUserDetailsManager(user,admin);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
