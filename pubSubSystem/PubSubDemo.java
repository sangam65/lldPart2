package pubSubSystem;

import pubSubSystem.consumer.Consumer;
import pubSubSystem.kafkaBroker.KafkaBroker;
import pubSubSystem.producer.Producer;
import pubSubSystem.topics.Topic;

public class PubSubDemo {
    public static void main(String []args){
        KafkaBroker kafkaBroker=new KafkaBroker();
        Producer producer=new Producer("producer", kafkaBroker);
        Topic topic=new Topic("topic_1");
        kafkaBroker.addTopic(topic);
        Consumer consumer=new Consumer("consumer");
        Consumer consumer2=new Consumer("consumer2"); 
        topic.addConsumer(consumer);
        topic.addConsumer(consumer2);
        producer.produce(topic.getTopicName(), "sangam");
        
    }
}
