package br.com.konisberg.product_api.application.usecase;

import br.com.konisberg.product_api.application.dto.ProductDTO;
import br.com.konisberg.product_api.application.dto.SuccessResponseDTO;
import br.com.konisberg.product_api.application.form.ProductCheckStockForm;
import br.com.konisberg.product_api.application.form.ProductForm;
import br.com.konisberg.product_api.application.form.ProductQuantityForm;
import br.com.konisberg.product_api.application.form.ProductStockForm;
import br.com.konisberg.product_api.application.interator.ProductInterator;
import br.com.konisberg.product_api.domain.entity.Product;
import br.com.konisberg.product_api.domain.repository.ProductGateway;
import br.com.konisberg.product_api.infra.config.exception.SuccessResponse;
import br.com.konisberg.product_api.infra.config.exception.ValidationException;
import br.com.konisberg.product_api.infra.util.ValidationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Named
public class ProductUseCase implements ProductInterator {

    private final ProductGateway productGateway;

    @Autowired
    private ObjectMapper objectMapper;

    public ProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @SneakyThrows
    @Transactional
    @Override
    public ProductDTO create(ProductForm productForm) {
        ValidationUtils.validateNotEmpty(productForm.getName(), "product's name");
        ValidationUtils.validateNotEmpty(String.valueOf(productForm.getQuantityAvailable()), "product's quantity");
        ValidationUtils.validateNotEmpty(String.valueOf(productForm.getCategoryId()), "product's category id");
        ValidationUtils.validateNotEmpty(String.valueOf(productForm.getSupplierId()), "product's supplier");
        ValidationUtils.validatePositiveNumber(productForm.getQuantityAvailable(), "product's quantity");
        return ProductDTO.from(productGateway.create(productForm));
    }

    @SneakyThrows
    @Transactional
    @Override
    public ProductDTO update(Integer id, ProductForm productForm) {
        ValidationUtils.validateNotEmpty(String.valueOf(id), "product's id");
        ValidationUtils.validateNotEmpty(productForm.getName(), "product's name");
        ValidationUtils.validateNotEmpty(String.valueOf(productForm.getQuantityAvailable()), "product's quantity");
        ValidationUtils.validateNotEmpty(String.valueOf(productForm.getCategoryId()), "product's category id");
        ValidationUtils.validateNotEmpty(String.valueOf(productForm.getSupplierId()), "product's supplier");
        ValidationUtils.validatePositiveNumber(productForm.getQuantityAvailable(), "product's quantity");
        return ProductDTO.from(productGateway.update(id, productForm));
    }

    @Override
    public ProductDTO searchById(Integer id) {
        ValidationUtils.validateNotEmpty(String.valueOf(id), "product's id");
        return ProductDTO.from(productGateway.findById(id));
    }

    @Override
    public List<ProductDTO> searchAll() {
        return ProductDTO.fromList(productGateway.findAll());
    }

    @SneakyThrows
    @Transactional
    @Override
    public SuccessResponseDTO delete(Integer id) {
        ValidationUtils.validateNotEmpty(String.valueOf(id), "product's id");
        return SuccessResponseDTO.from(productGateway.delete(id));
    }

    @Override
    public List<ProductDTO> findByName(String name) {
        ValidationUtils.validateNotEmpty(name, "product's name");
        return ProductDTO.fromList(productGateway.findByNameIgnoreCaseContaining(name));
    }

    @Override
    public List<ProductDTO> findByCategoryId(Integer categoryId) {
        ValidationUtils.validateNotEmpty(String.valueOf(categoryId), "category's id");
        return ProductDTO.fromList(productGateway.findByCategoryId(categoryId));
    }

    @Override
    public List<ProductDTO> findBySupplierId(Integer supplierId) {
        ValidationUtils.validateNotEmpty(String.valueOf(supplierId), "supplier's id");
        return ProductDTO.fromList(productGateway.findBySupplierId(supplierId));
    }

    @Transactional
    public void updateProductStock(ProductStockForm productStockForm) {
        ValidationUtils.validateNotEmpty(productStockForm.salesId(), "sale's id");
//        ValidationUtils.validateNotEmpty(productStockForm.transactionId(), "transaction's id");
        productStockForm.products().forEach(
                productQuantityForm -> {
                    ValidationUtils.validateNotEmpty(String.valueOf(productQuantityForm.productId()), "product's id");
                    ValidationUtils.validateNotEmpty(String.valueOf(productQuantityForm.quantity()), "quantity's product");
                }
        );
        productGateway.updateProductStock(productStockForm);
    }

    public SuccessResponse checkProductsStock(ProductCheckStockForm request){
        if (isEmpty(request) || isEmpty(request.products())) {
            throw new ValidationException("The request data and products must be informed.");
        }
        request
                .products()
                .forEach(this::validateStock);
        return productGateway.checkProductsStock(request);
    }

    private void validateStock(ProductQuantityForm productQuantityForm) {
        ValidationUtils.validateNotEmpty(String.valueOf(productQuantityForm.productId()), "product's id must be informed.");
        ValidationUtils.validateNotEmpty(String.valueOf(productQuantityForm.quantity()), "product's quantity must be informed.");
        Product product = productGateway.findById(productQuantityForm.productId());
        ValidationUtils.validateNumberIsGreaterThan(
                productQuantityForm.quantity(), product.getQuantityAvailable(),
                String.format("The product %s is out of stock.", product.getId()));
    }
}
