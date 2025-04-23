package com.desafio.batch.estudos.batch.config;

import com.desafio.batch.estudos.batch.listener.StepCompletionListener;
import com.desafio.batch.estudos.dto.PostDTO;
import com.desafio.batch.estudos.entity.Post;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.inject.Inject;

@Configuration
public class BatchConfig {


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Inject
    private PlatformTransactionManager platformTransactionManager;

    public PlatformTransactionManager getTransactionManager() { return platformTransactionManager; }

    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("importJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }


    @Bean
    public Step step(JobRepository jobRepository,
                     PlatformTransactionManager transactionManager,
                     ItemReader<PostDTO> reader,
                     ItemProcessor<PostDTO, Post> processor,
                     ItemWriter<Post> writer,
                     StepCompletionListener stepCompletionListener) {
        return new StepBuilder("step", jobRepository)
                .<PostDTO, Post>chunk(5, getTransactionManager())
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .listener(stepCompletionListener)
                .build();
    }




}
