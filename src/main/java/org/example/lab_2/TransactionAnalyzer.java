package org.example.lab_2;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionAnalyzer {
    public static double calculateTotalBalance(List<Transaction> transactions){
        double sum = 0;
        for (Transaction transaction : transactions) {
            sum += transaction.getAmount();
        }
        return sum;
    }

    public static int countTransactionsByMonth(List<Transaction> transactions, String monthYear){
        int count = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getDate()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM"))
                    .equals(monthYear)){
                count++;
            }
        }
        return count;
    }

    public static List<Transaction> findTopExpenses(List<Transaction> transactions){
        return transactions.stream()
                .filter(tr -> tr.getAmount() < 0)
                .sorted(Comparator.comparing(Transaction::getAmount))
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Transaction> findBiggestAndLeastExpenses(List<Transaction> transactions){
        Transaction biggest = transactions.stream().max(Comparator.comparing(Transaction::getAmount)).get();
        Transaction least = transactions.stream().min(Comparator.comparing(Transaction::getAmount)).get();
        return List.of(biggest, least);
    }
}
