package com.avaaj.blog.service;

import java.util.List;

import com.avaaj.blog.payload.CategoryDto;

public interface CategoryService {
	CategoryDto addCategory(CategoryDto categoryDto);
	
	CategoryDto getCategory(Long categoryId);
	
	List<CategoryDto> getAllCategories();
	
	CategoryDto updateCategory(Long categoryId ,CategoryDto categoryDto);
	
	void deleteCategory(Long categoryId);
}
