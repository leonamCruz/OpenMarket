package tech.leonam.openmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.leonam.openmarket.model.entity.SupplierEntity;

public interface SupplierRepository extends JpaRepository<SupplierEntity,Long> {
}
