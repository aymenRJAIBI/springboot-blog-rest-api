package com.springboot.blog.controller;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(name = " CRUD REST APIs for Post Resource")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(
            summary = "Create Post REST API",
            description = "create Post REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "http status 201 created successfully"
    )
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {

        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All Posts  REST API",
            description = "Get All Posts REST API is used to Fetch all  post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "http status 200 SUCCESSFUL"
    )
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy) {

        return postService.getAllPosts(pageNo, pageSize, sortBy);


    }

    @Operation(
            summary = "Get Post By Id REST API",
            description = "Get Post By Id REST API is used to get post from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "http status 200 SUCCESSFUL"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @Operation(
            summary = "Update Post  REST API",
            description = "Update Post REST API is used to update post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "http status 200 SUCCESSFUL"
    )
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.updatePost(id, postDto));
    }

    @Operation(
            summary = "Delete Post  REST API",
            description = "Delete Post REST API is used to Delete post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "http status 200 SUCCESSFUL"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted with success ! ");
    }

    @GetMapping("category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(name = "id") Long categoryId) {
        List<PostDto> result = postService.getPostsByCategory(categoryId);

        return ResponseEntity.ok(result);
    }

}
