package com.example.bankbalanceexactlyonce;

import com.example.bankbalanceexactlyonce.producer.BankTransactionsProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BankBalanceExactlyOnceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BankBalanceExactlyOnceApplication.class, args);
  }

  @Bean
  public ApplicationRunner runner(BankTransactionsProducer producer) {
    return (args -> producer.produce());
  }

}
