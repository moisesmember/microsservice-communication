package br.com.konisberg.product_api.infra.controller;

import br.com.konisberg.product_api.application.dto.ProductDTO;
import br.com.konisberg.product_api.application.form.ProductForm;
import br.com.konisberg.product_api.application.usecase.ProductUseCase;
import br.com.konisberg.product_api.infra.service.ProductService;
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
@RequestMapping(value = PathRest.API + PathRest.VERSION + PathRest.PRODUCT)
@RequiredArgsConstructor
@Tag(name = "Produto", description = "Operações da produto")
public class ProductController {

    @Autowired
    private final ProductService productService;

    @Transactional
    @Operation(summary = "Cadastrar produto", tags = {"Produto"})
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductForm productForm) {
        ProductUseCase productUseCase = new ProductUseCase(productService);
        final ProductDTO product = productUseCase.create(productForm);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.id())
                .toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @Transactional
    @Operation(summary = "Atualizar produto", tags = {"Produto"})
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductForm productForm) {
        ProductUseCase productUseCase = new ProductUseCase(productService);
        final ProductDTO product = productUseCase.update(id, productForm);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.id())
                .toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @Operation(summary = "Lista de produtos", tags = {"Produto"})
    @GetMapping
    public ResponseEntity<List<ProductDTO>> searchProductAll() {
        ProductUseCase productUseCase = new ProductUseCase(productService);
        final List<ProductDTO> products = productUseCase.searchAll();
        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "Consultar produto por id", tags = {"Produto"})
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> searchProductById(@PathVariable Integer id) {
        ProductUseCase productUseCase = new ProductUseCase(productService);
        return ResponseEntity.ok().body(productUseCase.searchById(id));
    }

    @Operation(summary = "Excluir produto por id", tags = {"Produto"})
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProductById(@PathVariable Integer id) {
        ProductUseCase productUseCase = new ProductUseCase(productService);
        return ResponseEntity.ok().body(productUseCase.delete(id));
    }
}
