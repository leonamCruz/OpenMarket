package tech.leonam.openmarket.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
@Getter
@Setter
@NoArgsConstructor
public class SupplierSaveDto {

    @NotNull(message = "Nome não pode ser nulo.")
    private String name;

    @CNPJ(message = "CPNJ Inválido.")
    private String cnpj;

    @NotNull(message = "Endereço Nulo.")
    private String address;

    @NotNull
    @Pattern(regexp = "\\d{8,15}", message = "Número de telefone inválido")
    private String numberPhone;

    @NotNull
    @Email(message = "E-mail inválido.")
    private String email;

}
