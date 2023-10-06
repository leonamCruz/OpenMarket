package tech.leonam.openmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.leonam.openmarket.model.dto.LoginDto;
import tech.leonam.openmarket.model.dto.LoginDtoResponseCpf;
import tech.leonam.openmarket.service.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping
    public ResponseEntity<LoginDtoResponseCpf> login(@RequestBody LoginDto login) {
        return ResponseEntity.ok(service.login(login));
    }

}
