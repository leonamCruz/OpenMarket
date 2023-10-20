package tech.leonam.openmarket.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.exception.IdBrandNotFoundExpection;
import tech.leonam.openmarket.exception.IdProductNotFoundExpection;
import tech.leonam.openmarket.exception.IdSupplierNotFoundExpection;
import tech.leonam.openmarket.model.dto.ProductResponseDto;
import tech.leonam.openmarket.model.dto.ProductSaveDto;
import tech.leonam.openmarket.model.entity.ProductEntity;
import tech.leonam.openmarket.repository.ProductRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final SupplierService supplierService;
    private final ModelMapper modelMapper;

    public ProductResponseDto save(ProductSaveDto entity) throws IdSupplierNotFoundExpection, IdBrandNotFoundExpection {
        var entityBrand = brandService.findById(entity.getIdBrand());
        var entitySupplier = supplierService.findById(entity.getIdSupplier());

        var entityForSave = dtoToEntity(entity);

        entityForSave.setBrand(entityBrand);
        entityForSave.setSupplier(entitySupplier);
        entityForSave.setLocalDateTime(LocalDateTime.now(ZoneId.of("America/Belem")));

        var entitySaved = productRepository.save(entityForSave);

        return modelMapper.map(entitySaved, ProductResponseDto.class);
    }

    public ProductEntity dtoToEntity(ProductSaveDto saveDto){
        var entity = new ProductEntity();
        entity.setName(saveDto.getName());
        entity.setAmount(saveDto.getAmount());
        entity.setPrice(saveDto.getPrice());
        entity.setCategory(saveDto.getCategory());
        entity.setUnit(saveDto.getUnit());
        entity.setCodeBar(saveDto.getCodeBar());

        return entity;
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public ProductEntity findById(Long id) throws IdProductNotFoundExpection {
        return productRepository.findById(id).orElseThrow(()->new IdProductNotFoundExpection("Id " + id + " não localizado."));
    }

    public ProductResponseDto update(Long id, ProductSaveDto entity) throws IdSupplierNotFoundExpection, IdBrandNotFoundExpection {
        var entityBrand = brandService.findById(entity.getIdBrand());
        var entitySupplier = supplierService.findById(entity.getIdSupplier());

        var entityForSave = modelMapper.map(entity, ProductEntity.class);
        entityForSave.setBrand(entityBrand);
        entityForSave.setSupplier(entitySupplier);
        entity.setIdBrand(id);

        var entitySaved = productRepository.save(entityForSave);

        return modelMapper.map(entitySaved, ProductResponseDto.class);
    }

    public ProductResponseDto update(ProductEntity entity){
        return modelMapper.map(productRepository.save(entity), ProductResponseDto.class);
    }

    public void delete(Long id) throws IdProductNotFoundExpection {
        verifyIfIdProductExists(id);
        productRepository.deleteById(id);
    }

    public void verifyIfIdProductExists(long id) throws IdProductNotFoundExpection {
        if (!productRepository.existsById(id)) throw new IdProductNotFoundExpection("Id " + id + " não localizado.");
    }

    public ProductEntity findByCodeBarEntity(String codeBar) {
        return productRepository.findByCodeBar(codeBar);
    }

    public ProductResponseDto findByCodeBarResponse(String codeBar){
        return modelMapper.map(productRepository.findByCodeBar(codeBar), ProductResponseDto.class);
    }

}
