package atm.atmState;

import java.util.HashMap;

import atm.currency.Currency;
import atm.currency.CurrencyType;
import atm.entites.Bank;
import atm.entites.Bank.Card;
import atm.exception.AccountException;

public class HasCardState implements AtmInterface{

    @Override
    public void insertCard(Card card,AtmInterface currentState) {
        throw new UnsupportedOperationException("Atm has card, can't perform this operation");
    }

    @Override
    public boolean enterPin(Bank bank, Card card, int pin,AtmInterface currentState)throws AccountException {
        boolean res= bank.pinAuthentication(card, pin);
        
        return res;
         
    }

    @Override
    public void withDrawCash(Bank bank, Card card, int balance,AtmInterface currentState) {
       throw new UnsupportedOperationException("Atm has card, can't perform this operation");
    }

    @Override
    public void deposit(Currency currency, CurrencyType currencyType, int count,AtmInterface currentState) {
       throw new UnsupportedOperationException("Atm has card, can't perform this operation");
    }

    @Override
    public void checkBalance(Bank bank, Card card,AtmInterface currentState) {
       throw new UnsupportedOperationException("Atm has card, can't perform this operation");
    }

    @Override
    public void addBank(Bank bank, HashMap<String, Bank> bankList,AtmInterface currentState) {
      
        throw new UnsupportedOperationException("Atm has card, can't perform this operation");
    }

    @Override
    public AtmInterface changeState(AtmInterface currentState) {
        currentState= new AtmAuthenticatedState();
        return currentState;
    }

    

}
