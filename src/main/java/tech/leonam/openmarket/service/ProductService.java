package tech.leonam.openmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.exception.IdBrandNotFoundExpection;
import tech.leonam.openmarket.exception.IdProductNotFoundExpection;
import tech.leonam.openmarket.exception.IdSupplierNotFoundExpection;
import tech.leonam.openmarket.model.dto.ProductResponseDto;
import tech.leonam.openmarket.model.dto.ProductSaveDto;
import tech.leonam.openmarket.model.entity.ProductEntity;
import tech.leonam.openmarket.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandService brandService;
    @Autowired
    private SupplierService supplierService;

    public ProductResponseDto save(ProductSaveDto entity) throws IdSupplierNotFoundExpection, IdBrandNotFoundExpection {
        var entityBrand = brandService.findById(entity.getIdBrand());
        var entitySupplier = supplierService.findById(entity.getIdSupplier());

        var entityForSave = dtoToEntity(entity);
        entityForSave.setBrand(entityBrand);
        entityForSave.setSupplier(entitySupplier);

        var entitySaved = productRepository.save(entityForSave);

        return entityToResponse(entitySaved);
    }

    private static ProductEntity dtoToEntity(ProductSaveDto saveDto){
        var productEntity = new ProductEntity();
        productEntity.setName(saveDto.getName());
        productEntity.setAmount(saveDto.getAmount());
        productEntity.setPrice(saveDto.getPrice());
        productEntity.setUnit(saveDto.getUnit());
        productEntity.setCodeBar(saveDto.getCodeBar());
        productEntity.setCategory(saveDto.getCategory());

        return productEntity;
    }

    private static ProductResponseDto entityToResponse(ProductEntity entity){
        var productResponse = new ProductResponseDto();
        productResponse.setId(entity.getId());
        productResponse.setName(entity.getName());
        productResponse.setAmount(entity.getAmount());
        productResponse.setPrice(entity.getPrice());
        productResponse.setUnit(entity.getUnit());
        productResponse.setCodeBar(entity.getCodeBar());
        productResponse.setCategory(entity.getCategory());
        productResponse.setBrand(entity.getBrand());
        productResponse.setSupplier(entity.getSupplier());

        return productResponse;
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

        var entityForSave = dtoToEntity(entity);

        entityForSave.setBrand(entityBrand);
        entityForSave.setSupplier(entitySupplier);
        entity.setIdBrand(id);

        var entitySaved = productRepository.save(entityForSave);

        return entityToResponse(entitySaved);
    }

    public void delete(Long id) throws IdProductNotFoundExpection {
        verifyIfIdProductExists(id);
        productRepository.deleteById(id);
    }

    public void verifyIfIdProductExists(long id) throws IdProductNotFoundExpection {
        if (!productRepository.existsById(id)) throw new IdProductNotFoundExpection("Id " + id + " não localizado.");
    }

}
