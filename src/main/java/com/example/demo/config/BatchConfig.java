package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import com.example.demo.entity.Emp;
import com.example.demo.service.ExcelItemReader;
import com.example.demo.service.MyDataProcessor;
import com.example.demo.service.MyDataWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MyDataProcessor myDataProcessor;

    @Autowired
    private MyDataWriter myDataWriter;

    @Autowired
    private ExcelItemReader excelItemReader;

    @Autowired
    private DataSource batchDataSource; // H2 DataSource for Spring Batch

    @Override
    public void setDataSource(DataSource dataSource) {
        // Override to set H2 DataSource for Spring Batch metadata
        super.setDataSource(batchDataSource);
    }

    @Bean
    public Job importJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
            .<Emp, Emp>chunk(10)
            .reader(excelItemReader)
            .processor(myDataProcessor)
            .writer(myDataWriter)
            .build();
    }
}
