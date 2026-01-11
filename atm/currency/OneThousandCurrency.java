package atm.currency;

public class OneThousandCurrency extends Currency{

    public OneThousandCurrency(Currency currency, int count) {
        super(currency,count,CurrencyType.THOUSAND);
        
    }

}
