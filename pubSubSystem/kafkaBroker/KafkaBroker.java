package pubSubSystem.kafkaBroker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import pubSubSystem.consumer.Consumer;
import pubSubSystem.exception.TopicException;
import pubSubSystem.topics.Topic;

public class KafkaBroker {
    private final Map<String, Topic> topicList;

    public KafkaBroker() {
        this.topicList = new ConcurrentHashMap<>();
    }

    public void addTopic(Topic topic) {
        if (topicList.containsKey(topic.getTopicName())) {
            throw new TopicException("Topic already added");
        }
        topicList.put(topic.getTopicName(), topic);
    }

    public void removeTopic(Topic topic) {
        if (!topicList.containsKey(topic.getTopicName())) {
            throw new TopicException("Topic not found in broker");
        }
        topicList.remove(topic.getTopicName());
    }

    public synchronized void produceData(String topicName, String data) {
        if (!topicList.containsKey(topicName)) {
            throw new TopicException("Topic not found in broker");
        }
        Topic topic = topicList.get(topicName);
        topic.addDataToPartition(data);
    }

    public synchronized void removeConsumer(String topicName, Consumer consumer) {
        if (!topicList.containsKey(topicName)) {
            throw new TopicException("Topic not found in broker");
        }
        Topic topic = topicList.get(topicName);
        topic.removeConsumer(consumer);
        
    }
    public synchronized void addConsumer(String topicName, Consumer consumer) {
        if (!topicList.containsKey(topicName)) {
            throw new TopicException("Topic not found in broker");
        }
        Topic topic = topicList.get(topicName);
        topic.addConsumer(consumer);
        
    }
}
