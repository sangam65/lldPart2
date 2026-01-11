package atm.currency;

public abstract class Currency {
    private final Currency next;
    private int count;
    private final CurrencyType currencyType;

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public Currency getNext() {
        return next;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Currency(Currency next, int count, CurrencyType currencyType) {
        this.next = next;
        this.currencyType = currencyType;
        this.count = count;
    }

    public boolean canProcess(int balance) {
        int money = currencyType.getValue();

        int notes = balance / money;
        if (notes > count) {
            System.out.println("Atm does not have sufficient balance");
        }
        balance -= (notes * money);
        if (balance == 0)
            return true;
        if (next == null)
            return false;
        return next.canProcess(balance);

    }

    public void displayNotes(int balance) {
            int money = currencyType.getValue();

        int notes = balance / money;
        System.out.println("notes of "+currencyType+" "+notes);
          balance -= (notes * money);
        if (balance == 0)
            return ;
        next.canProcess(balance);
    }
    public boolean addCurrency(CurrencyType currencyType,int count){
        if(this.currencyType.equals(currencyType)){
            this.currencyType.setValue(count+this.currencyType.getValue());
            return true;
        }
        if(next==null)return false;
        return next.addCurrency(currencyType, count);
    }
}
