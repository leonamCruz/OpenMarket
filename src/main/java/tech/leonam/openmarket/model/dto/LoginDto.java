package tech.leonam.openmarket.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {

    @NotNull
    private String cpf;
    @NotNull
    private String password;
}
