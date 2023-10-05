package tech.leonam.openmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.leonam.openmarket.exception.IdSupplierNotFoundExpection;
import tech.leonam.openmarket.model.entity.SupplierEntity;
import tech.leonam.openmarket.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    private SupplierService service;
    @PostMapping
    public ResponseEntity<SupplierEntity> save(@RequestBody SupplierEntity entity){
        var entitySaved = service.save(entity);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entitySaved.getId()).toUri();

        return ResponseEntity.created(uri).body(entitySaved);
    }
    @GetMapping
    public ResponseEntity<List<SupplierEntity>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierEntity> findById(@PathVariable Long id) throws IdSupplierNotFoundExpection {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws IdSupplierNotFoundExpection {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierEntity> update(@PathVariable Long id, @RequestBody SupplierEntity entity) throws IdSupplierNotFoundExpection {
        return ResponseEntity.ok(service.update(id,entity));
    }
}
