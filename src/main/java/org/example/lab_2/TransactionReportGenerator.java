package org.example.lab_2;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionReportGenerator {
    public static void printBalanceReport(double totalBalance) {
        System.out.println("Total balance: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Transaction amount for " + monthYear + " period: " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 top expenses: ");
        topExpenses.forEach(tr -> {
            System.out.println(tr + ": " + tr.getAmount());
        });
    }

    public static void printExpensesByCategory(List<Transaction> transactions) {
        Map<String, Double> expensesByCategory = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(Transaction::getDescription, Collectors.summingDouble(Transaction::getAmount)));

        printExpenses(expensesByCategory);
    }

    public static void printExpensesByMonth(List<Transaction> transactions) {
        Map<Month, Double> expensesByCategory = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        t -> t.getDate().getMonth(),
                        Collectors.summingDouble(Transaction::getAmount)
                        )
                );

        printExpenses(expensesByCategory);
    }

    private static <T> void printExpenses(Map<T, Double> expenses) {
            expenses.forEach((key, value) -> {
            int charAmount = (int) (value / -1000);
            System.out.println(key + " : ");
            Stream.generate(() -> "*").limit(charAmount).forEach(System.out::print);
            System.out.println();
        });
    }
}
