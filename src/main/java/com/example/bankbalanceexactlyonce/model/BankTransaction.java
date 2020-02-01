package com.example.bankbalanceexactlyonce.model;

import java.time.Instant;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankTransaction {
  private @NotNull String name;
  private @NotNull Integer amount;
  private @NotNull Instant time;
}
