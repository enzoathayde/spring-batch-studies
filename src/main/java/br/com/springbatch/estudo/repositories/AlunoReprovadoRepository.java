package br.com.springbatch.estudo.repositories;

import br.com.springbatch.estudo.domain.AlunoReprovado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoReprovadoRepository extends JpaRepository<AlunoReprovado, Long> {
}
