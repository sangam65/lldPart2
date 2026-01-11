package atm;

import java.util.HashMap;


import atm.atmState.AtmAuthenticatedState;
import atm.atmState.AtmInterface;
import atm.atmState.HasCardState;
import atm.atmState.NoCardState;
import atm.currency.Currency;
import atm.entites.Bank;
import atm.entites.Card;
import atm.exception.AccountException;
import atm.exception.AtmException;
import atm.exception.BankException;

public class AtmClass {
    private final HashMap<String, Bank> bankList;

    private Currency currency;
    private Card insertedCard;
    private Bank bankOfInsertedCard;
    private AtmInterface atmState;

    public AtmClass(Currency currency) {
        this.bankList = new HashMap<>();
        this.currency = currency;
        this.insertedCard = null;
        this.bankOfInsertedCard = null;
        this.atmState = new NoCardState();
    }

    public synchronized void addBank(Bank bank) throws BankException {
        atmState.addBank(bank, bankList);

    }

    public synchronized void insertcard(Card card) throws AtmException,BankException {
        if(this.insertedCard!=null){
            throw new AtmException("Card is already inserted");
        }
        Bank bank = findBankAssociatedWithCard(card);
        this.bankOfInsertedCard = bank;
        this.insertedCard = card;
        this.atmState = new HasCardState();

    }

    private Bank findBankAssociatedWithCard(Card card) {
        String cardId = card.getCardId();
        String last4CardId = cardId.substring(cardId.length() - 4);
        if (!bankList.containsKey(last4CardId)) {
            throw new BankException("Card not associated with any bank");
        }
        return bankList.get(last4CardId);

    }

    public synchronized boolean matchPin(int pin) throws AccountException {
        boolean pinMatched= atmState.enterPin(bankOfInsertedCard, insertedCard, pin);
        if(pinMatched==true){
            this.atmState = new AtmAuthenticatedState();
            return true;
        }
        else{
            this.atmState=new NoCardState();
            setBankAndCardNull();
            
            return false;
        }
        
    }

    public synchronized void checkBalance() {
        atmState.checkBalance(bankOfInsertedCard, insertedCard);
        System.out.println("ejecting card");
        this.atmState = (new NoCardState());
         setBankAndCardNull();
    }

    public synchronized void withDrawBalance(int balanace) throws AccountException {
       try{
         atmState.withDrawCash(bankOfInsertedCard, insertedCard,currency, balanace);
       
        this.atmState=new NoCardState();
        setBankAndCardNull();
       }
       catch(BankException | AccountException e){
            
       }
       
    }
    private void setBankAndCardNull(){
         System.out.println("ejecting card");
        this.bankOfInsertedCard=null;
        this.insertedCard=null;
    }
}
