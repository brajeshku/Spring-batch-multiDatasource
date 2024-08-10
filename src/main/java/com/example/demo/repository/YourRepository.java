package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Emp;

public interface YourRepository extends JpaRepository<Emp, Long> {
}