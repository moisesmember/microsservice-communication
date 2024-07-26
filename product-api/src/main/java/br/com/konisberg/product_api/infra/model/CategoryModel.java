package br.com.konisberg.product_api.infra.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

@Data
@Entity
@Builder
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@Where(clause = "deleted_at is null")
public class CategoryModel extends Auditable{
    @Column(name = "description", nullable = false)
    private String description;
}
