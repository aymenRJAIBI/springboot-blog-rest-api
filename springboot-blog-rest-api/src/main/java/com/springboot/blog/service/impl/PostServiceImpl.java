package com.springboot.blog.service.impl;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;


    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        // convert Dto to entity type

        Category category = findCategoryByIdOrThrow(postDto.getCategoryId());
        Post post = mapToEntity(postDto);
        post.setCategory(category);
        Post newPost = postRepository.save(post);
        // convert Entity to Dto;
        return mapToDto(newPost);

    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content = listOfPosts.stream().map(this::mapToDto).toList();
        PostResponse response = new PostResponse();
        response.setContent(content);
        response.setPageNo(posts.getNumber());
        response.setPageSize(posts.getSize());
        response.setTotalElements(posts.getTotalElements());
        response.setTotalPages(posts.getTotalPages());
        response.setLast(posts.isLast());

        return response;

    }

    @Override
    public PostDto getPostById(long id) {
        Post post = findPostByIdOrThrow(id);
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {

        Post post = findPostByIdOrThrow(id);
        Category category = findCategoryByIdOrThrow(postDto.getCategoryId());

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);

        Post updatedPost = postRepository.save(post);

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePost(long id) {
        Post post = findPostByIdOrThrow(id);
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {

        Category category = findCategoryByIdOrThrow(categoryId);

        List<Post> posts = postRepository.findByCategoryId(category.getId());

        return posts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private PostDto mapToDto(Post post) {


//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
        return modelMapper.map(post, PostDto.class);
    }

    private Post mapToEntity(PostDto postDto) {
//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        return modelMapper.map(postDto, Post.class);
    }

    private Post findPostByIdOrThrow(long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id" + id));
    }

    private Category findCategoryByIdOrThrow(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id" + id));
    }
}
