package com.example.demo.ui;

import com.example.demo.service.CounterService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
public class CounterUI extends VerticalLayout {
    private final CounterService counterService;

    private final TextField counterTextField;

    public CounterUI(@Autowired CounterService counterService) {
        this.counterService = counterService;

        counterTextField = new TextField("Counter value");

        Button incrementButton = new Button("Increment");
        incrementButton.addClickListener(e -> {
            counterService.incrementCounter();
            updateCounterValue();
            Notification.show("Counter value incremented");
        });

        Button saveButton = new Button("Save");
        saveButton.addClickListener(e -> {
            int newValue = Integer.parseInt(counterTextField.getValue());
            counterService.setCounterValue(newValue);
            updateCounterValue();
            Notification.show("Counter value saved");
        });

        add(counterTextField, incrementButton, saveButton);

        updateCounterValue();
    }

    private void updateCounterValue() {
        int counterValue = counterService.getCounterValue();
        counterTextField.setValue(String.valueOf(counterValue));
    }
}