package com.krillinator.springSecurityLektion.configurations;

import com.krillinator.springSecurityLektion.user.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity                   // Enables @PreAuthorize
public class AppSecurityConfig {

    private final AppPasswordConfig bcrypt;
    private final UserModelService userModelService;

    @Autowired
    public AppSecurityConfig(AppPasswordConfig bcrypt, UserModelService userModelService) {
        this.bcrypt = bcrypt;
        this.userModelService = userModelService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // .csrf().disable()
                .authorizeHttpRequests( requests -> {
                        requests
                            .requestMatchers( "/", "/login", "/logout" , "/error", "/rest/**", "/register", "/static/**", "/helloWorld").permitAll()
                            .requestMatchers("/admin").hasRole("ADMIN")
                            .anyRequest()
                            .authenticated();
                        }
                )

                .formLogin( formlogin -> {
                            formlogin.loginPage("/login");
                        }
                )
                //.usernameParameter("email")
                // TODO - ERROR : UserDetailsService returned null, which is an interface contract violation

                .rememberMe(rememberMe -> {
                            rememberMe
                                    .rememberMeParameter("remember-me")
                                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))   // 3 Weeks
                                    .key("someSecureKey")
                                    .userDetailsService(userModelService);
                                    }
                )

                .logout( logout -> {
                    logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("remember-me", "JSESSIONID");
                        }
                )
                .authenticationProvider(authenticationOverride());

        return http.build();
    }

    // Tell Spring Security to use OUR implementation
    public DaoAuthenticationProvider authenticationOverride() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userModelService);            // Query
        provider.setPasswordEncoder(bcrypt.bCryptPasswordEncoder()); // Encoder BCRYPT

        return provider;
    }

}
