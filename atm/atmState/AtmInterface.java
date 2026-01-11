package atm.atmState;




import java.util.HashMap;

import atm.currency.Currency;
import atm.currency.CurrencyType;
import atm.entites.Bank;
import atm.entites.Card;


public interface AtmInterface {

    void insertCard(Card card);
    boolean enterPin(Bank bank,Card card,int pin);
    void withDrawCash(Bank bank,Card card,Currency currency,int balance);
    void deposit(Currency currency,CurrencyType currencyType,int count);
    void checkBalance(Bank bank,Card card);
    void addBank(Bank bank,HashMap<String,Bank>bankList);

    

}
