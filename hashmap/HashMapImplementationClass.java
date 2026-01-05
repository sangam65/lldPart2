package hashmap;

import hashmap.entities.HashMapImpl;

public class HashMapImplementationClass {
    public static void main(String []args){
        HashMapImpl<Integer,Integer> hashMapImpl=new HashMapImpl<>();
        hashMapImpl.put(2, 5);
          System.out.println("For key 2"+hashMapImpl.getValue(2));
        hashMapImpl.put(2,5);
         System.out.println("For key 2"+hashMapImpl.getValue(2));
    }
}
