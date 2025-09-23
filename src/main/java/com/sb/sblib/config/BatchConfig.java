package com.sb.sblib.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.sb.sblib.tasklet.TaskletA;
import com.sb.sblib.tasklet.TaskletB;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Step stepA(JobRepository jobRepository, PlatformTransactionManager txManager, TaskletA taskletA) {
        return new StepBuilder("stepA", jobRepository)
                .tasklet(taskletA, txManager)
                .build();
    }

    @Bean
    public Step stepB(JobRepository jobRepository, PlatformTransactionManager txManager, TaskletB taskletB) {
        return new StepBuilder("stepB", jobRepository)
                .tasklet(taskletB, txManager)
                .build();
    }

    @Bean
    public Job jobA(JobRepository jobRepository, Step stepA) {
        return new JobBuilder("jobA", jobRepository)
                .start(stepA)
                .build();
    }

    @Bean
    public Job jobB(JobRepository jobRepository, Step stepB) {
        return new JobBuilder("jobB", jobRepository)
                .start(stepB)
                .build();
    }
}
