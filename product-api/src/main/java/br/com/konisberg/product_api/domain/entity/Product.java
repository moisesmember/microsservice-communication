package br.com.konisberg.product_api.domain.entity;

import br.com.konisberg.product_api.infra.model.ProductModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class Product {
    private Integer id;
    private String name;
    private Category category;
    private Supplier supplier;
    private Integer quantityAvailable;

    public static Product of (ProductModel product) {
        var response = new Product();
        BeanUtils.copyProperties(product, response);
        if (product.getCategory() != null) {
            response.setCategory(Category.of(product.getCategory()));
        }
        if (product.getSupplier() != null) {
            response.setSupplier(Supplier.of(product.getSupplier()));
        }
        return response;
    }

    public static List<Product> ofList(List<ProductModel> list) {
        return list.stream().map(Product::of).collect(Collectors.toList());
    }
}
