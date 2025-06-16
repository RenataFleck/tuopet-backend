package com.example.tuopet.controller;

import com.example.tuopet.dto.AuthenticationDTO;
import com.example.tuopet.dto.LoginResponseDTO;
import com.example.tuopet.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO authenticationDTO){
        return ResponseEntity.ok(authenticationService.login(authenticationDTO));
    }

}
