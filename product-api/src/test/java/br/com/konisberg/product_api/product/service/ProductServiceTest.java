package br.com.konisberg.product_api.product.service;

import br.com.konisberg.product_api.application.form.ProductForm;
import br.com.konisberg.product_api.domain.entity.Product;
import br.com.konisberg.product_api.infra.model.CategoryModel;
import br.com.konisberg.product_api.infra.model.ProductModel;
import br.com.konisberg.product_api.infra.model.SupplierModel;
import br.com.konisberg.product_api.infra.repository.CategoryRepository;
import br.com.konisberg.product_api.infra.repository.ProductRepository;
import br.com.konisberg.product_api.infra.repository.SupplierRepository;
import br.com.konisberg.product_api.infra.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@Tag("unit")
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        ProductModel product1 = new ProductModel();
        ProductModel product2 = new ProductModel();
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.findAll();
        assertEquals(2, products.size());
    }

    @Test
    void testFindById() {
        ProductModel product = new ProductModel();
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Product foundProduct = productService.findById(1);
        assertNotNull(foundProduct);
    }

    @Test
    void testCreate() {
        ProductForm form = new ProductForm();
        form.setName("Product A");
        form.setSupplierId(1);
        form.setCategoryId(1);
        form.setQuantityAvailable(10);

        ProductModel product = new ProductModel();
        product.setName("Product A");
        product.setSupplier(new SupplierModel());
        product.setCategory(new CategoryModel());
        product.setQuantityAvailable(10);

        when(supplierRepository.findById(1)).thenReturn(Optional.of(new SupplierModel()));
        when(categoryRepository.findById(1)).thenReturn(Optional.of(new CategoryModel()));
        when(productRepository.save(any(ProductModel.class))).thenReturn(product);

        Product createdProduct = productService.create(form);
        assertNotNull(createdProduct);
        assertEquals("Product A", createdProduct.getName());
    }

    @Test
    void testUpdate() {
        ProductForm form = new ProductForm();
        form.setName("Product B");
        form.setSupplierId(1);
        form.setCategoryId(1);
        form.setQuantityAvailable(20);

        ProductModel product = new ProductModel();
        product.setName("Product A");
        product.setSupplier(new SupplierModel());
        product.setCategory(new CategoryModel());
        product.setQuantityAvailable(10);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        when(supplierRepository.findById(1)).thenReturn(Optional.of(new SupplierModel()));
        when(categoryRepository.findById(1)).thenReturn(Optional.of(new CategoryModel()));
        when(productRepository.save(any(ProductModel.class))).thenReturn(product);

        Product updatedProduct = productService.update(1, form);
        assertNotNull(updatedProduct);
        assertEquals("Product B", updatedProduct.getName());
    }

    @Test
    void testDelete() {
        ProductModel product = new ProductModel();
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        Product deletedProduct = productService.delete(1);
        assertNotNull(deletedProduct);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void testCount() {
        when(productRepository.count()).thenReturn(10L);

        long count = productService.count("");
        assertEquals(10, count);
    }
}
