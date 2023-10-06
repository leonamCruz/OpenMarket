package tech.leonam.openmarket.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

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

    private String category;

    private String unit;

    private String codeBar;

    @Transient
    private Long brandId;

    @Transient
    private Long supplierId;

    public void setBrandAndSupplier(BrandEntity brandEntity, SupplierEntity supplierEntity) {
        this.brand = brandEntity;
        this.supplier = supplierEntity;
    }

}
