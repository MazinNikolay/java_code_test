package com.example.java_code_test.service.impl;

import com.example.java_code_test.dto.WalletDto;
import com.example.java_code_test.entity.WalletEntity;
import com.example.java_code_test.repository.WalletRepository;
import com.example.java_code_test.service.WalletService;
import com.example.java_code_test.utils.WalletMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository repository;
    private final WalletMapping mapping;

    @Override
    public void operate(WalletDto wallet) {
        WalletEntity entity = checkWalletExist(wallet.getWalletId());
        BigDecimal resultAmount = calculateAmount(wallet, entity);
        wallet.setAmount(resultAmount);
        repository.save(mapping.mapToEntity(wallet));
    }

    @Override
    public BigDecimal calculateAmount(WalletDto wallet, WalletEntity entity) {
        switch (wallet.getOperationType()) {
            case DEPOSIT -> entity.setAmount(entity.getAmount().add(wallet.getAmount()));
            case WITHDRAW -> {
                if (checkAmountSize(entity.getAmount(), wallet.getAmount())) {
                    entity.setAmount(entity.getAmount().subtract(wallet.getAmount()));
                } else {
                    throw new RuntimeException("Not enough limit");
                }
            }
            default -> throw new RuntimeException("Operation is not supported");
        }
        return entity.getAmount();
    }

    @Override
    public BigDecimal getAmount(UUID walletId) {
        return checkWalletExist(walletId).getAmount();
    }

    @Override
    public boolean checkAmountSize(BigDecimal initAmount, BigDecimal operateAmount) {
        return initAmount.subtract(operateAmount).longValue() >= 0;
    }

    @Override
    public WalletEntity checkWalletExist(UUID walletId) {
        return repository.findById(walletId).
                orElseThrow(() -> new RuntimeException("Wallet is not found"));
    }
}