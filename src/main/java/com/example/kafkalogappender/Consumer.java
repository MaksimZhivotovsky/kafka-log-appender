package com.example.kafkalogappender;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args)  {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "test-group");

        KafkaConsumer<String, String>  kafkaConsumer = new KafkaConsumer<> (properties);
        List<String> topics = new ArrayList<>();
        topics.add("my-topic-1");
        kafkaConsumer.subscribe(topics);
        try{
            while (true){
                ConsumerRecords<String, String>  records = kafkaConsumer.poll(Duration.ofMinutes(20));
                for (ConsumerRecord<String, String> message: records){
                    System.out.println(String.format("Processing logs on kafka Topic - %s, Partition - %d, Value: %s", message.topic(), message.partition(), message.value()));
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            kafkaConsumer.close();
        }
    }
}
