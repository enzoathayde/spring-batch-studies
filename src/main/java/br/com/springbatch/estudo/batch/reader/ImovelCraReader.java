package br.com.springbatch.estudo.batch.reader;

import br.com.springbatch.estudo.dto.AlunoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Component
public class ImovelCraReader implements ItemReader<AlunoDTO> {

    @PersistenceContext
    private EntityManager entityManager;

    private final String alunoReprovadoQuery = "SELECT\n" +
            "\tid,\n" +
            "\tnome,\n" +
            "\temail\n" +
            "FROM\n" +
            "\taluno\n" +
            "WHERE\n" +
            "\treprovado IS TRUE\n" +
            "ORDER BY\n" +
            "\tid\n" +
            "LIMIT ?\n" +
            "OFFSET ?";
    private Iterator<AlunoDTO> alunoDTOIterator;
    private final int pageSize = 5;
    private int currentPage = 1;

    @Override
    @Transactional(readOnly = true)
    public AlunoDTO read()  throws NullPointerException {

        if(alunoDTOIterator == null || !alunoDTOIterator.hasNext()) {

            List<AlunoDTO> alunosReprovadosPage = fetchAlunosReprovados();

            if(alunosReprovadosPage == null) {
                Boolean noData = true;
                return null;
            }

            alunoDTOIterator = alunosReprovadosPage.iterator();

            currentPage++;
        }

        if (alunoDTOIterator != null && alunoDTOIterator.hasNext()) {
            return alunoDTOIterator.next();
        }

        return null;
    }


    private List<AlunoDTO> fetchAlunosReprovados() {
        Query query = entityManager.createNativeQuery(alunoReprovadoQuery, "AlunoReprovadoEntityMapping");
        query.setParameter(1, pageSize);
        query.setParameter(2, (currentPage - 1) * pageSize);

        List<AlunoDTO> result = query.getResultList();

        return result;
    }





}
