package com.desafio.batch.estudos.batch.processor;

import com.desafio.batch.estudos.dto.PostDTO;
import com.desafio.batch.estudos.entity.Post;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PostProcessor implements ItemProcessor<PostDTO, Post>{

    @Override
    public Post process(PostDTO post)  {

        if(post.getId() == null) {
            return null;
        }

        return storePostInDatabase(new Post(), post);
    }


    private static Post storePostInDatabase(Post postOptional, PostDTO postDTO) {
        postOptional.setUserId(postDTO.getUserId());
        postOptional.setId(postDTO.getId());
        postOptional.setTitle(postDTO.getTitle());
        postOptional.setBody(postDTO.getBody());

        return postOptional;
    }



}
