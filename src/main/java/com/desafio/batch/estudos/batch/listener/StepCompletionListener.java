package com.desafio.batch.estudos.batch.listener;

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
        log.debug("----------------------- INICIANDO SPRING BATCH PARA POSTS DO DUMMY JSON -----------------------");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.debug("----------------------- FINALIZANDO SPRING BATCH  -----------------------");
        return stepExecution.getExitStatus();
    }
}
