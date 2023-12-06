package com.example.OnlineShop.Kafka.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
	
	
	//The main topic, activity logs are sent to
	@Bean
    public NewTopic userActivityTopic() {
        return TopicBuilder.name("user-activity")
                .partitions(1)
                .replicas(1)
                .build();
    }
	
	//Messages transformed in Stream, where user id is the key and the value is how much they bought of each product
	@Bean
    public NewTopic pbpu() {
        return TopicBuilder.name("products-bought-and-viewed-by-user")
                .partitions(1)
                .replicas(1)
                .build();
    }
	//Messages transformed in Stream, where product id is the key and the value is how much of it was bought in total
	@Bean
    public NewTopic pbitc() {
        return TopicBuilder.name("products-bought-and-viewed-in-total")
                .partitions(1)
                .replicas(1)
                .build();
	}
}
