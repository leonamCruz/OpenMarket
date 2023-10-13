package tech.leonam.openmarket.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.exception.IdSupplierNotFoundExpection;
import tech.leonam.openmarket.model.dto.SupplierResponseDto;
import tech.leonam.openmarket.model.dto.SupplierSaveDto;
import tech.leonam.openmarket.model.entity.SupplierEntity;
import tech.leonam.openmarket.repository.SupplierRepository;

import java.util.List;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional
    public SupplierResponseDto save(SupplierSaveDto supplier) {
        var entity = dtoToEntity(supplier);

        var entitySaved = supplierRepository.save(entity);

        return entityToResponse(entitySaved);
    }

    public SupplierResponseDto findByIdService(Long id) throws IdSupplierNotFoundExpection {
        var entityFind = supplierRepository.findById(id).orElseThrow(() -> new IdSupplierNotFoundExpection("Id " + id + " not found"));

        return entityToResponse(entityFind);
    }
    public SupplierEntity findById(Long id) throws IdSupplierNotFoundExpection {
        return supplierRepository.findById(id).orElseThrow(() -> new IdSupplierNotFoundExpection("Id " + id + " not found"));
    }

    public static SupplierResponseDto entityToResponse(SupplierEntity entity){
        var entityResponse = new SupplierResponseDto();
        entityResponse.setId(entity.getId());
        entityResponse.setName(entity.getName());
        entityResponse.setEmail(entity.getEmail());
        entityResponse.setNumberPhone(entity.getNumberPhone());
        entityResponse.setCnpj(entity.getCnpj());
        entityResponse.setAddress(entity.getAddress());

        return entityResponse;
    }
    public static SupplierEntity dtoToEntity(SupplierSaveDto supplier){
        var entity = new SupplierEntity();
        entity.setName(supplier.getName());
        entity.setEmail(supplier.getEmail());
        entity.setNumberPhone(supplier.getNumberPhone());
        entity.setCnpj(supplier.getCnpj());
        entity.setAddress(supplier.getAddress());

        return entity;
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
    public SupplierResponseDto update(Long id, SupplierSaveDto supplier) throws IdSupplierNotFoundExpection {
        verifyIfNotExistsId(id);

        var entity = dtoToEntity(supplier);
        entity.setId(id);

        var saved = supplierRepository.save(entity);

        return entityToResponse(saved);
    }

    public void verifyIfNotExistsId(Long id) throws IdSupplierNotFoundExpection {
        if (!supplierRepository.existsById(id)) throw new IdSupplierNotFoundExpection("Id " + id + " não localizado.");
    }

}
