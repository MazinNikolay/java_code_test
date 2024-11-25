package com.example.java_code_test.controller;

import com.example.java_code_test.dto.WalletDto;
import com.example.java_code_test.service.impl.WalletServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
@Tag(name = "Проведение транзакция и получение информации о состоянии о счете")
public class WalletOperationController {
    private final WalletServiceImpl service;

    @Operation(summary = "Пополнение/Снятие денежных средств")
    @PostMapping("/wallet")
    @ApiResponses(value = @ApiResponse(responseCode = "200",
            description = "ok",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = WalletDto.class))}
    ))
    public ResponseEntity<WalletDto> walletOperation(@RequestBody WalletDto wallet) {
        service.operate(wallet);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение данных о состоянии счета")
    @GetMapping("/wallets/{WALLET_UUID}")
    @ApiResponses(value = @ApiResponse(responseCode = "200",
            description = "ok"))
    public ResponseEntity<BigDecimal> getWalletAmount(@PathVariable UUID WALLET_UUID) {
        return ResponseEntity.ok().body(service.getAmount(WALLET_UUID));
    }
}