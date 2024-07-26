package br.com.konisberg.product_api.infra.model;

import br.com.konisberg.product_api.domain.entity.Category;
import br.com.konisberg.product_api.domain.entity.Product;
import br.com.konisberg.product_api.domain.entity.Supplier;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Where(clause = "deleted_at is null")
public class ProductModel extends Auditable{

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryModel category;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierModel supplier;

    @Column(name = "quantity_available", nullable = false)
    private Integer quantityAvailable;

    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

    public static ProductModel of(Product product, SupplierModel supplier, CategoryModel category) {
        return ProductModel
                .builder()
                .name(product.getName())
                .quantityAvailable(product.getQuantityAvailable())
                .supplier(supplier)
                .category(category)
                .build();
    }
}
