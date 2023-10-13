package tech.leonam.openmarket.model.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class SaleSaveDto {

    @NotNull(message = "Código de barras nulo")
    private String codeBar;
    @NotNull(message = "Quantidade nula.")
    private long amount;
    @AssertTrue(message = "Quantidade não pode ser negativa.")
    private boolean isAmountNotNegative() {
        return amount >= 0;
    }

}
