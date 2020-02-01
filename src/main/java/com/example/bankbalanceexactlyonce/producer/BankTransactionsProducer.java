package com.example.bankbalanceexactlyonce.producer;

import com.example.bankbalanceexactlyonce.model.BankTransaction;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BankTransactionsProducer {

  @Value("${kafka.topic.bank-transaction}")
  private String bankTransactionTopic;

  private KafkaTemplate<String, BankTransaction> kafkaTemplate;

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  BankTransactionsProducer(KafkaTemplate<String, BankTransaction> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Scheduled(fixedRate = 1000)
  public void produce() {
    log.debug("Send to " + bankTransactionTopic);
    this.kafkaTemplate.send(createRandomTransaction("John"));
    this.kafkaTemplate.send(createRandomTransaction("steven"));
    this.kafkaTemplate.send(createRandomTransaction("alice"));
  }

  private ProducerRecord<String, BankTransaction> createRandomTransaction(String name) {
    Integer amount = ThreadLocalRandom.current().nextInt(0, 100);
    BankTransaction bankTransaction = new BankTransaction(name, amount, Instant.now());
    log.debug("Produced :: " + bankTransaction.toString());
    return new ProducerRecord<>(bankTransactionTopic, name, bankTransaction);
  }
}
