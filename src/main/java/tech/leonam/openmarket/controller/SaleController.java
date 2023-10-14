package tech.leonam.openmarket.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.leonam.openmarket.model.dto.SaleResponseDto;
import tech.leonam.openmarket.model.dto.SaleSaveDto;
import tech.leonam.openmarket.model.entity.SaleEntity;
import tech.leonam.openmarket.service.SaleService;

import java.util.List;

@RestController
@RequestMapping("api/sale")
public class SaleController {

    private final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SaleResponseDto> saveSale(@RequestBody @Valid SaleSaveDto dto){
        var entitySaved = service.saveSale(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entitySaved.getId()).toUri();

        return ResponseEntity.created(uri).body(entitySaved);
    }

    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable Long id){
        service.deleteSale(id);
    }

    @GetMapping
    public ResponseEntity<List<SaleEntity>> findAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponseDto> updateSale(@PathVariable Long id, @RequestBody @Valid SaleSaveDto dto){
        return ResponseEntity.ok(service.updateSale(id, dto));
    }

}
