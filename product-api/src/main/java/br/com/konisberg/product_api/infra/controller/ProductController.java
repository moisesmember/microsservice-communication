package br.com.konisberg.product_api.infra.controller;

import br.com.konisberg.product_api.application.dto.ProductDTO;
import br.com.konisberg.product_api.application.form.ProductForm;
import br.com.konisberg.product_api.application.usecase.ProductUseCase;
import br.com.konisberg.product_api.infra.service.ProductService;
import br.com.konisberg.product_api.infra.util.PathRest;
import br.com.konisberg.product_api.infra.util.RouteTag;
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
@Tag(name = RouteTag.PRODUCT, description = "Operações da produto")
public class ProductController {

    @Autowired
    private final ProductService productService;

    @Transactional
    @Operation(summary = "Cadastrar produto", tags = {RouteTag.PRODUCT})
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
    @Operation(summary = "Atualizar produto", tags = {RouteTag.PRODUCT})
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

    @Operation(summary = "Lista de produtos", tags = {RouteTag.PRODUCT})
    @GetMapping
    public ResponseEntity<List<ProductDTO>> searchProductAll() {
        ProductUseCase productUseCase = new ProductUseCase(productService);
        final List<ProductDTO> products = productUseCase.searchAll();
        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "Consultar produto por id", tags = {RouteTag.PRODUCT})
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> searchProductById(@PathVariable Integer id) {
        ProductUseCase productUseCase = new ProductUseCase(productService);
        return ResponseEntity.ok().body(productUseCase.searchById(id));
    }

    @Operation(summary = "Excluir produto por id", tags = {RouteTag.PRODUCT})
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProductById(@PathVariable Integer id) {
        ProductUseCase productUseCase = new ProductUseCase(productService);
        return ResponseEntity.ok().body(productUseCase.delete(id));
    }

    @Operation(summary = "Lista de produtos por nome", tags = {RouteTag.PRODUCT})
    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<ProductDTO>> searchProductAllByName(@PathVariable String name) {
        ProductUseCase productUseCase = new ProductUseCase(productService);
        final List<ProductDTO> products = productUseCase.findByName(name);
        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "Lista de produtos por categoria", tags = {RouteTag.PRODUCT})
    @GetMapping("/findByCategoryId/{categoryId}")
    public ResponseEntity<List<ProductDTO>> searchProductAllByCategoryId(@PathVariable Integer categoryId) {
        ProductUseCase productUseCase = new ProductUseCase(productService);
        final List<ProductDTO> products = productUseCase.findByCategoryId(categoryId);
        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "Lista de produtos por fornecedor", tags = {RouteTag.PRODUCT})
    @GetMapping("/findBySupplierId/{supplierId}")
    public ResponseEntity<List<ProductDTO>> searchProductAllBySupplierId(@PathVariable Integer supplierId) {
        ProductUseCase productUseCase = new ProductUseCase(productService);
        final List<ProductDTO> products = productUseCase.findBySupplierId(supplierId);
        return ResponseEntity.ok().body(products);
    }
}
