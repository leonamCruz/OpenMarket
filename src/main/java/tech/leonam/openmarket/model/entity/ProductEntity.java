package tech.leonam.openmarket.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tech.leonam.openmarket.model.enums.CategoryEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private long amount;

    private BigDecimal price;

    @ManyToOne
    private BrandEntity brandEntity;

    @ManyToOne
    private SupplierEntity supplierEntity;

    private CategoryEnum categoryEnum;

    private String unit;

    private String codeBar;
    private LocalDateTime localDateTime;

}
