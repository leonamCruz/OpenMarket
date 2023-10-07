package tech.leonam.openmarket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDtoResponseWithToken {
    private String cpf;
    private String token;
}
