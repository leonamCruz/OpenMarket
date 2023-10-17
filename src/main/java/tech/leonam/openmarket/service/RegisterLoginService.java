package tech.leonam.openmarket.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.exception.CpfExistsExecption;
import tech.leonam.openmarket.exception.PasswordFormatInvalid;
import tech.leonam.openmarket.model.dto.RegisterDto;
import tech.leonam.openmarket.model.entity.LoginEntity;
import tech.leonam.openmarket.repository.RespositoryLogin;

@Service
@AllArgsConstructor
public class RegisterLoginService {
    private final RespositoryLogin respositoryLogin;

    public LoginEntity register(RegisterDto registerDto) throws CpfExistsExecption, PasswordFormatInvalid {
        if(this.respositoryLogin.findByCpf(registerDto.getCpf()) != null){
            throw new CpfExistsExecption("CPF já cadastrado.");
        }

        var contains = false;
        for(var c: registerDto.getPassword().toCharArray()){
            if(Character.isDigit(c)){
                contains = true;
                break;
            }
        }

        if(!contains) throw new PasswordFormatInvalid("Senha Inválida.");

        var passwordEncripyted = new BCryptPasswordEncoder().encode(registerDto.getPassword());
        var loginEntity = new LoginEntity(registerDto.getCpf(),passwordEncripyted,registerDto.getRole());

        return respositoryLogin.save(loginEntity);
    }

}

