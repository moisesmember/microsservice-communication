package br.com.konisberg.product_api.infra.service;

import br.com.konisberg.product_api.application.form.*;
import br.com.konisberg.product_api.domain.entity.Product;
import br.com.konisberg.product_api.domain.entity.SalesProduct;
import br.com.konisberg.product_api.domain.entity.enums.SalesStatus;
import br.com.konisberg.product_api.domain.repository.ProductGateway;
import br.com.konisberg.product_api.infra.adapter.SalesClient;
import br.com.konisberg.product_api.infra.config.exception.SuccessResponse;
import br.com.konisberg.product_api.infra.config.exception.ValidationException;
import br.com.konisberg.product_api.infra.model.CategoryModel;
import br.com.konisberg.product_api.infra.model.ProductModel;
import br.com.konisberg.product_api.infra.model.SupplierModel;
import br.com.konisberg.product_api.infra.repository.CategoryRepository;
import br.com.konisberg.product_api.infra.repository.ProductRepository;
import br.com.konisberg.product_api.infra.repository.SupplierRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static br.com.konisberg.product_api.infra.config.RequestUtil.getCurrentRequest;

@Service
@AllArgsConstructor
public class ProductService implements ProductGateway {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

//    private final SalesClient salesClient;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Integer ZERO = 0;
    private static final String AUTHORIZATION = "Authorization";
    private static final String TRANSACTION_ID = "transactionid";
    private static final String SERVICE_ID = "serviceid";

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
        product.setCreationDate(new Date());
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
        productFound.setLastModifiedDate(new Date());
        return Product.of(productRepository.save(productFound));
    }

    @Override
    public SuccessResponse delete(Integer id) {
        productRepository.deleteById(id);
        return SuccessResponse.create("The Product was deleted.");
    }

    @Override
    public long count(String search) {
        return productRepository.count();
    }

    @Override
    public boolean existsById(Integer id) {
        return productRepository.existsById(id);
    }

    @Override
    public List<Product> findByNameIgnoreCaseContaining(String name) {
        return Product.ofList(productRepository.findByNameIgnoreCaseContaining(name));
    }

    @Override
    public List<Product> findByCategoryId(Integer categoryId) {
        return Product.ofList(productRepository.findByCategoryId(categoryId));
    }

    @Override
    public List<Product> findBySupplierId(Integer supplierId) {
        return Product.ofList(productRepository.findBySupplierId(supplierId));
    }

    @Override
    public Boolean existsByCategoryId(Integer categoryId) {
        return productRepository.existsByCategoryId(categoryId);
    }

    @Override
    public Boolean existsBySupplierId(Integer supplierId) {
        return productRepository.existsBySupplierId(supplierId);
    }

    @Override
    public void updateProductStock(ProductStockForm productStockForm) {
        updateStock(productStockForm);
    }

    private void updateStock(ProductStockForm productStockForm) {
        List<ProductModel> productsForUpdate = new ArrayList<>();
        productStockForm
                .products()
                .forEach(salesProduct -> {
                    ProductModel existingProduct = productRepository.findById(salesProduct.productId())
                            .orElseThrow(() -> new ValidationException("There's no Product for the given ID."));
                    validateQuantityInStock(salesProduct, existingProduct);
                    existingProduct.updateStock(salesProduct.quantity());
                    existingProduct.setLastModifiedDate(new Date());
                    productsForUpdate.add(existingProduct);
                });
        if (!productsForUpdate.isEmpty()) {
            productRepository.saveAll(productsForUpdate);
            SalesConfirmationForm approvedMessage = new SalesConfirmationForm(productStockForm.salesId(), SalesStatus.APPROVED, productStockForm.transactionid());
            //salesConfirmationSender.sendSalesConfirmationMessage(approvedMessage);
        }
    }

//    private SalesProduct getSalesByProductId(Integer productId) {
//        try {
//            var currentRequest = getCurrentRequest();
//            var token = currentRequest.getHeader(AUTHORIZATION);
//            var transactionId = currentRequest.getHeader(TRANSACTION_ID);
//            var serviceId = currentRequest.getAttribute(SERVICE_ID);
//            log.info("Sending GET request to orders by productId with data {} | [transactionID: {} | serviceID: {}]",
//                    productId, transactionId, serviceId);
//            var response = salesClient
//                    .findSalesByProductId(productId, token, transactionId)
//                    .orElseThrow(() -> new ValidationException("The sales was not found by this product."));
//            log.info("Recieving response from orders by productId with data {} | [transactionID: {} | serviceID: {}]",
//                    objectMapper.writeValueAsString(response), transactionId, serviceId);
//            return response;
//        } catch (Exception ex) {
//            log.error("Error trying to call Sales-API: {}", ex.getMessage());
//            throw new ValidationException("The sales could not be found.");
//        }
//    }

    public SuccessResponse checkProductsStock(ProductCheckStockForm request) {
        try {
            var currentRequest = getCurrentRequest();
            var transactionId = currentRequest.getHeader(TRANSACTION_ID);
            var serviceId = currentRequest.getAttribute(SERVICE_ID);
            log.info("Request to POST product stock with data {} | [transactionID: {} | serviceID: {}]",
                    objectMapper.writeValueAsString(request), transactionId, serviceId);
            var response = SuccessResponse.create("The stock is ok!");
            log.info("Response to POST product stock with data {} | [transactionID: {} | serviceID: {}]",
                    objectMapper.writeValueAsString(response), transactionId, serviceId);
            return response;
        } catch (Exception ex) {
            throw new ValidationException(ex.getMessage());
        }
    }

    private void validateQuantityInStock(ProductQuantityForm salesProductForm,
                                         ProductModel existingProduct) {
        if (salesProductForm.quantity() > existingProduct.getQuantityAvailable()) {
            throw new ValidationException(
                    String.format("The product %s is out of stock.", existingProduct.getId()));
        }
    }
}
