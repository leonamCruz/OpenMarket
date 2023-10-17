package tech.leonam.openmarket.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.exception.IdBrandNotFoundExpection;
import tech.leonam.openmarket.model.dto.BrandResponseDto;
import tech.leonam.openmarket.model.dto.BrandSaveDto;
import tech.leonam.openmarket.model.entity.BrandEntity;
import tech.leonam.openmarket.repository.BrandRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public BrandResponseDto save(BrandSaveDto brandSaveDto) {
        var entitySaved = brandRepository.save(modelMapper.map(brandSaveDto,BrandEntity.class));

        return modelMapper.map(entitySaved, BrandResponseDto.class);
    }

    public BrandResponseDto findByIdService(Long id) throws IdBrandNotFoundExpection {
        var findEntity = brandRepository.findById(id).orElseThrow(() -> new IdBrandNotFoundExpection("Id " + id + " não localizado."));

        return new BrandResponseDto(findEntity.getId(), findEntity.getName());
    }

    public BrandEntity findById(Long id) throws IdBrandNotFoundExpection {
        return brandRepository.findById(id).orElseThrow(() -> new IdBrandNotFoundExpection("Id " + id + " não localizado."));
    }

    public List<BrandEntity> findAll() {
        return brandRepository.findAll();
    }

    public void delete(Long id) throws IdBrandNotFoundExpection {
        verifyIfIdBrandExists(id);
        brandRepository.deleteById(id);
    }

    public BrandResponseDto update(Long id, BrandSaveDto brandSaveDto) throws IdBrandNotFoundExpection {
        verifyIfIdBrandExists(id);

        var entity = modelMapper.map(brandSaveDto,BrandEntity.class);
        entity.setId(id);

        var entitySaved = brandRepository.save(entity);

        return modelMapper.map(entitySaved, BrandResponseDto.class);
    }

    private void verifyIfIdBrandExists(Long id) throws IdBrandNotFoundExpection {
        if (!brandRepository.existsById(id)) throw new IdBrandNotFoundExpection("Id " + id + " não localizado.");
    }

}
