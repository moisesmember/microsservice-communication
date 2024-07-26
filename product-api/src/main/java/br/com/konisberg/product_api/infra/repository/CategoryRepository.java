package br.com.konisberg.product_api.infra.repository;

import br.com.konisberg.product_api.infra.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {
}
