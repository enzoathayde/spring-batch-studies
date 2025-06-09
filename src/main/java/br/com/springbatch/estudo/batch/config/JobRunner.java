package br.com.springbatch.estudo.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;

public class JobRunner implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job alunoJob;

    public JobRunner(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.alunoJob = job;
    }

    @Override
    public void run(String... args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                        .toJobParameters();

        jobLauncher.run(alunoJob, jobParameters);
    }
}
