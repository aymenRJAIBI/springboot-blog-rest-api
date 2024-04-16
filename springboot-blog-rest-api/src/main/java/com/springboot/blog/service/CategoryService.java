package com.springboot.blog.service;

import com.springboot.blog.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto category);
    CategoryDto getCategory(Long categoryId);

    List<CategoryDto> getAllCategories();

    CategoryDto update(CategoryDto categoryDto, Long category);

    void deleteCategory(Long categoryId);
}
