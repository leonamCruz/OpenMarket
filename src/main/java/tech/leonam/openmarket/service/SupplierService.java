package tech.leonam.openmarket.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.exception.IdSupplierNotFoundExpection;
import tech.leonam.openmarket.model.entity.SupplierEntity;
import tech.leonam.openmarket.repository.SupplierRepository;

import java.util.List;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Transactional
    public SupplierEntity save(SupplierEntity supplier) {
        return supplierRepository.save(supplier);
    }

    public SupplierEntity findById(Long id) throws IdSupplierNotFoundExpection {
        return supplierRepository.findById(id).orElseThrow(() -> new IdSupplierNotFoundExpection("Id " + id + " not found"));
    }

    public List<SupplierEntity> findAll() {
        return supplierRepository.findAll();
    }

    @Transactional
    public void delete(Long id) throws IdSupplierNotFoundExpection {
        verifyIfNotExistsId(id);
        supplierRepository.deleteById(id);
    }

    @Transactional
    public SupplierEntity update(Long id, SupplierEntity entity) throws IdSupplierNotFoundExpection {
        verifyIfNotExistsId(id);
        entity.setId(id);

        return save(entity);
    }

    public void verifyIfNotExistsId(Long id) throws IdSupplierNotFoundExpection {
        if (!supplierRepository.existsById(id)) throw new IdSupplierNotFoundExpection("Id " + id + " not found");
    }

}
