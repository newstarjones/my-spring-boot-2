package com.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

//    @KafkaListener(
//            topicPartitions = {
//                    @TopicPartition(topic = "topic1", partitions = { "0", "1" }),
//                    @TopicPartition(topic = "topic2", partitions = { "2" },
//                            partitionOffsets = { @PartitionOffset(partition = "2", initialOffset= "1") }
//                    )
//            }
//    )
//    public void listen(String message) {
//        System.out.println("接收到消息: " + message);
//    }

//    @KafkaListener(topics = "test-topic", groupId = "my-group")
//    public void listen(String message) {
//        System.out.println("接收到消息: " + message);
//    }

}
