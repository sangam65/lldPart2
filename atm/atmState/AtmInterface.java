package atm.atmState;




import java.util.HashMap;

import atm.currency.Currency;
import atm.currency.CurrencyType;
import atm.entites.Bank;
import atm.entites.Card;


public interface AtmInterface {

    void insertCard(Card card,AtmInterface currentState);
    boolean enterPin(Bank bank,Card card,int pin,AtmInterface currentState);
    void withDrawCash(Bank bank,Card card,int balance,AtmInterface currentState);
    void deposit(Currency currency,CurrencyType currencyType,int count,AtmInterface currentState);
    void checkBalance(Bank bank,Card card,AtmInterface currentState);
    void addBank(Bank bank,HashMap<String,Bank>bankList,AtmInterface currentState);
    AtmInterface changeState(AtmInterface currentState);
    

}
