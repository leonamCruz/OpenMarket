package tech.leonam.openmarket.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginResponseDto {
    private String cpf;
    private String token;
}
