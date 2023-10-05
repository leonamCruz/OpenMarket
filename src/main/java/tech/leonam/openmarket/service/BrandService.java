package tech.leonam.openmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.exception.IdBrandNotFoundExpection;
import tech.leonam.openmarket.model.entity.BrandEntity;
import tech.leonam.openmarket.repository.BrandRepository;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public BrandEntity save(BrandEntity entity){
        return brandRepository.save(entity);
    }

    public BrandEntity findById(Long id) throws IdBrandNotFoundExpection {
        return brandRepository.findById(id).orElseThrow(()-> new IdBrandNotFoundExpection("Id " + id + " not found"));
    }

    public List<BrandEntity> findAll() {
        return brandRepository.findAll();
    }

    public void delete(Long id) throws IdBrandNotFoundExpection {
        verifyIfIdBrandExists(id);
        brandRepository.deleteById(id);
    }

    public BrandEntity update(Long id, BrandEntity entity) throws IdBrandNotFoundExpection {
        verifyIfIdBrandExists(id);

        var entityRepository = findById(id);
        entity.setId(entityRepository.getId());

        return save(entity);
    }

    private void verifyIfIdBrandExists(Long id) throws IdBrandNotFoundExpection {
        if (!brandRepository.existsById(id)) throw new IdBrandNotFoundExpection("Id " + id + " not found");
    }

}
