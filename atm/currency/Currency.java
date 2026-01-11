package atm.currency;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class Currency {
    private final Currency next;
    private int count;
    private final CurrencyType currencyType;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public Currency getNext() {
        return next;
    }

    public int getCount() {
        return count;
    }

    public synchronized void setCount(int count) {
        this.count = count;
    }

    public Currency(Currency next, int count, CurrencyType currencyType) {
        this.next = next;
        this.currencyType = currencyType;
        this.count = count;
    }

    public boolean reserveAndValidate(int balance) {
        try {
            lock.writeLock().lock();
            int money = currencyType.getValue();
            int notes = balance / money;
            
            if (notes > count) {
                return false;  // Can't dispense from this denomination
            }
            
            // RESERVE the notes (mark as taken)
            this.count -= notes;
            balance -= (notes * money);
            
            if (balance == 0)
                return true;  // Success, all denominations reserved
            
            if (next == null)
                return false;  // Not enough for remaining balance
            
            return next.reserveAndValidate(balance);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void displayNotes(int balance) {
        int money = currencyType.getValue();

        int notes = balance / money;

        balance -= (notes * money);

       
        System.out.println("notes of " + currencyType + " " + notes);

        if (balance == 0)
            return;
        next.displayNotes(balance);

    }

    public synchronized boolean addCurrency(CurrencyType currencyType, int count) {
        if (this.currencyType.equals(currencyType)) {
            this.count += count;
            return true;
        }
        if (next == null)
            return false;
        return next.addCurrency(currencyType, count);
    }
}
