package tech.leonam.openmarket.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.exception.IdSupplierNotFoundExpection;
import tech.leonam.openmarket.model.dto.SupplierResponseDto;
import tech.leonam.openmarket.model.dto.SupplierSaveDto;
import tech.leonam.openmarket.model.entity.SupplierEntity;
import tech.leonam.openmarket.repository.SupplierRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public SupplierResponseDto save(SupplierSaveDto supplier) {
        var entity = modelMapper.map(supplier,SupplierEntity.class);

        var entitySaved = supplierRepository.save(entity);

        return modelMapper.map(entitySaved, SupplierResponseDto.class);
    }

    public SupplierResponseDto findByIdService(Long id) throws IdSupplierNotFoundExpection {
        var entityFind = supplierRepository.findById(id).orElseThrow(() -> new IdSupplierNotFoundExpection("Id " + id + " not found"));

        return modelMapper.map(entityFind, SupplierResponseDto.class);
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
    public SupplierResponseDto update(Long id, SupplierSaveDto supplier) throws IdSupplierNotFoundExpection {
        verifyIfNotExistsId(id);

        var entity = modelMapper.map(supplier, SupplierEntity.class);
        entity.setId(id);

        var saved = supplierRepository.save(entity);

        return modelMapper.map(saved, SupplierResponseDto.class);
    }

    public void verifyIfNotExistsId(Long id) throws IdSupplierNotFoundExpection {
        if (!supplierRepository.existsById(id)) throw new IdSupplierNotFoundExpection("Id " + id + " não localizado.");
    }

}
