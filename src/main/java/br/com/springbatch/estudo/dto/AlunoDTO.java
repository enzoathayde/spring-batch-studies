package br.com.springbatch.estudo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlunoDTO {

    private Long id;
    private String nome;
    private String email;

}
