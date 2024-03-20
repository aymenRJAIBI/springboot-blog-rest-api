package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

}
