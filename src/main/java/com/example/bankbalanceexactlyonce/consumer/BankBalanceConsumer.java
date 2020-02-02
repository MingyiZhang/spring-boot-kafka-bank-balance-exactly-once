package com.example.bankbalanceexactlyonce.consumer;

import com.example.bankbalanceexactlyonce.model.BankBalance;
import com.example.bankbalanceexactlyonce.topic.KafkaTopicConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BankBalanceConsumer {

//  private KafkaTopicConfig kafkaTopics;
//
//  public BankBalanceConsumer(KafkaTopicConfig kafkaTopics) {
//    this.kafkaTopics = kafkaTopics;
//  }

  @KafkaListener(topics = "${kafka.topic.bank-balance}")
  public void consume(BankBalance balance) {
    log.debug("Consumed :: " + balance.toString());
  }

}
