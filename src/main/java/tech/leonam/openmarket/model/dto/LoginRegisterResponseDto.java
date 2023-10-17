package tech.leonam.openmarket.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tech.leonam.openmarket.model.enums.RoleEnum;

@Data
@Getter
@Setter
public class LoginRegisterResponseDto {
    private String cpf;
    private String password;
    private RoleEnum role;
}
