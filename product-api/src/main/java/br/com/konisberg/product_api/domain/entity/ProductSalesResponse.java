package br.com.konisberg.product_api.domain.entity;

import br.com.konisberg.product_api.infra.model.ProductModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSalesResponse {
    private Integer id;
    private String name;
    private Integer quantityAvailable;
    private Date creationDate;
    private Supplier supplier;
    private Category category;
    private List<String> sales;

    public static ProductSalesResponse of (ProductModel product, List<String> sales) {
        var response = new ProductSalesResponse();
        BeanUtils.copyProperties(product, response);
        if (product.getCategory() != null) {
            response.setCategory(Category.of(product.getCategory()));
        }
        if (product.getSupplier() != null) {
            response.setSupplier(Supplier.of(product.getSupplier()));
        }
        if (!sales.isEmpty()) {
            response.setSales(sales);
        }
        return response;
    }
}
