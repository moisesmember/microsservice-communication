package br.com.konisberg.product_api.infra.service;

import br.com.konisberg.product_api.application.form.ProductForm;
import br.com.konisberg.product_api.domain.entity.Product;
import br.com.konisberg.product_api.domain.repository.ProductGateway;
import br.com.konisberg.product_api.infra.model.CategoryModel;
import br.com.konisberg.product_api.infra.model.ProductModel;
import br.com.konisberg.product_api.infra.model.SupplierModel;
import br.com.konisberg.product_api.infra.repository.CategoryRepository;
import br.com.konisberg.product_api.infra.repository.ProductRepository;
import br.com.konisberg.product_api.infra.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductGateway {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public List<Product> findAll() {
        return Product.ofList(productRepository.findAll());
    }

    @Override
    public Product findById(Integer id) {
        return Product.of(productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found")));
    }

    @Override
    public Product create(ProductForm param) {
        SupplierModel supplier = supplierRepository.findById(param.getSupplierId())
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
        CategoryModel category = categoryRepository.findById(param.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        ProductModel product = new ProductModel();
        product.setName(param.getName());
        product.setSupplier(supplier);
        product.setCategory(category);
        product.setQuantityAvailable(param.getQuantityAvailable());
        return Product.of(productRepository.save(product));
    }

    @Override
    public Product update(Integer id, ProductForm param) {
        ProductModel productFound = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        SupplierModel supplier = supplierRepository.findById(param.getSupplierId())
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
        CategoryModel category = categoryRepository.findById(param.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        productFound.setName(param.getName());
        productFound.setSupplier(supplier);
        productFound.setCategory(category);
        productFound.setQuantityAvailable(param.getQuantityAvailable());
        return Product.of(productRepository.save(productFound));
    }

    @Override
    public Product delete(Integer id) {
        ProductModel productFound = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        productRepository.delete(productFound);
        return Product.of(productFound);
    }

    @Override
    public long count(String search) {
        return productRepository.count();
    }
}
