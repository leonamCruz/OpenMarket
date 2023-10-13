package tech.leonam.openmarket.model.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private ProductEntity product;
    private long amount;

}
