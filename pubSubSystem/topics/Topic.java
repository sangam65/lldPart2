package pubSubSystem.topics;

import java.time.Duration;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import pubSubSystem.consumer.Consumer;
import pubSubSystem.exception.ConsumerException;
import pubSubSystem.message.Message;


public class Topic {

    private final String topicName;
    private final String topicId;
    private Map<String,Consumer>consumerList;
    private final Deque<Message>dataList;
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
        dataList.addLast(new Message(data));
        notifyConsumer();
    
    }
    
    private void notifyConsumer(){
        List<Consumer>consumers=getConsumerList();
        for(Consumer consumer:consumers){
            consumer.consumeData(dataList.peekFirst());
        }
     
    }
    public synchronized void removeConsumer(Consumer consumer){
        if(!consumerList.containsKey(consumer.getConsumerId())){
            throw new ConsumerException("Consumer not present in topic");
        }
        consumerList.remove(consumer.getConsumerId());
    }
    // scheduled to run at every day will remove message whose time is greater than 12 hours will remove it
    public void removeFirstData(){
        while(!dataList.isEmpty()){
           Duration duration=Duration.between(LocalDate.now(),dataList.peekFirst().getTimeStamp());
      
            if(duration.toHours()>12){
                dataList.pollFirst();
            }
            else{
                break;
            }
        }
    }

    


}
