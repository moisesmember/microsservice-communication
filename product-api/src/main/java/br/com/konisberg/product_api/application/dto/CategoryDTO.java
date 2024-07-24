package br.com.konisberg.product_api.application.dto;

import br.com.konisberg.product_api.domain.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CategoryDTO(
        Integer id, String description
) {
    public static CategoryDTO from(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getDescription()
        );
    }

    public static List<CategoryDTO> fromList(List<Category> list) {
        return list.stream().map(CategoryDTO::from).collect(Collectors.toList());
    }
}
