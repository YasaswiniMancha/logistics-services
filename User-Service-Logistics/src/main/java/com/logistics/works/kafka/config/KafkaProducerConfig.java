package com.logistics.works.kafka.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

@Configuration
public class KafkaProducerConfig {

	@Bean
	public ProducerFactory<String , Object> producerFactory(){
		Map<String , Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JacksonJsonSerializer.class);	//It converts Java objects → JSON before sending to Kafka.
        config.put(ProducerConfig.ACKS_CONFIG, "all");
		return new DefaultKafkaProducerFactory<>(config);
	}
	
	/*
	 Kafka only sends bytes.
	 Serializer converts:
	 Java Object → JSON → Bytes → Kafka Topic 
	 */
	
	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}
}
