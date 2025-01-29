package edu.ithaca.dturnbull.bank;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        //alphanumeric, period, underscore, percent, plus, hyphen username
        //@ Sign
        //alphanumeric, period, hyphen domain
        //period and then domain suffix of 1-3 letters
        Pattern emailPattern = Pattern.compile("^^[a-zA-Z0-9_%+\\-]+(?:\\.[a-zA-Z0-9_%+\\-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z-]{2,3}$");
        Matcher emailMatcher = emailPattern.matcher(email);
        return (emailMatcher.find());
    }
}