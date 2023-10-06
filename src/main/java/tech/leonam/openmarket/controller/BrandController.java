package tech.leonam.openmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import tech.leonam.openmarket.model.entity.BrandEntity;
import tech.leonam.openmarket.service.BrandService;

import java.util.List;

@RestController
@RequestMapping("api/brand")
public class BrandController {

    @Autowired
    private BrandService service;

    @PostMapping
    public ResponseEntity<BrandEntity> save(@RequestBody BrandEntity brand){
        var entitySaved = service.save(brand);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entitySaved.getId()).toUri();

        return ResponseEntity.created(uri).body(entitySaved);
    }

    @GetMapping
    public ResponseEntity<List<BrandEntity>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandEntity> findById(@PathVariable Long id) throws IdBrandNotFoundExpection {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws IdBrandNotFoundExpection {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandEntity> update(@PathVariable Long id, @RequestBody BrandEntity entity) throws IdBrandNotFoundExpection {
        return ResponseEntity.ok(service.update(id, entity));
    }

}