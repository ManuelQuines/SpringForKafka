package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
@Bean
    public NewTopic generateTopic(){

    Map<String, String> configurations = new HashMap<>();
    configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
    //delete (borra el mensaje por completo), compact (mantiene el mensaje actualizado en caso de repetirse)
    configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
    //Tiempo de retención de mensaje (por defecto viene en -1, quiere decir nunca borrar)
    configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
    //Tamaño máximo del segmento, por defecto en bytes viene 1GB 1073741824
    configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "12");
    //Tamaño máximo de cada mensaje por defecto viene en 1MB


    return TopicBuilder.name("QuinesM-Topic")
            .partitions(2)
            .replicas(2)
            .configs(configurations)
            .build();
}



}
