package com.avaaj.blog.service;

import java.util.List;

import com.avaaj.blog.payload.PostDto;
import com.avaaj.blog.payload.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto);

	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

	PostDto getPostById(long id);

	PostDto updatePost(PostDto postDto, long id);

	void deletePostBtId(long id);
	
	List<PostDto> getPostsByCategory(Long categoryId);
}
