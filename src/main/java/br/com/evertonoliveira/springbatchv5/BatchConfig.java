package br.com.evertonoliveira.springbatchv5;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {
	
	@Bean
	public Job imprimeOlaJob(JobRepository jobRepository, Step imprimeOlaStep) {
		return new JobBuilder("imprimeOlaJob", jobRepository)
				.start(imprimeOlaStep)
				.build();
	}
  
	@Bean
	public Step imprimeOlaStep(JobRepository jobRepository, PlatformTransactionManager transactionManager ) {
		return new StepBuilder("imprimeOlaStep", jobRepository)
				.tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
						System.out.println("Olá Mundo!");
						return RepeatStatus.FINISHED;
					}, transactionManager)
				.build();
	}
  
}
