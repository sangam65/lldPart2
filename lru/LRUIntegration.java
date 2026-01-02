package lru;

import lru.entities.LRU;

public class LRUIntegration {
    public static void main(String []args){
        LRU<Integer,String> lru=new LRU<>(5);
        lru.addValue(3, "sangam");
        lru.addValue(4, "rahul");
        String value=lru.getValue(3);
                System.out.println(value);
        lru.addValue(2, "Kumbhkaran");
        lru.addValue(8, "Anjali");
        lru.addValue(10, "anjali birthday");
        lru.addValue(1, "new January");

    }
}
