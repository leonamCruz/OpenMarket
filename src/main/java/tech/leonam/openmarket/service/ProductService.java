package tech.leonam.openmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.leonam.openmarket.exception.IdBrandNotFoundExpection;
import tech.leonam.openmarket.exception.IdProductNotFoundExpection;
import tech.leonam.openmarket.exception.IdSupplierNotFoundExpection;
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

    public ProductEntity save(ProductEntity entity) throws IdSupplierNotFoundExpection, IdBrandNotFoundExpection {
        var entityBrand = brandService.findById(entity.getBrandId());
        var entitySupplier = supplierService.findById(entity.getSupplierId());

        entity.setBrand(entityBrand);
        entity.setSupplier(entitySupplier);

        return productRepository.save(entity);
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public ProductEntity findById(Long id) throws IdProductNotFoundExpection {
        return productRepository.findById(id).orElseThrow(()->new IdProductNotFoundExpection("Id " + id + " not found"));
    }

    public ProductEntity update(Long id, ProductEntity entity) throws IdProductNotFoundExpection, IdSupplierNotFoundExpection, IdBrandNotFoundExpection {
        verifyIfIdProductExists(id);

        var entityRepository = findById(id);
        entity.setId(entityRepository.getId());

        return save(entity);
    }

    public void delete(Long id) throws IdProductNotFoundExpection {
        verifyIfIdProductExists(id);
        productRepository.deleteById(id);
    }

    public void verifyIfIdProductExists(long id) throws IdProductNotFoundExpection {
        if (!productRepository.existsById(id)) throw new IdProductNotFoundExpection("Id " + id + " not found");
    }

}
