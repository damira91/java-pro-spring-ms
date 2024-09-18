DO $$
DECLARE
i INT;
    transfer_sum        NUMERIC;
    client_id_from      BIGINT;
    client_id_to        BIGINT;
    account_number_from TEXT;
    account_number_to   TEXT;
    status              TEXT;
BEGIN
FOR i IN 1..100 LOOP
    transfer_sum := 100 + random() * 900;
    client_id_from := floor(random() * 1000)::BIGINT + 1;
    client_id_to := floor(random() * 1000)::BIGINT + 1;
    account_number_from := '12345' || (random() * 100)::INT;
    account_number_to := '54321' || (random() * 100)::INT;
    status := CASE WHEN random() > 0.5 THEN 'COMPLETED' ELSE 'FAILED' END;

INSERT INTO transfers (client_id_from, client_id_to, account_number_from, account_number_to, transfer_sum, status, created_at)
VALUES (client_id_from, client_id_to, account_number_from, account_number_to, transfer_sum, status, NOW());
END LOOP;
END $$;