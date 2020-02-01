package com.example.bankbalanceexactlyonce.topic;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaTopicConfig {

  @Value("${kafka.topic.bank-transaction}")
  private String bankTransactionTopic;

  @Value("${kafka.topic.bank-balance}")
  private String bankBalanceTopic;

  @Bean
  public NewTopic getBankTransactionTopic() {
    return createNewTopic(bankTransactionTopic, 1, 1);
  }

  @Bean
  public NewTopic getBankBalanceTopic() {
    return createNewTopic(bankBalanceTopic, 1, 1);
  }

  private NewTopic createNewTopic(String topic, Integer partitions, Integer replicas) {
    log.debug("Create topic :: " + topic);
    return TopicBuilder.name(topic)
        .partitions(partitions)
        .replicas(replicas)
        .build();
  }

}
