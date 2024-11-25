-- liquibase formatted sql
CREATE TABLE wallets(
    wallet_id   UUID    primary key,
    amount      NUMERIC
);