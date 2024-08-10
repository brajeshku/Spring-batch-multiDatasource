package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Emp;
import com.example.demo.repository.YourRepository;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
@Service
public class TestServiceImpl {

	@Autowired
	private YourRepository yourRepository;
	
	public void testing() {
		List<Emp> emps = yourRepository.findAll();
	}
	
}
