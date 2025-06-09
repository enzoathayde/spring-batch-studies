package br.com.springbatch.estudo.batch.processor;

import br.com.springbatch.estudo.domain.AlunoReprovado;
import br.com.springbatch.estudo.dto.AlunoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AlunoProcessor implements ItemProcessor<AlunoDTO, AlunoReprovado> {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public AlunoReprovado process(AlunoDTO item) throws Exception {

        AlunoReprovado newAluno = new AlunoReprovado();

        newAluno.setId(item.getId());
        newAluno.setNome(item.getNome());
        newAluno.setEmail(item.getEmail());

        return newAluno;
    }
}
