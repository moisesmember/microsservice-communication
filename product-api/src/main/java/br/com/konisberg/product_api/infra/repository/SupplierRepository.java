package br.com.konisberg.product_api.infra.repository;

import br.com.konisberg.product_api.infra.model.SupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierModel, Integer> {
}
