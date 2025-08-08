package org.example.aletheiacares;

import org.springframework.stereotype.Component;

    @Component
    public class CategoryMapper {

        /**
         * Convert a Category entity to its DTO representation.
         */
        public CategoryDto toDto(Category category) {
            if (category == null) {
                return null;
            }
            return new CategoryDto(category.getId(), category.getName());
        }

        /**
         * Convert a CategoryDto to its JPA entity representation.
         */
        public Category toEntity(CategoryDto dto) {
            if (dto == null) {
                return null;
            }
            Category category = new Category();
            category.setId(dto.getId());
            category.setName(dto.getName());
            return category;
        }
    }


