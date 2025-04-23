package com.desafio.batch.estudos.dto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostDTO {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
