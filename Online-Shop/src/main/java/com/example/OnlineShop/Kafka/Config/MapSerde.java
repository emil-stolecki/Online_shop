package com.example.OnlineShop.Kafka.Config;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapSerde<K,V> implements Serde<Map<K,V>>{

	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Class<K> keyType;
	private final Class<V> valueType;
	private final JsonSerde<V> valueSerde;
	
	public MapSerde(Class<K> keyType, Class<V> valueType,JsonSerde<V> valueSerde) {
		this.keyType = keyType;
        this.valueType = valueType;
        this.valueSerde=valueSerde;
	}
	@Override
	public Serializer<Map<K,V>> serializer() {
		return (topic, data) -> {
            try {
                return objectMapper.writeValueAsBytes(data);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error serializing map to JSON", e);
            }
        };
	}

	@Override
	public Deserializer<Map<K,V>> deserializer() {
		return (topic, data) -> {
	        try {
	            return objectMapper.readValue(data,
	            		objectMapper.getTypeFactory().constructMapType(Map.class, keyType, valueType));
	        } catch (IOException e) {
	            throw new RuntimeException("Error deserializing map from JSON", e);
	        }
	    };
	}

	

}
