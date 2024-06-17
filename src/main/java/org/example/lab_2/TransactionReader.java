package org.example.lab_2;

import org.example.lab_2.Exceptions.WrongTransactionType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionReader {
    public static List<Transaction> readTransactions(String filepath, String type) throws WrongTransactionType, IOException {
        switch (type){
            case "json": return readJSONTransactions(filepath);
            case "csv": return readCSVTransactions(filepath);
            default: throw new WrongTransactionType("There is no such type");
        }
    }

    private static List<Transaction> readCSVTransactions(String filePath) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        URL url = new URL(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        String line;
        while ((line = br.readLine()) != null){
            String[] values = line.split(",");
            Transaction transaction = new Transaction(
                    LocalDate.parse(values[0], DateTimeFormatter.ofPattern("dd-MM-yyy")),
                    Double.parseDouble(values[1]),
                    values[2]);
            transactions.add(transaction);
        }
        return transactions;
    }

    private static List<Transaction> readJSONTransactions(String filePath){
        List<Transaction> transactions = new ArrayList<>();
        return transactions;
    }
}
