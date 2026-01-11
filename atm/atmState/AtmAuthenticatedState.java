package atm.atmState;

import java.util.HashMap;

import atm.currency.Currency;
import atm.currency.CurrencyType;
import atm.entites.Bank;
import atm.entites.Card;
import atm.exception.AccountException;
import atm.exception.BankException;

public class AtmAuthenticatedState implements AtmInterface {

    @Override
    public void insertCard(Card card) {
        throw new UnsupportedOperationException("Atm has card and authentication done, can't perform this operation");
    }

    @Override
    public boolean enterPin(Bank bank, Card card, int pin) {

        throw new UnsupportedOperationException("Atm has card and authentication done, can't perform this operation");
    }

    // rollback will be added when database is added by adding transactional annotation
    @Override
    public synchronized void withDrawCash(Bank bank, Card card, Currency currency, int balance) {
        try {
              int bankBalance = bank.checkBalance(card);
              if(bankBalance<balance){
                throw new BankException("Insufficient balance");
              }
            boolean cashDispense=currency.reserveAndValidate(balance);
            if(cashDispense==false){
                throw new BankException("Bank does not have enough balance to dispense this money");
            }
            bank.withDrawCash(card, balance);
            currency.displayNotes(balance);

        } catch (AccountException|BankException e) {
            System.out.println(e.getMessage());
            
        }

    }

    @Override
    public void deposit(Currency currency, CurrencyType currencyType, int count) {

        throw new UnsupportedOperationException("Unimplemented method 'deposit'");
    }

    @Override
    public synchronized void checkBalance(Bank bank, Card card) {
        try {
            int balance = bank.checkBalance(card);
            System.out.println("balance " + balance);
        } catch (AccountException e) {
            System.out.println("display :"+e.getMessage());
        }
    }

    @Override
    public void addBank(Bank bank, HashMap<String, Bank> bankList) {

        throw new UnsupportedOperationException("Unimplemented method 'addBank'");
    }

}
