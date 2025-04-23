package com.desafio.batch.estudos.batch.writer;

import com.desafio.batch.estudos.entity.Post;
import com.desafio.batch.estudos.repository.PostRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostWriter implements ItemWriter<Post> {

    private final PostRepository postRepository;
    private final EntityManager entityManager;

    @Override
    public void write(Chunk<? extends Post> chunk) throws Exception {
        for (Post post : chunk.getItems()) {
            try {
                Optional<Post> postOptional = postRepository.findById(post.getId());
                postOptional.ifPresent(value -> post.setId(value.getId()));
                postRepository.save(post);
                entityManager.flush();
                entityManager.clear();
            } catch (Exception e) {
                log.error("Erro ao processar {}", post.getId());
            }
        }
    }
}
