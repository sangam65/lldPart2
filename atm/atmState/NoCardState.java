package atm.atmState;





import java.util.HashMap;

import atm.currency.Currency;
import atm.currency.CurrencyType;
import atm.entites.Bank;
import atm.entites.Card;
import atm.exception.BankException;

public class NoCardState  implements AtmInterface{

    

    @Override
    public boolean enterPin(Bank bank, Card card, int pin,AtmInterface currentState) {
         throw new UnsupportedOperationException("Atm don't has card to perform operation");
    }

    @Override
    public void withDrawCash(Bank bank, Card card, int balance,AtmInterface currentState) {
         throw new UnsupportedOperationException("Atm don't has card to perform operation");
    }

    @Override
    public void deposit(Currency currency, CurrencyType currencyType, int count,AtmInterface currentState){
        boolean added=currency.addCurrency(currencyType, count);
        if(added==true){
            System.out.println("Currency added ");
        }
        else{
            System.out.println("Currency not added ");
        }


    }

    @Override
    public void checkBalance(Bank bank, Card card,AtmInterface currentState) {
        
         throw new UnsupportedOperationException("Atm don't has card to perform operation");
    }

    @Override
    public void addBank(Bank bank,HashMap<String,Bank>bankList,AtmInterface currentState) {
        if(bankList.containsKey(bank.getBankId())){
            throw new BankException("Bank already added to atm");
        }
        bankList.put(bank.getBankId(),bank);
    }

    

    @Override
    public void insertCard(Card card,AtmInterface currentState) {
        System.out.println("Card inserted");
    }

    @Override
    public AtmInterface changeState(AtmInterface currentState) {
       currentState= new HasCardState();
       return currentState;
    }

   
}
