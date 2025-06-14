package br.com.springbatch.estudo.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StepCompletionListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.debug("----------------------- JOB PARA BUSCAR ALUNOS REPROVADOS INICIOU-SE -----------------------");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.debug("----------------------- JOB PARA BUSCAR ALUNOS REPROVADOS FOI FINALIZADO -----------------------");
        return stepExecution.getExitStatus();
    }

}
