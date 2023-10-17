package tech.leonam.openmarket.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import tech.leonam.openmarket.model.enums.CategoryEnum;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private long amount;

    private BigDecimal price;

    @ManyToOne
    private BrandEntity brand;

    @ManyToOne
    private SupplierEntity supplier;

    private CategoryEnum category;

    private String unit;

    private String codeBar;

}
