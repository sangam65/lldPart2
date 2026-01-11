package atm.entites;

import java.util.HashMap;
import java.util.UUID;

import atm.exception.AccountException;

public class Bank {

    private final String bankId;
    private HashMap<String, Account> accounts;


    public Bank() {
        this.bankId = UUID.randomUUID().toString();
        this.accounts = new HashMap<>();
    }

    

    class Account {
        private final String accountId;
        private int balance;
        private Card card;

        public String getAccountId() {
            return accountId;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public Card getCard() {
            return card;
        }

        public void setCard(Card card) {
            this.card = card;
        }

        private Account(int balance, Card card) {
            this.accountId = UUID.randomUUID().toString() + bankId.substring(bankId.length() - 4);
            this.balance = balance;
            this.card = card;
        }

    }

    public String getBankId() {
        return bankId;
    }

    public synchronized Account addAccount(int balance, int pin) throws AccountException {
        if (balance < 0) {
            throw new AccountException("Balance can't be less than 0");
        }
        if (pin < 1000 || pin >= 10000) {
            throw new AccountException("Pin shall be positive number of 4 digitd");
        }

        Card card = new Card(this,pin);
        Account account = new Account(balance, card);
        accounts.put(card.getCardId(), account);
        return account;
    }

    public void changePin(Card card, int pin) throws AccountException {

        if (pin < 1000 || pin >= 10000) {
            throw new AccountException("Pin shall be positive number of 4 digitd");
        }
        if (!accounts.containsKey(card.getCardId())) {
            throw new AccountException("Card number is invalid");
        }
        try {
            synchronized (this) {
                card.setPin(pin);

            }
        } catch (Exception e) {

        }

    }

    public int withDrawCash(Card card, int balance) throws AccountException {
        if (balance <= 0) {
            throw new AccountException("You can't withdraw 0 or less than 0 balance ");

        }
        if (!accounts.containsKey(card.getCardId())) {
            throw new AccountException("Card number is invalid");
        }
        try {
            synchronized (this) {
                Account account = accounts.get(card.getCardId());
                if (account.getBalance() < balance) {
                    throw new AccountException("You don't have sufficient balance");

                }
                int currentBalance = account.getBalance();
                int remainingBalance = currentBalance - balance;
                account.setBalance(remainingBalance);
                System.out.println("Amount withdrawn is " + balance);
                return balance;
            }
        } catch (AccountException e) {
            throw e;
        }

    }

    public boolean pinAuthentication(Card card,int pin) throws AccountException{
         if (!accounts.containsKey(card.getCardId())) {
            throw new AccountException("Card number is invalid");
        }
        try{
             synchronized(this){
                
                boolean authenticated=card.getPin()==pin;
                return authenticated;

            }
        }
        catch(AccountException e){
            throw e;
        }
       
    }
    public boolean CardExist(Card card) {
          if (!accounts.containsKey(card.getCardId())) {
           return false;
        }

        return true;
    }
}
