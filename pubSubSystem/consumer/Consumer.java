package pubSubSystem.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import pubSubSystem.exception.ConsumerException;
import pubSubSystem.message.Message;

public class Consumer {
    private final String consumerId;
    private final List<Message>dataList;

    private final String consumerName;
    private int currentData;
    public Consumer(String consumerName) {
        this.currentData=0;
        this.dataList=new CopyOnWriteArrayList<>();
        this.consumerName = consumerName;
        this.consumerId=UUID.randomUUID().toString();
    }
    public String getConsumerId() {
        return consumerId;
    }
   
    public String getConsumerName() {
        return consumerName;
    }
    public void consumeData(Message message){
        dataList.add(message);
        
    }
    public synchronized void dataConsumed(){
        if(currentData>=this.dataList.size()){
            throw new ConsumerException("All data consumed");
        }
        this.dataList.get(this.currentData).getMessage();
        this.currentData++;
    }
    public synchronized List<Message> consumedData(){
        List<Message>data=new ArrayList<>();
        for(int i=0;i<this.currentData;i++){
            data.add(this.dataList.get(i));
        }
        return data;
    }

    
}
