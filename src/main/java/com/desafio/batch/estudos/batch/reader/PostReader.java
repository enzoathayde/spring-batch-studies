package com.desafio.batch.estudos.batch.reader;

import com.desafio.batch.estudos.client.PostsClient;
import com.desafio.batch.estudos.dto.PostDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class PostReader implements ItemReader<PostDTO> {

    private final PostsClient postsClient;

    private int currentPage = 1;
    private final int pageSize = 5;
    private Iterator<PostDTO> currentIterator;

    public PostReader(PostsClient postsClient) {
        this.postsClient = postsClient;
    }

    @Override
    public PostDTO read() {
        if (currentIterator == null || !currentIterator.hasNext()) {
            List<PostDTO> postsPage = postsClient.getPosts(currentPage, pageSize);

           List<PostDTO> posts = postsPage;
           if (posts == null || posts.isEmpty()) {
               return null;
           }

          currentIterator = posts.iterator();
          currentPage++;
        }

        return currentIterator.next();
    }
}
