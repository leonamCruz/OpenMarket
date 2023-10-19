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
import tech.leonam.openmarket.exception.AmountProductException;
import tech.leonam.openmarket.exception.IdSaleNotFoundException;
import tech.leonam.openmarket.model.dto.SaleResponseDto;
import tech.leonam.openmarket.model.dto.SaleSaveDto;
import tech.leonam.openmarket.model.entity.SaleEntity;
import tech.leonam.openmarket.service.SaleService;

import java.util.List;

@RestController
@RequestMapping("api/sale")
@AllArgsConstructor
public class SaleController {

    private final SaleService service;

    @PreAuthorize("hasRole('ROLE_CASHIER')")
    @PostMapping
    public ResponseEntity<SaleResponseDto> saveSale(@RequestBody @Valid SaleSaveDto dto) throws AmountProductException {
        var entitySaved = service.saveSale(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entitySaved.getId()).toUri();

        return ResponseEntity.created(uri).body(entitySaved);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable Long id) throws IdSaleNotFoundException {
        service.deleteSale(id);
    }

    @PreAuthorize("hasRole('ROLE_CASHIER')")
    @GetMapping
    public ResponseEntity<List<SaleEntity>> findAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PreAuthorize("hasRole('ROLE_CASHIER')")
    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<SaleResponseDto> updateSale(@PathVariable Long id, @RequestBody @Valid SaleSaveDto dto) throws AmountProductException {
        return ResponseEntity.ok(service.updateSale(id, dto));
    }

}
