package br.com.springbatch.estudo.domain;

import br.com.springbatch.estudo.dto.AlunoDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "aluno")
@SqlResultSetMapping(
        name = "AlunoReprovadoEntityMapping",
        classes = @ConstructorResult(
                targetClass = AlunoDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "nome", type = String.class),
                        @ColumnResult(name = "email", type = String.class ),
                }
        )
)
public class Aluno {

    @Id
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

}
