package org.example.lab_2;

import org.example.lab_2.Exceptions.WrongTransactionType;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws WrongTransactionType, IOException {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionReader.readTransactions(filePath, "csv");

        for (Transaction tr : transactions) {
            System.out.println(tr);
        }

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        int transactionCount = TransactionAnalyzer.countTransactionsByMonth(transactions, "01-2024");
        TransactionReportGenerator.printTransactionsCountByMonth("02-2024", transactionCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        TransactionReportGenerator.printExpensesByCategory(transactions);
        TransactionReportGenerator.printExpensesByMonth(transactions);
    }
}
