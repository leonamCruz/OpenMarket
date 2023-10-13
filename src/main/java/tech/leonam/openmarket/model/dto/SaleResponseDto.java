package tech.leonam.openmarket.model.dto;

import lombok.Data;
import lombok.Getter;
import tech.leonam.openmarket.model.entity.ProductEntity;

@Data
@Getter
public class SaleResponseDto {
    private Long id;
    private ProductEntity product;
}
