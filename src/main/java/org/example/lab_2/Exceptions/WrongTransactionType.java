package org.example.lab_2.Exceptions;

public class WrongTransactionType extends Exception{
    public WrongTransactionType(String errorMessage){
        super(errorMessage);
    }
}
