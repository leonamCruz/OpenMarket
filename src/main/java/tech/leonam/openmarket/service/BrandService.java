package tech.leonam.openmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.exception.IdBrandNotFoundExpection;
import tech.leonam.openmarket.model.dto.BrandResponseDto;
import tech.leonam.openmarket.model.dto.BrandSaveDto;
import tech.leonam.openmarket.model.entity.BrandEntity;
import tech.leonam.openmarket.repository.BrandRepository;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public BrandResponseDto save(BrandSaveDto brandSaveDto) {
        var entity = new BrandEntity();
        entity.setName(brandSaveDto.getName());

        var entitySaved = brandRepository.save(entity);

        return new BrandResponseDto(entitySaved.getId(), entitySaved.getName());
    }

    public BrandResponseDto findByIdService(Long id) throws IdBrandNotFoundExpection {
        var findEntity = brandRepository.findById(id).orElseThrow(() -> new IdBrandNotFoundExpection("Id " + id + " not found"));

        return new BrandResponseDto(findEntity.getId(), findEntity.getName());
    }

    public BrandEntity findById(Long id) throws IdBrandNotFoundExpection {
        return brandRepository.findById(id).orElseThrow(() -> new IdBrandNotFoundExpection("Id " + id + " not found"));
    }

    public List<BrandEntity> findAll() {
        return brandRepository.findAll();
    }

    public void delete(Long id) throws IdBrandNotFoundExpection {
        verifyIfIdBrandExists(id);
        brandRepository.deleteById(id);
    }

    public BrandResponseDto update(Long id, BrandSaveDto brandSaveDto) throws IdBrandNotFoundExpection {
        var findEntity = brandRepository.findById(id).orElseThrow(() -> new IdBrandNotFoundExpection("Id " + id + " not found"));
        var entity = new BrandEntity();
        entity.setId(findEntity.getId());
        entity.setName(brandSaveDto.getName());

        var entitySaved = brandRepository.save(entity);

        return new BrandResponseDto(entitySaved.getId(), entitySaved.getName());
    }

    private void verifyIfIdBrandExists(Long id) throws IdBrandNotFoundExpection {
        if (!brandRepository.existsById(id)) throw new IdBrandNotFoundExpection("Id " + id + " not found");
    }

}
