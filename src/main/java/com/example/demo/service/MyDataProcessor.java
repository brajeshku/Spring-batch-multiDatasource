package com.example.demo.service;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Emp;

@Component
@StepScope
public class MyDataProcessor implements ItemProcessor<Emp, Emp> {

    @Override
    public Emp process(Emp item) {
        // Processing logic here
        return item;
    }
}
