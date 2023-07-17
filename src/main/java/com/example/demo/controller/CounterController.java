package com.example.demo.controller;
import com.example.demo.model.Counter;
import com.example.demo.repositories.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {
    private final CounterRepository counterRepository;

    @Autowired
    public CounterController(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @GetMapping("/counter")
    public int getCounterValue() {
        Counter counter = counterRepository.findById(1L).orElse(new Counter());
        return counter.getValue();
    }

    @PostMapping("/counter")
    public void updateCounterValue(@RequestBody int newValue) {
        Counter counter = counterRepository.findById(1L).orElse(new Counter());
        counter.setValue(newValue);
        counterRepository.save(counter);
    }
}