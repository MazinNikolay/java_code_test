-- liquibase formatted sql
CREATE TABLE wallets(
    wallet_id   UUID    primary key,
    amount      NUMERIC
);

INSERT INTO wallets (wallet_id, amount) VALUES ('e5f3e56b-4da4-4923-ac0f-fdf6bb410d4d', 1000);