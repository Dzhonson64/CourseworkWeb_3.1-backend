package com.coursework.web.controller;

import com.coursework.db.model.UserEntity;
import com.coursework.mapper.ProviderMapper;
import com.coursework.mapper.UserMapper;
import com.coursework.security.jwt.JwtTokenProvider;
import com.coursework.service.ProviderService;
import com.coursework.service.UserService;
import com.coursework.web.dto.ProviderDto;
import com.coursework.web.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Tag(name = "AuthenticationController", description = "Аунтетификация")
@RequestMapping(value = "/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManagerBean;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final ProviderService providerService;
    @PostMapping("login")
    public ResponseEntity login(@RequestBody  UserDto userDto) {
        try {
            String username = userDto.getNickName();
            authenticationManagerBean.authenticate(new UsernamePasswordAuthenticationToken(username, userDto.getPassword()));
            UserEntity userEntity = userService.findByUsername(username);
            if (userEntity == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            String token = jwtTokenProvider.createToken(username, userEntity.getRoles());
           Map<Object, Object> response = new HashMap<>();
           response.put("username", username);
           response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

    }

    @PostMapping("register/user")
    public ResponseEntity registerUser(@RequestBody UserDto userDto) {
       return  ResponseEntity.ok(UserMapper.MAPPER.toDto(userService.register(UserMapper.MAPPER.toEntity(userDto))));
    }

    @PostMapping("register/provider")
    public ResponseEntity registerProvider(@RequestBody ProviderDto providerDto) {
        return  ResponseEntity.ok(ProviderMapper.MAPPER.toDto(providerService.register(ProviderMapper.MAPPER.toEntity(providerDto))));
    }


}
