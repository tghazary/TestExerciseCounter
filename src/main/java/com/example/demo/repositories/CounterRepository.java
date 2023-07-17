package com.example.demo.repositories;
import com.example.demo.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepository extends JpaRepository<Counter, Long> {

}