package pubSubSystem.topics;

import java.time.Duration;


import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import pubSubSystem.consumer.Consumer;
import pubSubSystem.exception.ConsumerException;
// import pubSubSystem.exception.PartitionException;
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
    // @Scheduled()
    public void removeFirstData(){
        while(!dataList.isEmpty()){
           Duration duration=Duration.between(dataList.peekFirst().getTimeStamp(),LocalDateTime.now());
      
            if(duration.toHours()>12){
                dataList.pollFirst();
            }
            else{
                break;
            }
        }
    }

    protected class Partition {
        private final String partitionId;
        private final String topicId;
        private final Deque<String> datas;

        public String getPartitionId() {
            return partitionId;
        }

        public String getTopicId() {
            return topicId;
        }

        public void addData(String data) {
            this.datas.addLast(data);
        }

        public Partition(String topicId) {
            this.partitionId = UUID.randomUUID().toString();
            this.topicId = topicId;
            this.datas = new ArrayDeque<>();
        }

        
    }


}
