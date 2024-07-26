package br.com.konisberg.product_api.infra.controller;

import br.com.konisberg.product_api.application.dto.CategoryDTO;
import br.com.konisberg.product_api.application.dto.SuccessResponseDTO;
import br.com.konisberg.product_api.application.form.CategoryForm;
import br.com.konisberg.product_api.application.usecase.CategoryUseCase;
import br.com.konisberg.product_api.infra.service.CategoryService;
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
@RequestMapping(value = PathRest.API + PathRest.VERSION + PathRest.CATEGORY)
@RequiredArgsConstructor
@Tag(name = RouteTag.CATEGORY, description = "Operações da categoria")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @Transactional
    @Operation(summary = "Cadastrar categoria", tags = {RouteTag.CATEGORY})
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryForm categoryForm) {
        CategoryUseCase categoryUseCase = new CategoryUseCase(categoryService);
        final CategoryDTO category = categoryUseCase.create(categoryForm);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.id())
                .toUri();
        return ResponseEntity.created(uri).body(category);
    }

    @Transactional
    @Operation(summary = "Atualizar categoria", tags = {RouteTag.CATEGORY})
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryForm categoryForm) {
        CategoryUseCase categoryUseCase = new CategoryUseCase(categoryService);
        final CategoryDTO category = categoryUseCase.update(id, categoryForm);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.id())
                .toUri();
        return ResponseEntity.created(uri).body(category);
    }

    @Operation(summary = "Lista de categorias", tags = {RouteTag.CATEGORY})
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> searchCategoryAll() {
        CategoryUseCase categoryUseCase = new CategoryUseCase(categoryService);
        final List<CategoryDTO> categories = categoryUseCase.searchAll();
        return ResponseEntity.ok().body(categories);
    }

    @Operation(summary = "Consultar categoria por id", tags = {RouteTag.CATEGORY})
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> searchCategoryById(@PathVariable Integer id) {
        CategoryUseCase categoryUseCase = new CategoryUseCase(categoryService);
        return ResponseEntity.ok().body(categoryUseCase.searchById(id));
    }

    @Operation(summary = "Excluir categoria por id", tags = {RouteTag.CATEGORY})
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponseDTO> deleteCategoryById(@PathVariable Integer id) {
        CategoryUseCase categoryUseCase = new CategoryUseCase(categoryService);
        return ResponseEntity.ok().body(categoryUseCase.delete(id));
    }
}
