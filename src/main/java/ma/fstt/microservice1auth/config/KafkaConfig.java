package ma.fstt.microservice1auth.config;


import ma.fstt.microservice1auth.constant.AppConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {



    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name(AppConstant.CLIENT_INFO)
                .build();
    }
}
