package pubSubSystem.topics;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.UUID;

import pubSubSystem.exception.PartitionException;

public class Topic {
    private final Partition []partitions;
    private int numberOfPartitions;
    private final String topicName;
    private final String topicId;
    
    public int getNumberOfPartitions() {
        return numberOfPartitions;
    }

    

    public Topic(String topicName, int numberOfPartitions) {

        this.topicName = topicName;
        this.topicId = UUID.randomUUID().toString();
        this.numberOfPartitions = numberOfPartitions;

        this.partitions=new Partition[numberOfPartitions];
        
    }



   
    public String getTopicName() {
        return topicName;
    }

    public String getTopicId() {
        return topicId;
    }

    public void addDataToPartition(String data,int partitionNumber){
        if(partitionNumber>=numberOfPartitions){
            throw new PartitionException("PartitionNumber is greater than no of partitions given topic has");
        }
        Partition partition=partitions[partitionNumber];
        partition.addData(data);
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

        public String consumeData() {
            if (datas.isEmpty()) {
                throw new PartitionException("No data to consume");
            }
            return datas.pollFirst();
        }
    }
}
