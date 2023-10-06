package tech.leonam.openmarket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.leonam.openmarket.model.enums.RoleEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDtoResponse {

    private String cpf;
    private RoleEnum roleEnum;

}
