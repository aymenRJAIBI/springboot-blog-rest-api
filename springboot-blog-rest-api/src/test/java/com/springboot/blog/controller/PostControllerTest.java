package com.springboot.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.blog.dto.PostDto;
import com.springboot.blog.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PostControllerTest {
@Autowired
private MockMvc mockMvc;

@MockBean
private PostService postService;

@Autowired
private ObjectMapper mapper;

@Test
    public void givenPostObject_whenSave_thenReturnSavedPost() throws Exception {
    // given setup
    PostDto postDto = new PostDto();
    postDto.setTitle("test");
    postDto.setContent("test");
    postDto.setDescription("test");

    BDDMockito.given(postService.createPost(any(PostDto.class))).willAnswer((invocation) ->invocation.getArgument(0));

    // when -action
    ResultActions response = mockMvc.perform(post("/api/posts")
            .contentType("application/json")
            .content(mapper.writeValueAsString(postDto)));

    // then verify the result
    response.andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title",is(postDto.getTitle())))
            .andExpect(jsonPath("$.description",is(postDto.getDescription())))
            .andExpect(jsonPath("$.content",is(postDto.getContent())));



}

}
