package tech.leonam.openmarket.model.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tech.leonam.openmarket.model.enums.CategoryEnum;

import java.math.BigDecimal;
@Data
@Getter
@Setter
public class ProductSaveDto {

    @NotNull(message = "Nome não pode ser nulo.")
    private String name;
    @NotNull(message = "Quantidade não pode ser nula.")
    private long amount;
    @NotNull(message = "Preço não pode ser nulo.")
    private BigDecimal price;
    @NotNull(message = "Id da marca não pode ser nulo.")
    private Long idBrand;
    @NotNull(message = "Id do fornecedor não pode ser nulo.")
    private Long idSupplier;
    @NotNull(message = "Categoria não pode ser nula.")
    private CategoryEnum category;
    @NotNull(message = "Unidade não pode ser nula.")
    private String unit;
    @NotNull(message = "Código de barras não pode ser nulo.")
    private String codeBar;

    @AssertTrue(message = "Preço não pode ser negativo.")
    private boolean isPriceNotNegative() {
        return price.compareTo(BigDecimal.ZERO) >= 0;
    }

    @AssertTrue(message = "Id da marca não pode ser negativo.")
    private boolean isIdBrandNotNegative() {
        return idBrand >= 0;
    }

    @AssertTrue(message = "Id do fornecedor não pode ser negativo.")
    private boolean isIdSupplierNotNegative() {
        return idSupplier >= 0;
    }

    @AssertTrue(message = "Quantidade não pode ser negativa.")
    private boolean isAmountNotNegative() {
        return amount >= 0;
    }

}
