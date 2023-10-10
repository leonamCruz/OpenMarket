package tech.leonam.openmarket.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tech.leonam.openmarket.model.entity.BrandEntity;
import tech.leonam.openmarket.model.entity.SupplierEntity;
import tech.leonam.openmarket.model.enums.CategoryEnum;

import java.math.BigDecimal;
@Data
@Getter
@Setter
public class ProductResponseDto {

    private long id;
    private String name;
    private long amount;
    private BigDecimal price;
    private BrandEntity brand;
    private SupplierEntity supplier;
    private CategoryEnum category;
    private String unit;
    private String codeBar;

}
