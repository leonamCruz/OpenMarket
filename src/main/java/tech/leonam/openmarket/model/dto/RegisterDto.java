package tech.leonam.openmarket.model.dto;

import lombok.Data;
import tech.leonam.openmarket.model.enums.RoleEnum;

@Data
public class RegisterDto {
    private String cpf;
    private String password;
    private RoleEnum role;

}
