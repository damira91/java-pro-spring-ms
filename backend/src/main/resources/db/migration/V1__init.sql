create table accounts
(
    id             bigserial primary key,
    client_id      bigint,
    account_number varchar(16),
    balance        numeric(6, 2),
    created_at     timestamp,
    updated_at     timestamp
);

insert into accounts (client_id, account_number, balance)
values (1, '1234123412341234', 1000);

create table transfers
(
    id                  bigserial primary key,
    client_id_from      bigint,
    client_id_to        bigint,
    account_number_from varchar(16),
    account_number_to   varchar(16),
    transfer_sum        numeric(6, 2),
    status              varchar(255),
    created_at          timestamp,
    updated_at          timestamp
);

insert into transfers (client_id_from, client_id_to, account_number_from, account_number_to, transfer_sum, status)
values (1, 1, '1234123412341234', '1234123412341234', 10.00, 'SUCCESS');