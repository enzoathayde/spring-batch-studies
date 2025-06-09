package br.com.springbatch.estudo.batch.config;

import br.com.springbatch.estudo.batch.listener.StepCompletionListener;
import br.com.springbatch.estudo.domain.AlunoReprovado;
import br.com.springbatch.estudo.dto.AlunoDTO;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.inject.Inject;


@Configuration
public class AlunoJobConfig {


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Inject
    private PlatformTransactionManager platformTransactionManager;

    @Value("${itemsPerChunk}")
    private Integer itemsPerChunk;

    public PlatformTransactionManager getTransactionManager() {
        return platformTransactionManager;
    }

    @Bean
    public Job alunoJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("alunoJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository,
                     PlatformTransactionManager transactionManager,
                     ItemReader<AlunoDTO> reader,
                     ItemProcessor<AlunoDTO, AlunoReprovado> processor,
                     ItemWriter<AlunoReprovado> writer,
                     StepCompletionListener stepCompletionListener) {
        return new StepBuilder("step", jobRepository)
                .<AlunoDTO, AlunoReprovado>chunk(itemsPerChunk, getTransactionManager())
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .transactionManager(transactionManager)
                .listener(stepCompletionListener)
                .build();
    }
}