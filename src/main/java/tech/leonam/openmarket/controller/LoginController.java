package tech.leonam.openmarket.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.leonam.openmarket.exception.CpfExistsExecption;
import tech.leonam.openmarket.exception.PasswordFormatInvalid;
import tech.leonam.openmarket.model.dto.LoginDto;
import tech.leonam.openmarket.model.dto.LoginRegisterDto;
import tech.leonam.openmarket.model.dto.LoginRegisterResponseDto;
import tech.leonam.openmarket.model.dto.LoginResponseDto;
import tech.leonam.openmarket.service.LoginService;
import tech.leonam.openmarket.service.RegisterLoginService;

@RestController
@RequestMapping("/api/login")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final RegisterLoginService registerLoginService;

    @PostMapping
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginDto login) {
        return ResponseEntity.ok(loginService.login(login));
    }

    @PostMapping("/register")
    public ResponseEntity<LoginRegisterResponseDto> register(@RequestBody @Valid LoginRegisterDto registerDto) throws PasswordFormatInvalid, CpfExistsExecption {
        var entitySaved = registerLoginService.save(registerDto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entitySaved.getCpf()).toUri();

        return ResponseEntity.created(uri).body(entitySaved);
    }

}
