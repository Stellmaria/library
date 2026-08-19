--liquibase formatted sql

--changeset stell:fix-invalid-order-return-dates
UPDATE orders
SET return_date = order_date + INTERVAL '1 month'
WHERE return_date < order_date;

ALTER TABLE orders
    ADD CONSTRAINT chk_orders_return_date
        CHECK (return_date >= order_date);
--rollback ALTER TABLE orders DROP CONSTRAINT IF EXISTS chk_orders_return_date;

--changeset stell:enforce-non-negative-book-quantity
UPDATE book
SET quantity = 0
WHERE quantity < 0;

ALTER TABLE book
    ADD CONSTRAINT chk_book_quantity_non_negative
        CHECK (quantity >= 0);
--rollback ALTER TABLE book DROP CONSTRAINT IF EXISTS chk_book_quantity_non_negative;
