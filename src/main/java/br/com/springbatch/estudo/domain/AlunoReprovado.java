package br.com.springbatch.estudo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "alunos_reprovados")
public class AlunoReprovado {

    @Id
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

}
