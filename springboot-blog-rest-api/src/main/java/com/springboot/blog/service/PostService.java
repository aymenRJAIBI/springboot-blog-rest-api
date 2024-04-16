package com.springboot.blog.service;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto PostDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy);

    PostDto getPostById(long id);

    PostDto updatePost(long id, PostDto PostDto);

    void deletePost(long id);

    List<PostDto> getPostsByCategory(Long categoryId);

}
