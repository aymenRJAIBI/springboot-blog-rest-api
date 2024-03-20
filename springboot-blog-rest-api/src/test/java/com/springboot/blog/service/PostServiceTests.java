package com.springboot.blog.service;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.entity.Post;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostServiceTests {


    private PostRepository postRepository;
    private PostService postService;


    @BeforeEach
    public void setUp(){
        postRepository = Mockito.mock(PostRepository.class);
       // TODO add model mapper postService = new PostServiceImpl(postRepository);
       // postService = new PostServiceImpl(postRepository);
    }

    @Test
    public void givenPostObject_whenSave_thenReturnSavedPost() {

        //given- precondition or setup
        PostDto postDto = new PostDto();
        postDto.setContent("test");
        postDto.setDescription("test");
        postDto.setTitle("test");

        Post post = new Post();
        post.setId(1L);
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());

        //


        // when- action
        Mockito.when(postRepository.save(any(Post.class))).thenReturn(post);
        PostDto savedPost = postService.createPost(postDto);

        // then -verify the output
        assertEquals(post.getId(), savedPost.getId());
        assertEquals(post.getTitle(), savedPost.getTitle());
        assertEquals(post.getDescription(), savedPost.getDescription());
        assertEquals(post.getContent(), savedPost.getContent());

    }

}
