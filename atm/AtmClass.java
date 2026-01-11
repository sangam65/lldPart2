package atm;


import java.util.HashMap;

import atm.atmState.AtmInterface;
import atm.atmState.NoCardState;
import atm.currency.Currency;
import atm.entites.Bank;
import atm.entites.Bank.Card;
import atm.exception.AccountException;
import atm.exception.BankException;

public class AtmClass {
    private final HashMap<String,Bank> bankList;
    private Currency currency;
    private Card insertedCard;
    private Bank bankOfInsertedCard;
    private AtmInterface atmState;
    public AtmClass(Currency currency){
        this.bankList=new HashMap<>();
        this.currency=currency;
        this.insertedCard=null;
        this.bankOfInsertedCard=null;
        this.atmState=new NoCardState();
    }
    public synchronized void addBank(Bank bank) throws BankException{
        atmState.addBank(bank, bankList,atmState);
    }
    public synchronized void insertcard(Card card) throws BankException{
        Bank bank=findBankAssociatedWithCard(card);
        this.bankOfInsertedCard=bank;
        this.insertedCard=card;
       this.atmState= atmState.changeState(atmState);
        
    }
    private Bank findBankAssociatedWithCard(Card card){
        for(Bank bank:bankList.values()){
            
             boolean cardExist=   bank.CardExist(card);
             if(cardExist)return bank;
            
        }
        throw new BankException("Given card not associated with any bank");
    }

    public synchronized boolean matchPin(int pin) throws AccountException{
         atmState.enterPin(bankOfInsertedCard, insertedCard, pin,atmState);
         this.atmState=atmState.changeState(atmState);
         return true;
    }


    public synchronized void checkBalance(){
        atmState.checkBalance(bankOfInsertedCard, insertedCard,atmState);
    }


    public synchronized void withDrawBalance(int balanace)throws AccountException{
        currency.canProcess(balanace);
        atmState.withDrawCash(bankOfInsertedCard, insertedCard, balanace, atmState);
    }
}
