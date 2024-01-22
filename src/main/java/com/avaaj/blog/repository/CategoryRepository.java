package com.avaaj.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avaaj.blog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
