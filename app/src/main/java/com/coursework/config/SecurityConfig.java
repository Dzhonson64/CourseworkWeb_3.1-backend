package com.coursework.config;

import com.coursework.security.jwt.JwtConfigurer;
import com.coursework.security.jwt.JwtTokenProvider;
import com.twilio.http.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.sql.DataSource;

@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final DataSource dataSource;

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//
//
//
//        http.httpBasic().disable()
//                .csrf().disable()
//                .authorizeRequests()
//                .
//
//    antMatchers("/api/courseworkWeb/v3/api-docs/**",
//                        "/configuration/ui",
//                        "/swagger-resources/**",
//                        "/configuration/security",
//                        "/swagger-ui.html",
//                        "/webjars/**",
//                        "/actuator/**",
//                        " /api/courseworkWeb/user","/user/**","/api/courseworkWeb/auth/login").
//
//    permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .apply(new JwtConfigurer(jwtTokenProvider));
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(String.valueOf(HttpMethod.POST), "/login", "register/**").permitAll()
                .antMatchers(String.valueOf(HttpMethod.DELETE), "auth/users/*").hasRole("ADMIN")
                .antMatchers("/v3/api-docs/**",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/actuator/**", "/auth/login/**", "/auth/register/**", "/user/**", "/order/*/buy",
                        "/order/user/*", "/products/**", "/auth/login/provider", "/order/product", "/auth/users", "/order/all/*",
                        "/order/sales/*").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }


}
