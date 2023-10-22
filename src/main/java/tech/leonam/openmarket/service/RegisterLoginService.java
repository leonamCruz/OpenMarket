package tech.leonam.openmarket.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.exception.CpfExistsExecption;
import tech.leonam.openmarket.exception.PasswordFormatInvalidException;
import tech.leonam.openmarket.model.dto.LoginRegisterDto;
import tech.leonam.openmarket.model.dto.LoginRegisterResponseDto;
import tech.leonam.openmarket.model.entity.LoginEntity;
import tech.leonam.openmarket.model.enums.RoleEnum;
import tech.leonam.openmarket.repository.RespositoryLogin;

import javax.management.relation.RoleNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@AllArgsConstructor
public class RegisterLoginService {
    private final RespositoryLogin respositoryLogin;
    private final ModelMapper modelMapper;

    public LoginRegisterResponseDto save(LoginRegisterDto registerDto) throws CpfExistsExecption, PasswordFormatInvalidException, RoleNotFoundException {
        verifyCpf(registerDto.getCpf());
        verifyPassword(registerDto.getPassword());
        roleIsNull(registerDto.getRole());
        var passwordEncripyted = new BCryptPasswordEncoder().encode(registerDto.getPassword());

        var login = modelMapper.map(registerDto, LoginEntity.class);
        login.setPassword(passwordEncripyted);
        login.setLocalDateTime(LocalDateTime.now(ZoneId.of("America/Belem")));
        var saved = respositoryLogin.save(login);

        return modelMapper.map(saved, LoginRegisterResponseDto.class);
    }

    protected void verifyCpf(String cpf) throws CpfExistsExecption {
        if(this.respositoryLogin.findByCpf(cpf) != null){
            throw new CpfExistsExecption("CPF já cadastrado.");
        }
    }

    protected void verifyPassword(String password) throws PasswordFormatInvalidException {
        var contains = false;
        for(var c: password.toCharArray()){
            if(Character.isDigit(c)){
                contains = true;
                break;
            }
        }

        if(!contains) throw new PasswordFormatInvalidException("Senha Inválida.");
    }

    public void roleIsNull(RoleEnum roleEnum) throws RoleNotFoundException {
        if (roleEnum == null) throw new RoleNotFoundException("Privilégios não foram encontrados.");
    }
}

