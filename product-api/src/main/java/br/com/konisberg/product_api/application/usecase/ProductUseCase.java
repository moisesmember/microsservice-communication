package br.com.konisberg.product_api.application.usecase;

import br.com.konisberg.product_api.application.dto.ProductDTO;
import br.com.konisberg.product_api.application.form.ProductForm;
import br.com.konisberg.product_api.application.interator.ProductInterator;
import br.com.konisberg.product_api.domain.entity.Product;
import br.com.konisberg.product_api.domain.repository.ProductGateway;
import br.com.konisberg.product_api.domain.service.Mapper;
import br.com.konisberg.product_api.infra.util.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Named
@RequiredArgsConstructor
public class ProductUseCase implements ProductInterator {

    private final ProductGateway productGateway;
    private final Mapper<ProductDTO, ProductForm, Product> mapper;

    @SneakyThrows
    @Transactional
    @Override
    public ProductDTO create(ProductForm productForm) {
        ValidationUtils.validateNotEmpty(productForm.getName(), "product's name");
        ValidationUtils.validateNotEmpty(String.valueOf(productForm.getQuantityAvailable()), "product's quantity");
        ValidationUtils.validateNotEmpty(String.valueOf(productForm.getCategoryId()), "product's category id");
        ValidationUtils.validateNotEmpty(String.valueOf(productForm.getSupplierId()), "product's supplier");
        ValidationUtils.validatePositiveNumber(productForm.getQuantityAvailable(), "product's quantity");
        return mapper.domainToDto(productGateway.create(productForm));
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
        return mapper.domainToDto(productGateway.update(id, productForm));
    }

    @Override
    public ProductDTO searchById(Integer id) {
        ValidationUtils.validateNotEmpty(String.valueOf(id), "product's id");
        return mapper.domainToDto(productGateway.findById(id));
    }

    @Override
    public List<ProductDTO> searchAll() {
        return mapper.domainListToDtoList(productGateway.findAll());
    }

    @SneakyThrows
    @Transactional
    @Override
    public ProductDTO delete(Integer id) {
        ValidationUtils.validateNotEmpty(String.valueOf(id), "product's id");
        return mapper.domainToDto(productGateway.delete(id));
    }
}
