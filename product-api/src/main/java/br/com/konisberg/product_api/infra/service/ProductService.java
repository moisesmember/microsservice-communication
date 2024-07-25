package br.com.konisberg.product_api.infra.service;

import br.com.konisberg.product_api.application.form.ProductForm;
import br.com.konisberg.product_api.domain.entity.Product;
import br.com.konisberg.product_api.domain.repository.ProductGateway;
import br.com.konisberg.product_api.infra.config.exception.ValidationException;
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
        return Product.of(productRepository.findById(id).orElseThrow(() -> new ValidationException("There's no Product for the given ID.")));
    }

    @Override
    public Product create(ProductForm param) {
        SupplierModel supplier = supplierRepository.findById(param.getSupplierId())
                .orElseThrow(() -> new ValidationException("There's no Supplier for the given ID."));
        CategoryModel category = categoryRepository.findById(param.getCategoryId())
                .orElseThrow(() -> new ValidationException("There's no Category for the given ID."));
        ProductModel product = new ProductModel();
        product.setId(null);
        product.setName(param.getName());
        product.setSupplier(supplier);
        product.setCategory(category);
        product.setQuantityAvailable(param.getQuantityAvailable());
        return Product.of(productRepository.save(product));
    }

    @Override
    public Product update(Integer id, ProductForm param) {
        ProductModel productFound = productRepository.findById(id)
                .orElseThrow(() -> new ValidationException("There's no Product for the given ID."));
        SupplierModel supplier = supplierRepository.findById(param.getSupplierId())
                .orElseThrow(() -> new ValidationException("There's no Supplier for the given ID."));
        CategoryModel category = categoryRepository.findById(param.getCategoryId())
                .orElseThrow(() -> new ValidationException("There's no Category for the given ID."));
        productFound.setName(param.getName());
        productFound.setSupplier(supplier);
        productFound.setCategory(category);
        productFound.setQuantityAvailable(param.getQuantityAvailable());
        return Product.of(productRepository.save(productFound));
    }

    @Override
    public Product delete(Integer id) {
        ProductModel productFound = productRepository.findById(id)
                .orElseThrow(() -> new ValidationException("There's no Product for the given ID."));
        productRepository.delete(productFound);
        return Product.of(productFound);
    }

    @Override
    public long count(String search) {
        return productRepository.count();
    }

    @Override
    public boolean existsById(Integer id) {
        return productRepository.existsById(id);
    }
}
