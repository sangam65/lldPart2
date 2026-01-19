package pubSubSystem.producer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import pubSubSystem.exception.ProducerException;
import pubSubSystem.topics.Topic;

public class Producer  {
    private final Map<String,Topic>topics;
    private final Map<String,AtomicInteger>topicCounters;
    public Producer(){
        this.topics=new ConcurrentHashMap<>();
        this.topicCounters=new ConcurrentHashMap<>();

    }
    public  void addTopic(Topic topic){
        if(topics.containsKey(topic.getTopicName())){
            throw new ProducerException("Topic is already added in given producer");
        }
       

       
    }
  
    public synchronized void produce(String topicName,String data) {
        if(!topics.containsKey(topicName)){
            throw new ProducerException("Topic not found in which data is to be produced");
        }
        Topic topic=topics.get(topicName);
        int parititonCount=topic.getNumberOfPartitions();
        int nextPartition=getNextPartition(topicName,parititonCount);
        topic.addDataToPartition(data, nextPartition);
       
    }
    private int getNextPartition(String topicName,int parititionCount){
        AtomicInteger partition=topicCounters.computeIfAbsent(topicName,k->new AtomicInteger(0));
        return Math.abs(partition.getAndIncrement()%parititionCount);
    }
    public synchronized void produceDataAtPartition(String topicName,String data,int parititon){
        if(!topics.containsKey(topicName)){
            throw new ProducerException("Topic not found in which data is to be produced");
        }
        Topic topic=topics.get(topicName);
        topic.addDataToPartition(data, parititon);
    }

}
