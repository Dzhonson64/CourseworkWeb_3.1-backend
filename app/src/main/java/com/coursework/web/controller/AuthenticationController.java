package com.coursework.web.controller;

import com.coursework.db.model.UserEntity;
import com.coursework.mapper.ProviderMapper;
import com.coursework.mapper.UserMapper;
import com.coursework.security.jwt.JwtTokenProvider;
import com.coursework.service.ProviderService;
import com.coursework.service.UserService;
import com.coursework.web.dto.ProviderDto;
import com.coursework.web.dto.UserAndProvidersDto;
import com.coursework.web.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody  UserDto userDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationWithToken =
                    new UsernamePasswordAuthenticationToken( userDto.getName(), userDto.getPassword(), null );
            Authentication authentication = authenticationManagerBean.authenticate( authenticationWithToken );

            UserEntity userEntity = userService.findByUsername(userDto.getName());
            //authenticationManagerBean.authenticate(new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword()));

            if (userEntity == null) {
                throw new UsernameNotFoundException("User with username: " + userDto.getName() + " not found");
            }
            String token = jwtTokenProvider.createToken(userDto.getName(), userEntity.getRoles());
           Map<Object, Object> response = new HashMap<>();
           response.put("name", userDto.getName());
           response.put("token", token);
           response.put("id", userEntity.getId());
           response.put("isAdmin", providerService.isAdmin(userEntity.getRoles()));

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
    public ResponseEntity registerProvider(@RequestBody ProviderDto providerDto) throws Exception {
        return  ResponseEntity.ok(ProviderMapper.MAPPER.toDto(providerService.register(ProviderMapper.MAPPER.toEntity(providerDto))));
    }

    @PostMapping("login/provider")
    public ResponseEntity loginProvider(@RequestBody ProviderDto providerDto) {
        return  ResponseEntity.ok(ProviderMapper.MAPPER.toDto(providerService.login(ProviderMapper.MAPPER.toEntity(providerDto))));
    }

    @GetMapping("users")
    public List<UserAndProvidersDto> registerProvider() {
        return  providerService.getAllUsers();
    }

    @DeleteMapping("users/{id}")
    public void deleteProvider(@PathVariable("id") Long id) {
        providerService.delete(id);
    }


}
