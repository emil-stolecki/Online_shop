package com.example.OnlineShop.Kafka.Config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example.OnlineShop.Kafka.Message.Message;


//Kafka producer that will be used to send messages to main kafka topic 
//The message have Long key and value of type Message

@Configuration
public class Producer {
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	
	
	public Map<String,Object>producerConfig(){
		Map<String,Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return props;
	}
	@Bean
	public ProducerFactory<Long,Message> producerFactory(){
		DefaultKafkaProducerFactory<Long, Message> producerFactory = new DefaultKafkaProducerFactory<>(producerConfig());
		return producerFactory;
	}
	
	@Bean
	public KafkaTemplate<Long,Message> kafkaTemplate(ProducerFactory<Long,Message> producerFactory){
		return new KafkaTemplate<>(producerFactory);
	}
}
