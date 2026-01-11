package atm.atmState;

import java.util.HashMap;

import atm.currency.Currency;
import atm.currency.CurrencyType;
import atm.entites.Bank;
import atm.entites.Card;
import atm.exception.AccountException;

public class HasCardState implements AtmInterface{

    @Override
    public void insertCard(Card card) {
        throw new UnsupportedOperationException("Atm has card, can't perform this operation");
    }

    @Override
    public boolean enterPin(Bank bank, Card card, int pin)throws AccountException {
        boolean res= bank.pinAuthentication(card, pin);
        
        return res;
         
    }

    @Override
    public void withDrawCash(Bank bank, Card card,Currency currency, int balance) {
       throw new UnsupportedOperationException("Atm has card, can't perform this operation");
    }

    @Override
    public void deposit(Currency currency, CurrencyType currencyType, int count) {
       throw new UnsupportedOperationException("Atm has card, can't perform this operation");
    }

    @Override
    public void checkBalance(Bank bank, Card card) {
       throw new UnsupportedOperationException("Atm has card, can't perform this operation");
    }

    @Override
    public void addBank(Bank bank, HashMap<String, Bank> bankList) {
      
        throw new UnsupportedOperationException("Atm has card, can't perform this operation");
    }

    

}
