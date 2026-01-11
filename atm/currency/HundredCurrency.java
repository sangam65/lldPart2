package atm.currency;

public class HundredCurrency extends Currency{
   public HundredCurrency(Currency next,int count){
    super(next, count,CurrencyType.HUNDRED);
   }
}
