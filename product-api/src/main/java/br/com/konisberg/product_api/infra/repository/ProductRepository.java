package br.com.konisberg.product_api.infra.repository;

import br.com.konisberg.product_api.infra.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {

    List<ProductModel> findByNameIgnoreCaseContaining(String name);

    List<ProductModel> findByCategoryId(Integer id);

    List<ProductModel> findBySupplierId(Integer id);

    Boolean existsByCategoryId(Integer id);

    Boolean existsBySupplierId(Integer id);

}
