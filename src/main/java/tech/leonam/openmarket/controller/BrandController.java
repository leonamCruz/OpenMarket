package tech.leonam.openmarket.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.leonam.openmarket.exception.IdBrandNotFoundExpection;
import tech.leonam.openmarket.model.dto.BrandResponseDto;
import tech.leonam.openmarket.model.dto.BrandSaveDto;
import tech.leonam.openmarket.model.entity.BrandEntity;
import tech.leonam.openmarket.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("api/brand")
@RequiredArgsConstructor
public class BrandController {

    private BrandService service;

    @PostMapping
    public ResponseEntity<BrandResponseDto> save(@RequestBody @Valid BrandSaveDto brand){
        var entitySaved = service.save(brand);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entitySaved.getId()).toUri();

        return ResponseEntity.created(uri).body(entitySaved);
    }

    @GetMapping
    public ResponseEntity<List<BrandEntity>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponseDto> findById(@PathVariable Long id) throws IdBrandNotFoundExpection {
        return ResponseEntity.ok(service.findByIdService(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws IdBrandNotFoundExpection {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponseDto> update(@PathVariable Long id, @RequestBody @Valid BrandSaveDto entity) throws IdBrandNotFoundExpection {
        return ResponseEntity.ok(service.update(id, entity));
    }

}
