package br.com.konisberg.product_api.infra.controller;

import br.com.konisberg.product_api.application.dto.SupplierDTO;
import br.com.konisberg.product_api.application.form.SupplierForm;
import br.com.konisberg.product_api.application.usecase.SupplierUseCase;
import br.com.konisberg.product_api.infra.service.SupplierService;
import br.com.konisberg.product_api.infra.util.PathRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = PathRest.API + PathRest.VERSION + PathRest.SUPPLIER)
@RequiredArgsConstructor
@Tag(name = "Fornecedor", description = "Operações do fornecedor")
public class SupplierController {

    @Autowired
    private final SupplierService supplierService;

    @Transactional
    @Operation(summary = "Cadastrar fornecedor", tags = {"Fornecedor"})
    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@Valid @RequestBody SupplierForm supplierForm) {
        SupplierUseCase supplierUseCase = new SupplierUseCase(supplierService);
        final SupplierDTO supplier = supplierUseCase.create(supplierForm);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(supplier.id())
                .toUri();
        return ResponseEntity.created(uri).body(supplier);
    }

    @Transactional
    @Operation(summary = "Atualizar fornecedor", tags = {"Fornecedor"})
    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable Integer id, @Valid @RequestBody SupplierForm supplierForm) {
        SupplierUseCase supplierUseCase = new SupplierUseCase(supplierService);
        final SupplierDTO supplier = supplierUseCase.update(id, supplierForm);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(supplier.id())
                .toUri();
        return ResponseEntity.created(uri).body(supplier);
    }

    @Operation(summary = "Lista de fornecedores", tags = {"Fornecedor"})
    @GetMapping
    public ResponseEntity<List<SupplierDTO>> searchSupplierAll() {
        SupplierUseCase supplierUseCase = new SupplierUseCase(supplierService);
        final List<SupplierDTO> suppliers = supplierUseCase.searchAll();
        return ResponseEntity.ok().body(suppliers);
    }

    @Operation(summary = "Consultar forncedor por id", tags = {"Fornecedor"})
    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> searchSupplierById(@PathVariable Integer id) {
        SupplierUseCase supplierUseCase = new SupplierUseCase(supplierService);
        return ResponseEntity.ok().body(supplierUseCase.searchById(id));
    }

    @Operation(summary = "Excluir forncedor por id", tags = {"Fornecedor"})
    @DeleteMapping("/{id}")
    public ResponseEntity<SupplierDTO> deleteSupplierById(@PathVariable Integer id) {
        SupplierUseCase supplierUseCase = new SupplierUseCase(supplierService);
        return ResponseEntity.ok().body(supplierUseCase.delete(id));
    }
}
