package atm.atmState;

import java.util.HashMap;

import atm.currency.Currency;
import atm.currency.CurrencyType;
import atm.entites.Bank;
import atm.entites.Card;
import atm.exception.AccountException;

public class AtmAuthenticatedState implements AtmInterface{

    @Override
    public void insertCard(Card card) {
                throw new UnsupportedOperationException("Atm has card and authentication done, can't perform this operation");
    }

    @Override
    public boolean enterPin(Bank bank, Card card, int pin) {
      
            throw new UnsupportedOperationException("Atm has card and authentication done, can't perform this operation");
    }

    @Override
    public void withDrawCash(Bank bank, Card card,int balance) {
        try{

            bank.withDrawCash(card, balance);
            
        }
        catch(AccountException e){
            System.out.println(e.getMessage());
            

            
        }

    }

    @Override
    public void deposit(Currency currency, CurrencyType currencyType, int count) {
        
        throw new UnsupportedOperationException("Unimplemented method 'deposit'");
    }

    @Override
    public void checkBalance(Bank bank, Card card) {
       try{
        int balance= bank.checkBalance(card);
       System.out.println("balance "+balance);
       }
       catch(Exception e){

       }
    }

    @Override
    public void addBank(Bank bank, HashMap<String, Bank> bankList) {

        throw new UnsupportedOperationException("Unimplemented method 'addBank'");
    }

    @Override
    public AtmInterface changeState(AtmInterface currentState) {
       
        return new NoCardState();
    }

    
    

    

}
