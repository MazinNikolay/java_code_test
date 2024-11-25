package com.example.java_code_test.service;

import com.example.java_code_test.dto.WalletDto;
import com.example.java_code_test.entity.WalletEntity;

import java.math.BigDecimal;
import java.util.UUID;

public interface WalletService {
    void operate(WalletDto wallet);

    BigDecimal getAmount(UUID walletId);

    BigDecimal calculateAmount(WalletDto wallet, WalletEntity entity);

    boolean checkAmountSize(BigDecimal initAmount, BigDecimal operateAmount);

    WalletEntity checkWalletExist(UUID walletId);
}