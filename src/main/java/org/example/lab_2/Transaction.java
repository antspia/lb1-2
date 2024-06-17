package org.example.lab_2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@ToString
public class Transaction {
    @Getter
    private LocalDate date;
    @Getter
    private double amount;
    @Getter
    private String description;
}
