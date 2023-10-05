package tech.leonam.openmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.leonam.openmarket.model.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> {
}
