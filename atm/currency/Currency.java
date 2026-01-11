package atm.currency;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class Currency {
    private final Currency next;
    private int count;
    private final CurrencyType currencyType;
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

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

    public boolean canProcess(int balance) {
        try {
            reentrantReadWriteLock.writeLock().lock();
            int money = currencyType.getValue();

            int notes = balance / money;
            if (notes > count) {
                System.out.println("Atm does not have sufficient balance");
                return false;
            }
            balance -= (notes * money);
            if (balance == 0)
                return true;
            if (next == null)
                return false;
            return next.canProcess(balance);
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

    }

    public void displayNotes(int balance) {
        try {
            reentrantReadWriteLock.writeLock().lock();

            int money = currencyType.getValue();

            int notes = balance / money;

            balance -= (notes * money);
            this.count -= notes;
            System.out.println("notes of " + currencyType + " " + notes);
            if (balance == 0)
                return;
            next.displayNotes(balance);
        } catch (Exception e) {

        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }

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
