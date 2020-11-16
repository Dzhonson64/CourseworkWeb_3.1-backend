package com.coursework.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Tag(name = "Siebel", description = "Получение объектов лояльности Siebel")
public class SocialAuth {
    @GetMapping("/registratio")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Principal> user(@AuthenticationPrincipal Principal principal) {
        return ResponseEntity.ok(principal);
    }
}
