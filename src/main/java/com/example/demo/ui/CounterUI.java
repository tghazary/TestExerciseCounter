package com.example.demo.ui;

import com.example.demo.service.CounterService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
public class CounterUI extends VerticalLayout {
    private final CounterService counterService;


    @Max(value = Integer.MAX_VALUE, message = "Value should not exceed 100")
    @Min(value = Integer.MIN_VALUE, message = "Value should be greater than or equal to 0")
    @Pattern(regexp = "\\d+", message = "Invalid value. Only digits are allowed.")
    private final TextField counterTextField;

    public CounterUI(@Autowired CounterService counterService) {
        this.counterService = counterService;

        counterTextField = new TextField("Counter value");

        Button incrementButton = new Button("Increment");
        incrementButton.addClickListener(e -> {
            validateNumber(counterTextField);
            counterService.incrementCounter();
            updateCounterValue();
            Notification.show("Counter value incremented");
        });

        Button saveButton = new Button("Save");
        saveButton.addClickListener(e -> {
            validateNumber(counterTextField);
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

    private void validateNumber(TextField counterTextField) {
        String value = counterTextField.getValue();
        if (counterTextField.isInvalid() || value == null || value.isEmpty() || !value.matches("\\d+")) {
            counterTextField.setErrorMessage("Invalid value. Only integer values are allowed.");
            counterTextField.setInvalid(true);
        } else {
            try {
                Integer.parseInt(value);
            } catch (NumberFormatException e) {
                counterTextField.setErrorMessage("Invalid value. Only integer values are allowed.");
                counterTextField.setInvalid(true);
            }
        }
    }
}