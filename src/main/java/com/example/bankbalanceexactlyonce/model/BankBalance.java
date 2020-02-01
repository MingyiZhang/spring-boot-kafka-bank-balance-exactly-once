package com.example.bankbalanceexactlyonce.model;

import java.time.Instant;
import lombok.Data;

@Data
public class BankBalance {
  private Integer count;
  private Integer balance;
  private Instant time;

  public BankBalance() {
    this.count = 0;
    this.balance = 0;
    this.time = Instant.ofEpochMilli(0L);
  }

  public BankBalance(BankTransaction transaction, BankBalance oldBalance) {
    this.count = oldBalance.getCount() + 1;
    this.balance = oldBalance.getBalance() + transaction.getAmount();
    this.time = (oldBalance.getTime().isBefore(transaction.getTime())) ? transaction.getTime()
        : oldBalance.getTime();
  }

}
