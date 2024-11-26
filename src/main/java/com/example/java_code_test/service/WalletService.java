package com.example.java_code_test.service;

import com.example.java_code_test.dto.WalletDto;
import com.example.java_code_test.entity.WalletEntity;

import java.math.BigDecimal;
import java.util.UUID;

public interface WalletService {
    /**
     * Метод для обработки операции по
     * указанному счету.
     *
     * @param wallet ДТО кошелька из запроса.

     */
    void operate(WalletDto wallet);
    /**
     * Метод для получения текущего,
     * остатка средств по счету.
     *
     * @param walletId UUID идентификатор.
     *
     * @return актуальное значение остатка средств.
     */
    BigDecimal getAmount(UUID walletId);
    /**
     * Метод для проведения операции по,
     * указанному счету в зависимости
     * от запроса пользователя.
     *
     * @param wallet ДТО кошелька из запроса.
     * @param entity Сущность кошелька из БД.
     *
     * @return актуальное значение остатка средств.
     */
    BigDecimal calculateAmount(WalletDto wallet, WalletEntity entity);
    /**
     * Метод для проверки остатка средств,
     * по указанному счету.
     *
     * @param initAmount исходное состояние счета.
     * @param operateAmount сумма для изменения счета.
     *
     * @return логическая доступность проведения операции.
     */
    boolean checkAmountSize(BigDecimal initAmount, BigDecimal operateAmount);
    /**
     * Метод для проверки наличия кошелька.
     *
     * @param walletId UUID идентификатор.
     *
     * @return сущность кошелька из БД.
     */
    WalletEntity checkWalletExist(UUID walletId);
}