package com.avaaj.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avaaj.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	List<Post> findByCategoryId(Long categoryId);

}
