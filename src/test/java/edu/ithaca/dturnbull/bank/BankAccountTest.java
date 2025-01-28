package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        // hello 
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse( BankAccount.isEmailValid(""));         // empty string
        assertFalse(BankAccount.isEmailValid("plainaddress")); // missing '@' and domain
        assertFalse(BankAccount.isEmailValid("user@.com")); // domain starts with dot
        assertFalse(BankAccount.isEmailValid("user@domain")); // missing domain
        assertFalse(BankAccount.isEmailValid("a..b@gmail.com")); // consecutive dots in prefix
        assertFalse(BankAccount.isEmailValid("gb@@mail.com")); // more than one '@'
        assertFalse(BankAccount.isEmailValid(".hello@mail.com")); // starts with a special character
        assertFalse(BankAccount.isEmailValid("user@domain..com")); // consecutive dots in domain
        assertFalse(BankAccount.isEmailValid("user@domain,com")); // invalid character in domain
        assertFalse(BankAccount.isEmailValid("user@domain.com ")); // ending space
        assertFalse(BankAccount.isEmailValid(" user@domain.com")); // starting space 
        assertFalse(BankAccount.isEmailValid("ab@mail.c"));
        assertFalse(BankAccount.isEmailValid("ab@mail.c"));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}