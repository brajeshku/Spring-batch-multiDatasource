package com.example.demo.service;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Emp;
import com.example.demo.repository.YourRepository;

@Component
@StepScope
public class MyDataWriter implements ItemWriter<Emp> {

    @Autowired
    private YourRepository repository;

    @Override
    public void write(List<? extends Emp> items) {
        repository.saveAll(items);
    }
}