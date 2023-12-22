package io.bootify.springbatch.config;


import io.bootify.springbatch.domain.Transaccion;
import io.bootify.springbatch.repos.TransaccionRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;


@Configuration
@EnableBatchProcessing
public class SpringbatchConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private TransaccionRepository repository;

    @Bean
    public FlatFileItemReader<Transaccion> reader(){
        FlatFileItemReader<Transaccion> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new ClassPathResource("CsvTransacciones.csv")); // Aquí he cambiado el nombre del archivo CSV
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    private LineMapper<Transaccion> lineMapper() {
        DefaultLineMapper<Transaccion> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "fecha", "cantidad", "tipotrans", "cuentaOrigen", "cuentaDestino", "dateCreated", "lastUpdated"); // Aquí he cambiado los nombres de las columnas para que coincidan con tu archivo CSV
        CustomFieldSetMapper fieldSetMapper = new CustomFieldSetMapper();
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public TransaccionProcessor processor(){
        return new TransaccionProcessor();
    }

    @Bean
    public TransaccionItemWriter writer(EntityManagerFactory entityManagerFactory){
        return new TransaccionItemWriter(entityManagerFactory);
    }

    @Bean
    public Step step1(ItemReader<Transaccion> reader, TransaccionItemWriter writer, TransaccionProcessor processor){
        return new StepBuilder("csv-step", jobRepository).<Transaccion, Transaccion>chunk(500, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job runjob(Step step1){
        return new JobBuilder("importTransacciones", jobRepository)
                .start(step1)
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.setThreadNamePrefix("batch-");
        return taskExecutor;
    }
}



