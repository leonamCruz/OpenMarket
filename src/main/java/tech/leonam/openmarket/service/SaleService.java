package tech.leonam.openmarket.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.leonam.openmarket.exception.AmountProductException;
import tech.leonam.openmarket.exception.IdSaleNotFoundException;
import tech.leonam.openmarket.model.dto.SaleResponseDto;
import tech.leonam.openmarket.model.dto.SaleSaveDto;
import tech.leonam.openmarket.model.entity.ProductEntity;
import tech.leonam.openmarket.model.entity.SaleEntity;
import tech.leonam.openmarket.repository.SaleRepository;

import java.util.List;

@Service
public class SaleService {
    private final SaleRepository repository;
    private final ProductService productService;
    public SaleService(SaleRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    @PostMapping
    public SaleResponseDto saveSale(@RequestBody @Valid SaleSaveDto dto) throws AmountProductException {
        var entity = dtoToEntity(dto);
        verifyAmountProduct(entity);

        reduceAmountProduct(entity.getProduct(), entity.getAmount());

        var entitySaved = repository.save(entity);

        return entityToResponse(entitySaved);
    }

    public SaleResponseDto entityToResponse(SaleEntity entity){
        var response = new SaleResponseDto();
        response.setId(entity.getId());
        response.setProduct(entity.getProduct());

        return response;
    }

    private void reduceAmountProduct(ProductEntity product, Long amount) {
        product.setAmount(product.getAmount() - amount);
        productService.update(product);
    }

    private void verifyAmountProduct(SaleEntity entity) throws AmountProductException {
        if(entity.getAmount() > entity.getProduct().getAmount()) throw new AmountProductException("Você está tentando vender mais do que possui no estoque");
    }

    public SaleEntity dtoToEntity(SaleSaveDto dto){
        var entity = new SaleEntity();
        entity.setProduct(searchProductForCodeBar(dto.getCodeBar()));
        entity.setAmount(dto.getAmount());

        return entity;
    }

    public ProductEntity searchProductForCodeBar(String codeBar){
        return productService.findByCodeBarEntity(codeBar);
    }

    public void deleteSale(Long id) throws IdSaleNotFoundException {
        if (repository.existsById(id)) repository.deleteById(id);
        else throw new IdSaleNotFoundException("Id da venda não existe.");
    }

    public List<SaleEntity> getAll() {
        return repository.findAll();
    }

    public SaleResponseDto findById(Long id) {
        return null;
    }

    public SaleResponseDto updateSale(Long id, SaleSaveDto dto) {
        return null;
    }



}
