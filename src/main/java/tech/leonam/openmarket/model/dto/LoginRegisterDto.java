package tech.leonam.openmarket.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import tech.leonam.openmarket.model.enums.RoleEnum;

@Data
public class LoginRegisterDto {
    @CPF
    private String cpf;
    @Size(min = 8, max = 16)
    private String password;
    private RoleEnum role;

}
