package com.desafio.batch.estudos.client;

import com.desafio.batch.estudos.dto.PostDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class PostsClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${spring.paginated.url}")
    private String url;


    public List<PostDTO>  getPosts(Integer numberPage, Integer pageSize) {

        String fullUrl = url + "?_page=" + numberPage + "&_limit=" + pageSize;


        return restTemplate.exchange(
                fullUrl,
                HttpMethod.GET,
                null, // Sem corpo na requisição GET
                new ParameterizedTypeReference<List<PostDTO>>() {}
        ).getBody();

    }

}
