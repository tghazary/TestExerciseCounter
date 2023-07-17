package com.example.demo.service;

import com.example.demo.model.Counter;
import com.example.demo.repositories.CounterRepository;
import org.springframework.stereotype.Service;

@Service
public class CounterService {
    private final CounterRepository counterRepository;

    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    public int getCounterValue() {
        Counter counter = counterRepository.findById(1L).orElse(new Counter());
        return counter.getValue();
    }

    public void incrementCounter() {
        Counter counter = counterRepository.findById(1L).orElse(new Counter());
        counter.setValue(counter.getValue() + 1);
        counterRepository.save(counter);
    }

    public void setCounterValue(int value) {
        Counter counter = counterRepository.findById(1L).orElseThrow(() -> new IllegalStateException("Counter not found"));
        counter.setValue(value);
        counterRepository.save(counter);
    }
}