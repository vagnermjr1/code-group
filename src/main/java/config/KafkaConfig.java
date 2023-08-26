package config;

import dto.MembroDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configMap = new HashMap<>();

        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "br.com.codegroup.gerenciarportfolio.dto");

        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092,localhost:39092");

        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<String, Object>(configMap);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


    @Bean
    public ConsumerFactory<String, MembroDto> consumerFactory() {
        Map<String, Object> configMap = new HashMap<>();

        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id-json-1");

        configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "br.com.codegroup.gerenciarportfolio.dto");

        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092,localhost:39092");

        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(configMap);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MembroDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MembroDto> factory = new ConcurrentKafkaListenerContainerFactory<String, MembroDto>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
