package atm.atmState;

import java.util.HashMap;

import atm.currency.Currency;
import atm.currency.CurrencyType;
import atm.entites.Bank;
import atm.entites.Card;
import atm.exception.BankException;

public class NoCardState implements AtmInterface {

    @Override
    public boolean enterPin(Bank bank, Card card, int pin) {
        throw new UnsupportedOperationException("Atm don't has card to perform operation");
    }

    @Override
    public void withDrawCash(Bank bank, Card card,Currency currency, int balance) {
        throw new UnsupportedOperationException("Atm don't has card to perform operation");
    }

    @Override
    public void deposit(Currency currency, CurrencyType currencyType, int count) {
        boolean added = currency.addCurrency(currencyType, count);
        if (added == true) {
            System.out.println("Currency added ");
        } else {
            System.out.println("Currency not added ");
        }

    }

    @Override
    public void checkBalance(Bank bank, Card card) {

        throw new UnsupportedOperationException("Atm don't has card to perform operation");
    }

    @Override
    public synchronized void addBank(Bank bank, HashMap<String, Bank> bankList) {
        if (bankList.containsKey(bank.getBankId())) {
            throw new BankException("Bank already added to atm");
        }
        String bankId = bank.getBankId();
        String bankChar = bankId.substring(bankId.length() - 4);
        bankList.put(bankChar, bank);
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Card inserted");
    }



}
