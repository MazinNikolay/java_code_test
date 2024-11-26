package com.example.java_code_test.controller;

import com.example.java_code_test.dto.WalletDto;
import com.example.java_code_test.entity.OperationType;
import com.example.java_code_test.entity.WalletEntity;
import com.example.java_code_test.repository.WalletRepository;
import com.example.java_code_test.service.impl.WalletServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
class WalletOperationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WalletRepository repository;

    @SpyBean
    private WalletServiceImpl service;

    @InjectMocks
    private WalletOperationController controller;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        this.mapper = new ObjectMapper();
    }

    @Test
    void walletOperationTest() throws Exception {
        //given
        WalletDto dto = WalletDto.builder()
                .walletId(UUID.randomUUID())
                .operationType(OperationType.DEPOSIT)
                .amount(BigDecimal.valueOf(1000)).build();

        WalletEntity entity = WalletEntity.builder()
                .walletId(UUID.randomUUID())
                .amount(BigDecimal.valueOf(1000)).build();
        //when
        when(repository.findByIdForUpdate(any(UUID.class))).thenReturn(Optional.of(entity));
        when(repository.save(any(WalletEntity.class))).thenReturn(entity);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/wallet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getWalletAmountTest() throws Exception {
        //given
        UUID walletId = UUID.randomUUID();

        WalletDto dto = WalletDto.builder()
                .walletId(walletId)
                .operationType(OperationType.DEPOSIT)
                .amount(BigDecimal.valueOf(1000)).build();

        WalletEntity entity = WalletEntity.builder()
                .walletId(walletId)
                .amount(BigDecimal.valueOf(1000)).build();
        //when
        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(entity));
        when(repository.save(any(WalletEntity.class))).thenReturn(entity);
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/wallets/{WALLET_UUID}", walletId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}