package com.example.OnlineShop.Kafka.Config;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerde<T> implements Serde<T>{

	public static final ObjectMapper mapper = new ObjectMapper();
	private final Class<T> type;
	
	public JsonSerde(Class<T> type) {
		this.type = type;
	}

	
	
	@Override
	public Serializer<T> serializer() {
        return (topic, message) -> {
            try {
                return serialize(message);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new RuntimeException("Error serializing message to JSON", e);
            }
        };
    }

	@Override
	public Deserializer<T> deserializer() {
		return (topic, message) -> {
	        try {
	            return deserialize(message);
	        } catch (JsonProcessingException je) {
	            je.printStackTrace();
	            throw new RuntimeException(je);
	        } catch (IOException ioe) {
	            ioe.printStackTrace();
	            throw new RuntimeException(ioe);
	        }
	    };
    }
	
	private byte[] serialize(T message) throws JsonProcessingException {
		return mapper.writeValueAsBytes(message);
		
	}
	private T deserialize(byte[] message) throws StreamReadException, DatabindException, IOException  {
        return mapper.readValue(message, type); 
    }
}
