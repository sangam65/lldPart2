package pubSubSystem.topics;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import pubSubSystem.consumer.Consumer;
import pubSubSystem.exception.ConsumerException;


public class Topic {

    private final String topicName;
    private final String topicId;
    private Map<String,Consumer>consumerList;
    private final Deque<String>dataList;
    public Topic(String topicName) {

        this.topicName = topicName;
        this.topicId = UUID.randomUUID().toString();
        this.dataList=new ArrayDeque<>();
        this.consumerList=new ConcurrentHashMap<>();
        
    }

   
    
    public List<Consumer> getConsumerList() {
        List<Consumer>consumers=new ArrayList<>();
        for(Consumer consumer:consumerList.values()){
            consumers.add(consumer);
        }
        return consumers;
        
    }
    public  synchronized void addConsumer(Consumer consumer){
        if(consumerList.containsKey(consumer.getConsumerId())){
            throw new ConsumerException("Consumer is already added");
        }
        consumerList.put(consumer.getConsumerId(),consumer);
    }
 




   
    public String getTopicName() {
        return topicName;
    }

    public String getTopicId() {
        return topicId;
    }

    public synchronized void addDataToPartition(String data){
        dataList.addLast(data);
        notifyConsumer();
    
    }
    private  void removeData(){
        dataList.pollFirst();
    }
    private void notifyConsumer(){
        List<Consumer>consumers=getConsumerList();
        for(Consumer consumer:consumers){
            consumer.consumeData(dataList.peekFirst());
        }
        removeData();
    }
    public synchronized void removeConsumer(Consumer consumer){
        if(!consumerList.containsKey(consumer.getConsumerId())){
            throw new ConsumerException("Consumer not present in topic");
        }
        consumerList.remove(consumer.getConsumerId());
    }

    


}
