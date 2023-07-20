package com.example.demo.service;

import com.example.demo.model.Counter;
import com.example.demo.repositories.CounterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CounterService {
    private final CounterRepository counterRepository;

    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public int getCounterValue() {
        Counter counter = counterRepository.findById(1L).orElse(new Counter());
        return counter.getValue();
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void incrementCounter() {
        Counter counter = counterRepository.findById(1L).orElse(new Counter());
        counter.setValue(counter.getValue() + 1);
        counterRepository.save(counter);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void setCounterValue(int value) {
        Counter counter = counterRepository.findById(1L).orElseThrow(() -> new IllegalStateException("Counter not found"));
        counter.setValue(value);
        counterRepository.save(counter);
    }
}