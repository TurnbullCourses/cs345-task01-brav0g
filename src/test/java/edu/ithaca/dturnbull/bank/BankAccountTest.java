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
        // hello hello test1 
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   //Valid Partition: valid email address
        assertFalse( BankAccount.isEmailValid(""));         //Invalid Border Case: empty string 
        assertFalse(BankAccount.isEmailValid("plainaddress")); //Invalid Partition missing '@' and domain
        assertFalse(BankAccount.isEmailValid("user@.com")); //Invalid Border Case: domain starts with dot
        assertFalse(BankAccount.isEmailValid("user@domain")); //Invalid Partition:  missing domain
        assertFalse(BankAccount.isEmailValid("a..b@gmail.com")); //Invalid Border Case: consecutive dots in prefix
        assertFalse(BankAccount.isEmailValid("gb@@mail.com")); //Invalid Border Case: more than one '@'
        assertFalse(BankAccount.isEmailValid(".hello@mail.com")); //Invalid Partition: starts with a special character
        assertFalse(BankAccount.isEmailValid("user@domain..com")); //Invalid Border Case: consecutive dots in domain
        assertFalse(BankAccount.isEmailValid("user@domain,com")); //Invalid Partition: invalid chapracter in domain
        assertFalse(BankAccount.isEmailValid("user@domain.com ")); //Invalid Partition: ending space
        assertFalse(BankAccount.isEmailValid(" user@domain.com")); //Invalid Partition: starting space 
        assertFalse(BankAccount.isEmailValid("ab@mail.c")); //Invalid Boundary Case: domain suffix too short
        assertFalse(BankAccount.isEmailValid("ab@mail.c")); //Invalid Boundary Case: domain suffix too short
        //Potential Additional Tests: Check for the username ending in a '-'. Addresses are invalid if '-@' is present
        //An address of above and at maximum length
        //An address with too long of a domain suffix
        //An address with a valid subdomain
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