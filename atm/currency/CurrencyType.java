package atm.currency;

public enum CurrencyType {
    HUNDRED(100),
    FIVE_HUNDRED(500),
    THOUSAND(1000);
    private  int value;
    public int getValue() {
        return value;
    }
    private CurrencyType(int value){
        this.value=value;
    }
    public void setValue(int value){
        this.value=value;
    }
}
