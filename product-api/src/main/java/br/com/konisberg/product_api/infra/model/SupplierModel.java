package br.com.konisberg.product_api.infra.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Data
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Table(name = "supplier")
@Where(clause = "deleted_at is null")
public class SupplierModel extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;
}
