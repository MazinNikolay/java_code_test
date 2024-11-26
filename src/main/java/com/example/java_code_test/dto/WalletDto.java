package com.example.java_code_test.dto;

import com.example.java_code_test.entity.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletDto {
    @Schema(description = "Wallet")
    private UUID walletId;
    private OperationType operationType;
    private BigDecimal amount;
}