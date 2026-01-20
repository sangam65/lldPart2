package pubSubSystem.consumer;

import java.util.UUID;

public class Consumer {
    private final String consumerId;

    private final String consumerName;
    public Consumer(String consumerName) {

        this.consumerName = consumerName;
        this.consumerId=UUID.randomUUID().toString();
    }
    public String getConsumerId() {
        return consumerId;
    }
   
    public String getConsumerName() {
        return consumerName;
    }
    public void consumeData(String data){
        System.out.println(data);
    }

    
}
