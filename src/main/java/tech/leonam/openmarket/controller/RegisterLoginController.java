package tech.leonam.openmarket.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.leonam.openmarket.exception.CpfExistsExecption;
import tech.leonam.openmarket.exception.PasswordFormatInvalid;
import tech.leonam.openmarket.model.dto.RegisterDto;
import tech.leonam.openmarket.model.entity.LoginEntity;
import tech.leonam.openmarket.service.RegisterLoginService;

@RestController
@RequestMapping("/api/login/register")
@RequiredArgsConstructor
public class RegisterLoginController {
    private RegisterLoginService service;

    @PostMapping()
    public ResponseEntity<LoginEntity> register(@RequestBody @Valid RegisterDto entity) throws CpfExistsExecption, PasswordFormatInvalid {
        return ResponseEntity.ok(service.register(entity));
    }

}
