package atm.currency;

public class FiveHundredCurrency  extends Currency{

    public FiveHundredCurrency(Currency next, int count) {
        super(next, count,CurrencyType.FIVE_HUNDRED);
       
    }

}
