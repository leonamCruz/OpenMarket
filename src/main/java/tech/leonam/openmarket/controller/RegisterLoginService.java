package tech.leonam.openmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.exception.CpfExistsExecption;
import tech.leonam.openmarket.model.dto.RegisterDto;
import tech.leonam.openmarket.model.entity.LoginEntity;
import tech.leonam.openmarket.repository.RespositoryLogin;

@Service
public class RegisterLoginService {
    @Autowired
    private RespositoryLogin respositoryLogin;
    public LoginEntity register(RegisterDto registerDto) throws CpfExistsExecption {
        if(this.respositoryLogin.findByCpf(registerDto.getCpf()) != null){
            throw new CpfExistsExecption("The cpf already exists in the database");
        }

        var passwordEncripyted = new BCryptPasswordEncoder().encode(registerDto.getPassword());
        var loginEntity = new LoginEntity(registerDto.getCpf(),passwordEncripyted,registerDto.getRole());

        return respositoryLogin.save(loginEntity);
    }
}
