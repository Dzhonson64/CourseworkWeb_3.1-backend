package com.coursework.config;

import com.coursework.db.model.UserEntity;
import com.coursework.db.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

//  @Autowired
//  public void configAuthentication(AuthenticationManagerBuilder auth)
//    throws Exception {
//
//    auth.jdbcAuthentication().dataSource(dataSource)
//      .passwordEncoder(passwordEncoder())
//      .usersByUsernameQuery(
//        "select username, p.password as password, enabled "
//          + "from user as u "
//          + "inner join passwords as p on u.password_id = p.id "
//          + "where username=?")
//      .authoritiesByUsernameQuery("select username, role from user_roles where username=?");
//  }

//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    PasswordEncoder encoder = new BCryptPasswordEncoder();
//    return encoder;
//  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
       http.httpBasic().and().authorizeRequests()
            /*swagger*/
            .antMatchers("/v2/api-docs",
                    "/configuration/ui",
                    "/swagger-resources/**",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/webjars/**",
                    "/actuator/**"
            ).permitAll()
            /*end swagger*/

            .antMatchers(" /api/courseworkWeb/user", "/user")
            .permitAll()
            /*end DEV mode*/
            //.anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
      @Override
      public void commence(HttpServletRequest request, HttpServletResponse response,
                           AuthenticationException authException) throws IOException, ServletException {
        if (request.getRequestURI().startsWith("/api/courseworkWeb") && !request.getRequestURI().endsWith("login")) {
          response.setStatus(HttpStatus.UNAUTHORIZED.value());
        } else if (request.getRequestURI().startsWith("/webjars/springfox-swagger-ui/springfox.js")) {

        }
      }
    })
            .and()
            .csrf().disable();
    http.cors().disable();
//    System.setProperty("https.protocols", "TLSv1.2");
//
//    CharacterEncodingFilter filter = new CharacterEncodingFilter();
//    filter.setEncoding("UTF-8");
//    filter.setForceEncoding(true);
//    http.addFilterBefore(filter, CsrfFilter.class);
//
//    http.httpBasic().and().authorizeRequests()
//      /*swagger*/
//      .antMatchers("/v2/api-docs",
//        "/configuration/ui",
//        "/swagger-resources/**",
//        "/configuration/security",
//        "/swagger-ui.html",
//        "/webjars/**"
//      ).permitAll()
//  //hasAuthority(BaseRole.ADMIN.getRole())
//      /*end swagger*/
//
//      .antMatchers("/api/keeneye/user", "/user")
//      .permitAll()
//      .antMatchers(HttpMethod.POST,
//        "/api/keeneye/logout", "/logout",
//        "/api/keeneye/support", "/support",
//        "/api/keeneye/statistic", "/statistic","/request/supportRequest"
//
//      ).permitAll()
//      /*DEV mode*/
//      .antMatchers(HttpMethod.GET,
//        "/development/authAsUser",
//              "/api/keeneye/projects/published",
//              "/projects/**"
//      ).permitAll()
//      /*end DEV mode*/
//      .anyRequest().authenticated()
//      .and()
//      .exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
//      @Override
//      public void commence(HttpServletRequest request, HttpServletResponse response,
//                           AuthenticationException authException) throws IOException, ServletException {
//        if (request.getRequestURI().startsWith("/api/keeneye") && !request.getRequestURI().endsWith("login")) {
//          response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        } else if (request.getRequestURI().startsWith("/webjars/springfox-swagger-ui/springfox.js")) {
//
//        }
//      }
//    })
//      .and()
//      .csrf().disable();
//    http.cors().disable();
  }

  @Bean
  PrincipalExtractor principalExtractor(UserRepo userRepo){
    return map -> {
      return new UserEntity();
    };
  }

}
