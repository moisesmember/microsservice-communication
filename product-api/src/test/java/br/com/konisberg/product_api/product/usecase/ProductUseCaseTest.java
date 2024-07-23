package br.com.konisberg.product_api.product.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.com.konisberg.product_api.application.dto.CategoryDTO;
import br.com.konisberg.product_api.application.dto.ProductDTO;
import br.com.konisberg.product_api.application.dto.SupplierDTO;
import br.com.konisberg.product_api.application.form.ProductForm;
import br.com.konisberg.product_api.application.usecase.ProductUseCase;
import br.com.konisberg.product_api.domain.entity.Product;
import br.com.konisberg.product_api.domain.repository.ProductGateway;
import br.com.konisberg.product_api.domain.service.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.annotation.Transactional;

public class ProductUseCaseTest {

    @Mock
    private ProductGateway productGateway;

    @Mock
    private Mapper<ProductDTO, ProductForm, Product> mapper;

    @InjectMocks
    private ProductUseCase productUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        ProductForm form = new ProductForm();
        form.setName("Product A");
        form.setQuantityAvailable(10);
        form.setCategoryId(1);
        form.setSupplierId(1);

        Product product = new Product();
        CategoryDTO categoryDTO = new CategoryDTO(1, "Category A");
        SupplierDTO supplierDTO = new SupplierDTO(1, "Supplier A");
        ProductDTO productDTO = new ProductDTO(1, "Product A", categoryDTO, supplierDTO, 10);

        when(productGateway.create(any(ProductForm.class))).thenReturn(product);
        when(mapper.domainToDto(any(Product.class))).thenReturn(productDTO);

        ProductDTO result = productUseCase.create(form);

        assertNotNull(result);
        assertEquals("Product A", result.name());
        verify(productGateway).create(form);
        verify(mapper).domainToDto(product);
    }

    @Test
    void testUpdate() {
        Integer id = 1;
        ProductForm form = new ProductForm();
        form.setName("Product B");
        form.setQuantityAvailable(20);
        form.setCategoryId(1);
        form.setSupplierId(1);

        Product product = new Product();
        CategoryDTO categoryDTO = new CategoryDTO(1, "Category B");
        SupplierDTO supplierDTO = new SupplierDTO(1, "Supplier B");
        ProductDTO productDTO = new ProductDTO(1, "Product B", categoryDTO, supplierDTO, 20);

        when(productGateway.update(eq(id), any(ProductForm.class))).thenReturn(product);
        when(mapper.domainToDto(any(Product.class))).thenReturn(productDTO);

        ProductDTO result = productUseCase.update(id, form);

        assertNotNull(result);
        assertEquals("Product B", result.name());
        verify(productGateway).update(id, form);
        verify(mapper).domainToDto(product);
    }

    @Test
    void testSearchById() {
        Integer id = 1;
        Product product = new Product();
        CategoryDTO categoryDTO = new CategoryDTO(1, "Category A");
        SupplierDTO supplierDTO = new SupplierDTO(1, "Supplier A");
        ProductDTO productDTO = new ProductDTO(1, "Product A", categoryDTO, supplierDTO, 10);

        when(productGateway.findById(id)).thenReturn(product);
        when(mapper.domainToDto(any(Product.class))).thenReturn(productDTO);

        ProductDTO result = productUseCase.searchById(id);

        assertNotNull(result);
        assertEquals("Product A", result.name());
        verify(productGateway).findById(id);
        verify(mapper).domainToDto(product);
    }

    @Test
    void testSearchAll() {
        Product product = new Product();
        CategoryDTO categoryDTO = new CategoryDTO(1, "Category A");
        SupplierDTO supplierDTO = new SupplierDTO(1, "Supplier A");
        ProductDTO productDTO = new ProductDTO(1, "Product A", categoryDTO, supplierDTO, 10);
        List<Product> productList = Collections.singletonList(product);
        List<ProductDTO> productDTOList = Collections.singletonList(productDTO);

        when(productGateway.findAll()).thenReturn(productList);
        when(mapper.domainListToDtoList(anyList())).thenReturn(productDTOList);

        List<ProductDTO> result = productUseCase.searchAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Product A", result.get(0).name());
        verify(productGateway).findAll();
        verify(mapper).domainListToDtoList(productList);
    }

    @Test
    void testDelete() {
        Integer id = 1;
        Product product = new Product();
        CategoryDTO categoryDTO = new CategoryDTO(1, "Category A");
        SupplierDTO supplierDTO = new SupplierDTO(1, "Supplier A");
        ProductDTO productDTO = new ProductDTO(1, "Product A", categoryDTO, supplierDTO, 10);

        when(productGateway.delete(id)).thenReturn(product);
        when(mapper.domainToDto(any(Product.class))).thenReturn(productDTO);

        ProductDTO result = productUseCase.delete(id);

        assertNotNull(result);
        assertEquals("Product A", result.name());
        verify(productGateway).delete(id);
        verify(mapper).domainToDto(product);
    }
}

