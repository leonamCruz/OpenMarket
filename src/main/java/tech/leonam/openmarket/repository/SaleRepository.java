package tech.leonam.openmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.leonam.openmarket.model.entity.SaleEntity;

public interface SaleRepository extends JpaRepository<SaleEntity,Long> {
}
