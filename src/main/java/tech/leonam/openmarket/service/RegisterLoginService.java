package tech.leonam.openmarket.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.exception.CpfExistsExecption;
import tech.leonam.openmarket.exception.PasswordFormatInvalid;
import tech.leonam.openmarket.model.dto.LoginRegisterDto;
import tech.leonam.openmarket.model.dto.LoginRegisterResponseDto;
import tech.leonam.openmarket.model.entity.LoginEntity;
import tech.leonam.openmarket.repository.RespositoryLogin;

@Service
@AllArgsConstructor
public class RegisterLoginService {
    private final RespositoryLogin respositoryLogin;
    private final ModelMapper modelMapper;

    public LoginRegisterResponseDto save(LoginRegisterDto registerDto) throws CpfExistsExecption, PasswordFormatInvalid {
        verifyCpf(registerDto.getCpf());
        verifyPassword(registerDto.getPassword());

        var passwordEncripyted = new BCryptPasswordEncoder().encode(registerDto.getPassword());

        var login = modelMapper.map(registerDto, LoginEntity.class);
        login.setPassword(passwordEncripyted);

        var saved = respositoryLogin.save(login);

        return modelMapper.map(saved, LoginRegisterResponseDto.class);
    }

    protected void verifyCpf(String cpf) throws CpfExistsExecption {
        if(this.respositoryLogin.findByCpf(cpf) != null){
            throw new CpfExistsExecption("CPF já cadastrado.");
        }
    }

    protected void verifyPassword(String password) throws PasswordFormatInvalid {
        var contains = false;
        for(var c: password.toCharArray()){
            if(Character.isDigit(c)){
                contains = true;
                break;
            }
        }

        if(!contains) throw new PasswordFormatInvalid("Senha Inválida.");
    }

}

