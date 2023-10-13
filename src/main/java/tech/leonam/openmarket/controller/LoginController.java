package tech.leonam.openmarket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.leonam.openmarket.model.dto.LoginDto;
import tech.leonam.openmarket.model.dto.LoginDtoResponseWithToken;
import tech.leonam.openmarket.service.LoginService;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private LoginService service;

    @PostMapping
    public ResponseEntity<LoginDtoResponseWithToken> login(@RequestBody LoginDto login) {
        return ResponseEntity.ok(service.login(login));
    }

}
