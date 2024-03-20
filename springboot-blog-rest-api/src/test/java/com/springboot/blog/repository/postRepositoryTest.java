package com.springboot.blog.repository;
import com.springboot.blog.entity.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class postRepositoryTest {

    @Autowired
    private PostRepository postRepository;


    @DisplayName("Junit test for save Post operations")
    @Test
    public void givenPostObject_whenSave_thenReturnSavedPost(){
        //given- precondition or setup
        Post post = Post.builder()
                .title("post")
                .description("description")
                .content("content")
                .build();

        // when- action
        Post savedPost =  postRepository.save(post);

        // then -verify the output
        Assertions.assertThat(savedPost).isNotNull();
        Assertions.assertThat(savedPost.getId()).isGreaterThan(0);


    }

}
