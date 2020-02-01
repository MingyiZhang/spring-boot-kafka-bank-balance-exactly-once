package com.example.bankbalanceexactlyonce.processor;

import com.example.bankbalanceexactlyonce.model.BankBalance;
import com.example.bankbalanceexactlyonce.model.BankTransaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
@EnableKafkaStreams
@Slf4j
public class KafkaStreamConfig {

  @Value("${kafka.topic.bank-transaction}")
  private String bankTransactionTopic;

  @Value("${kafka.topic.bank-balance}")
  private String bankBalanceTopic;

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
  @Bean
  public KStream<String, BankTransaction> transactionUpdateStream(StreamsBuilder builder) {
    KStream<String, BankTransaction> stream = builder.stream(bankTransactionTopic);
    stream.groupByKey()
        .aggregate(
            BankBalance::new,
            (key, transaction, oldBalance) -> new BankBalance(transaction, oldBalance),
            Materialized.as("bank-balance-aggregate")
        )
        .toStream()
        .to(bankBalanceTopic);

    return stream;
  }

}
