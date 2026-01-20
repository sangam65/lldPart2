package pubSubSystem.producer;

import java.util.UUID;
import pubSubSystem.kafkaBroker.KafkaBroker;

public class Producer  {

    private final String producerName;
    private final String producerId;
    private final KafkaBroker kafkaBroker;

    public String getProducerName() {
        return producerName;
    }
    public String getProducerId() {
        return producerId;
    }
    public Producer(String producerName,KafkaBroker kafkaBroker){
       
        this.producerId=UUID.randomUUID().toString();
        this.producerName=producerName;
        this.kafkaBroker=kafkaBroker;
       

    }
   
  
    public synchronized void produce(String topicName,String data) {
        this.kafkaBroker.produceData(topicName, data);
       
    }
   
    

}
