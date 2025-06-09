package br.com.springbatch.estudo.batch.writer;

import br.com.springbatch.estudo.domain.AlunoReprovado;
import br.com.springbatch.estudo.repositories.AlunoReprovadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class AlunoWriter implements ItemWriter<AlunoReprovado> {

    private final AlunoReprovadoRepository alunoReprovadoRepository;
    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;

    @Override
    @Transactional
    public void write(Chunk<? extends AlunoReprovado> chunk) throws Exception {

        try {
            for (AlunoReprovado aluno : chunk.getItems()) {

                Optional<AlunoReprovado> alunoOptional = alunoReprovadoRepository.findById(aluno.getId());

                if (alunoOptional.isEmpty()) {
                    alunoReprovadoRepository.save(aluno);
                    entityManager.flush();
                    entityManager.clear();

                } else {
                    aluno.setId(alunoOptional.get().getId());
                    alunoReprovadoRepository.save(aluno);
                    entityManager.flush();
                    entityManager.clear();
                }


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
