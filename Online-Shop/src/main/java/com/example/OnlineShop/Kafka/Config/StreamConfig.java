package com.example.OnlineShop.Kafka.Config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.core.KafkaTemplate;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.common.utils.Bytes;

import com.example.OnlineShop.Kafka.Message.Product;
import com.example.OnlineShop.Kafka.Message.Message;


@Configuration
//@EnableKafka
//@EnableKafkaStreams
public class StreamConfig {
	
	 @Value("${spring.kafka.bootstrap-servers}")
	 private String bootstrapAddress;
	 @Value("${spring.kafka.streams.application-id}")
	 private String applicationId;
	
	 private String sourceTopic="user-activity";
	 private String outputTopicPBBU="products-bought-and-viewed-by-user";
	 //^ KEY: user id, VALUE: Map<Long,Product> -> {product id:, items bought so far:,
	 //												product page viewed so far:, price, categories:}
	 //^ It aggregates the total amount of each product bought and viewed by an user
	 private String outputTopicPBIT="products-bought-and-viewed-in-total";
	 //^ KEY: product id, VALUE: long
	 //^ It aggregates the total amount of each product bought and viewed by all user

	 
	 
	 public Properties kStreamsConfig(){
		 	Properties props = new Properties();
	        props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
	        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
	        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
	        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class.getName());
	        
	        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
	        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_DOC, "0");//see changes immediately

	        return props;
	    }
	 
	
	 	private Topology getTopology() {
	 		
	        final StreamsBuilder builder = new StreamsBuilder();
	   
	        final JsonSerde<Product> productSerde = new JsonSerde<Product>(Product.class);
	        final JsonSerde<Message>messageSerde=new JsonSerde<Message>(Message.class);
	        final MapSerde<Long,Product>mapSerde=new MapSerde<Long,Product>(Long.class,
	        																			Product.class,
	        																			productSerde);
	        
	        KStream<Long, Message> kStream = builder.stream(sourceTopic,
	        												Consumed.with(Serdes.Long(), messageSerde));  
	        
	        //send to products-bought-and-viewed-by-user
	        kStream
	        .groupByKey()    
	        .aggregate(HashMap::new,(key, newValue, aggregate) -> {
	        	
	        	if (aggregate.containsKey(newValue.productId())) {        		
	        		Product bp = aggregate.get(newValue.productId());
	        		bp.update(newValue);
	        		aggregate.put(
	        				newValue.productId(),bp);
	        		
	        	}
	        	else {
	        		
	        		Product bp = new Product();
	        		bp.update(newValue);
	        		aggregate.put(newValue.productId(),bp);
	        		
	        	}
	        		        	
	        	return aggregate;
	        },
	        Materialized.with(Serdes.Long(),mapSerde)
	        )
	        .toStream()
	        .to(outputTopicPBBU,Produced.with(Serdes.Long(),mapSerde ));
	        
	        
	     
	        //send to products-bought-and-viewed-in-total
	        kStream	   
	        .groupBy((k,v)->v.productId(), Grouped.with(Serdes.Long(),messageSerde))
	        .aggregate(Product::new,(key, newValue, aggregate)->{
	        	return aggregate.update(newValue);
	        },Materialized.with(Serdes.Long(), productSerde))
	        .toStream()
	        .to(outputTopicPBIT,Produced.with(Serdes.Long(), productSerde));
	         
	        return builder.build();
	        
	             
	        
	 	}
	 	@Bean
	    public KafkaStreams kafkaStreams() {
	        final Topology topology = getTopology();
	        final KafkaStreams streams = new KafkaStreams(topology, kStreamsConfig());
	        streams.cleanUp();
	        streams.start();
	    
	        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

	        return streams;
	    }
	 	
	 	
}
