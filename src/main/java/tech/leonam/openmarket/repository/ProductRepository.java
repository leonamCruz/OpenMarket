package tech.leonam.openmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.leonam.openmarket.model.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
    @Query
    ProductEntity findByCodeBar(String codeBar);
}
