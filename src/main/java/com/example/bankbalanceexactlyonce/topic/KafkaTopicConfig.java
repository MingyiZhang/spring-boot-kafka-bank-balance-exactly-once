package com.example.bankbalanceexactlyonce.topic;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConfigurationProperties(prefix = "kafka.topic")
public class KafkaTopicConfig {

  @Getter @Setter
  private String bankTransaction;

  @Getter @Setter
  private String bankBalance;

  @Bean
  public NewTopic createBankTransactionTopic() {
    return createNewTopic(bankTransaction, 1, 1);
  }

  @Bean
  public NewTopic createBankBalanceTopic() {
    return createNewTopic(bankBalance, 1, 1);
  }

  private NewTopic createNewTopic(String topic, Integer partitions, Integer replicas) {
    log.debug("Create topic :: " + topic);
    return TopicBuilder.name(topic)
        .partitions(partitions)
        .replicas(replicas)
        .build();
  }

}
