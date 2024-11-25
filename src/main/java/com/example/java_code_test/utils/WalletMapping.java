package com.example.java_code_test.utils;

import com.example.java_code_test.dto.WalletDto;
import com.example.java_code_test.entity.WalletEntity;
import org.springframework.stereotype.Service;

@Service
public class WalletMapping {
    public WalletEntity mapToEntity(WalletDto dto) {
        return WalletEntity.builder()
                .walletId(dto.getWalletId())
                .amount(dto.getAmount())
                .build();
    }
}