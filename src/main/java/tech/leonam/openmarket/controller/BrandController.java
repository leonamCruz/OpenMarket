package tech.leonam.openmarket.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.leonam.openmarket.exception.IdBrandNotFoundExpection;
import tech.leonam.openmarket.model.dto.BrandResponseDto;
import tech.leonam.openmarket.model.dto.BrandSaveDto;
import tech.leonam.openmarket.model.entity.BrandEntity;
import tech.leonam.openmarket.service.BrandService;

import java.util.List;

@PreAuthorize("hasRole('ROLE_MANAGER')")
@RestController
@RequestMapping("api/brand")
@AllArgsConstructor
public class BrandController {
    private final BrandService service;

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

