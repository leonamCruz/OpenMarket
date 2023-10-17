package tech.leonam.openmarket.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.leonam.openmarket.exception.IdBrandNotFoundExpection;
import tech.leonam.openmarket.exception.IdProductNotFoundExpection;
import tech.leonam.openmarket.exception.IdSupplierNotFoundExpection;
import tech.leonam.openmarket.model.dto.ProductResponseDto;
import tech.leonam.openmarket.model.dto.ProductSaveDto;
import tech.leonam.openmarket.model.entity.ProductEntity;
import tech.leonam.openmarket.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@RequestBody @Valid ProductSaveDto product) throws IdSupplierNotFoundExpection, IdBrandNotFoundExpection {
        var entitySaved = service.save(product);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entitySaved.getId()).toUri();

        return ResponseEntity.created(uri).body(entitySaved);
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> findById(@PathVariable Long id) throws IdProductNotFoundExpection {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable Long id, @RequestBody @Valid ProductSaveDto entity) throws IdSupplierNotFoundExpection, IdBrandNotFoundExpection {
        return ResponseEntity.ok(service.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws IdProductNotFoundExpection {
        service.delete(id);
    }

    @GetMapping("/{codeBar}")
    public ResponseEntity<ProductResponseDto> findByCodeBar(@PathVariable String codeBar){
        return ResponseEntity.ok(service.findByCodeBarResponse(codeBar));
    }

}
