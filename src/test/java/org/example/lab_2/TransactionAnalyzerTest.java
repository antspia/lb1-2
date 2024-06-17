package org.example.lab_2;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionAnalyzerTest {

    @Test
    void testCalculateTotalBalanceWithPositiveAmounts() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(LocalDate.parse("2022-01-01"), 50.0, "Purchase"));
        transactions.add(new Transaction(LocalDate.parse("2022-01-02"), 30.0, "Payment"));
        transactions.add(new Transaction(LocalDate.parse("2022-01-03"), 20.0, "Refund"));

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);

        assertEquals(100.0, totalBalance);
    }

    @Test
    void testCalculateTotalBalanceWithNegativeAmounts() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(LocalDate.parse("2022-01-01"), -50.0, "Purchase"));
        transactions.add(new Transaction(LocalDate.parse("2022-01-02"), -30.0, "Payment"));
        transactions.add(new Transaction(LocalDate.parse("2022-01-03"), -20.0, "Refund"));

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);

        assertEquals(-100.0, totalBalance);
    }

    @Test
    void testCalculateTotalBalanceWithMixedAmounts() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(LocalDate.parse("2022-01-01"), 50.0, "Purchase"));
        transactions.add(new Transaction(LocalDate.parse("2022-01-02"), -30.0, "Payment"));
        transactions.add(new Transaction(LocalDate.parse("2022-01-03"), 20.0, "Refund"));

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);

        assertEquals(40.0, totalBalance);
    }

    @Test
    void testCalculateTotalBalanceWithEmptyList() {
        List<Transaction> transactions = new ArrayList<>();

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);

        assertEquals(0.0, totalBalance);
    }

    @Test
    void testCountTransactionsByMonthWithNoMatchingTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(LocalDate.parse("2023-01-01"), 100.0, "Purchase"));
        transactions.add(new Transaction(LocalDate.parse("2023-01-02"), -50.0, "Refund"));
        transactions.add(new Transaction(LocalDate.parse("2023-01-03"), 200.0, "Purchase"));

        String monthYear = "2023-04";
        double expectedCount = 0;
        double actualCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);

        assertEquals(expectedCount, actualCount);
    }

    @Test
    void testCountTransactionsByMonthWithMultipleMatchingTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(LocalDate.parse("2023-01-01"), 100.0, "Purchase"));
        transactions.add(new Transaction(LocalDate.parse("2023-01-02"), -50.0, "Refund"));
        transactions.add(new Transaction(LocalDate.parse("2023-01-03"), 200.0, "Purchase"));
        transactions.add(new Transaction(LocalDate.parse("2023-02-15"), 50.0, "Purchase"));
        transactions.add(new Transaction(LocalDate.parse("2023-02-28"), -25.0, "Refund"));
        transactions.add(new Transaction(LocalDate.parse("2023-03-10"), 75.0, "Purchase"));

        String monthYear = "2023-02";
        int expectedCount = 2;
        int actualCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);

        assertEquals(expectedCount, actualCount);
    }

    @Test
    void testFindTopExpenses() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(LocalDate.parse("2023-01-01"), -100.0, "Refund"));
        transactions.add(new Transaction(LocalDate.parse("2023-01-02"), -50.0, "Refund"));
        transactions.add(new Transaction(LocalDate.parse("2023-01-03"), -200.0, "Refund"));
        transactions.add(new Transaction(LocalDate.parse("2023-01-04"), -150.0, "Refund"));
        transactions.add(new Transaction(LocalDate.parse("2023-01-05"), 300.0, "Purchase"));

        TransactionAnalyzer analyzer = new TransactionAnalyzer();
        List<Transaction> topExpenses = analyzer.findTopExpenses(transactions);

        assertEquals(-200.0, topExpenses.get(0).getAmount());
        assertEquals(-150.0, topExpenses.get(1).getAmount());
        assertEquals(-100.0, topExpenses.get(2).getAmount());
        assertEquals(-50.0, topExpenses.get(3).getAmount());
    }

    @Test
    void testFindBiggestAndLeastExpenses() {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(LocalDate.parse("2023-01-01"), 100.0, "Purchase"));
        transactions.add(new Transaction(LocalDate.parse("2023-01-02"), -50.0, "Refund"));
        transactions.add(new Transaction(LocalDate.parse("2023-01-03"), 200.0, "Purchase"));
        transactions.add(new Transaction(LocalDate.parse("2023-01-04"), -150.0, "Refund"));
        transactions.add(new Transaction(LocalDate.parse("2023-01-05"), -300.0, "Refund"));

        TransactionAnalyzer analyzer = new TransactionAnalyzer();
        List<Transaction> result = analyzer.findBiggestAndLeastExpenses(transactions);

        assertEquals(2, result.size());
        assertEquals(200.0, result.get(0).getAmount()); // Biggest
        assertEquals(-300.0, result.get(1).getAmount()); // Least
    }
}
