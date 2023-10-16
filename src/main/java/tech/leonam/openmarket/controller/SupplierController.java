package tech.leonam.openmarket.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.leonam.openmarket.exception.IdSupplierNotFoundExpection;
import tech.leonam.openmarket.model.dto.SupplierResponseDto;
import tech.leonam.openmarket.model.dto.SupplierSaveDto;
import tech.leonam.openmarket.model.entity.SupplierEntity;
import tech.leonam.openmarket.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SupplierResponseDto> save(@RequestBody @Valid SupplierSaveDto dto){
        var entitySaved = service.save(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entitySaved.getId()).toUri();

        return ResponseEntity.created(uri).body(entitySaved);
    }
    @GetMapping
    public ResponseEntity<List<SupplierEntity>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDto> findById(@PathVariable Long id) throws IdSupplierNotFoundExpection {
        return ResponseEntity.ok(service.findByIdService(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws IdSupplierNotFoundExpection {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDto> update(@PathVariable Long id, @RequestBody @Valid SupplierSaveDto entity) throws IdSupplierNotFoundExpection {
        return ResponseEntity.ok(service.update(id,entity));
    }
}
