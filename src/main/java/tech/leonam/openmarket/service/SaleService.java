package tech.leonam.openmarket.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
@AllArgsConstructor
public class SaleService {
    private final SaleRepository repository;
    private final ProductService productService;
    private final ModelMapper modelMapper;
    @PostMapping
    public SaleResponseDto saveSale(@RequestBody @Valid SaleSaveDto dto) throws AmountProductException {
        var product = searchProductForCodeBar(dto.getCodeBar());

        var entity = modelMapper.map(dto, SaleEntity.class);
        entity.setProduct(product);

        verifyAmountProduct(entity);

        reduceAmountProduct(entity.getProduct(), entity.getAmount());

        return modelMapper.map(repository.save(entity), SaleResponseDto.class);
    }
    private void reduceAmountProduct(ProductEntity product, Long amount) {
        product.setAmount(product.getAmount() - amount);
        productService.update(product);
    }

    private void verifyAmountProduct(SaleEntity entity) throws AmountProductException {
        if(entity.getAmount() > entity.getProduct().getAmount()) throw new AmountProductException("Você está tentando vender mais do que possui no estoque");
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
        return modelMapper.map(repository.findById(id), SaleResponseDto.class);
    }

    public SaleResponseDto updateSale(Long id, SaleSaveDto dto) {
        return null;
    }
}
