-- ============================================================
-- Mechanic Shop Management System
-- Database: mechanic_shop (MariaDB / MySQL)
-- ============================================================

-- Drop and recreate database
DROP DATABASE IF EXISTS mechanic_shop;
CREATE DATABASE mechanic_shop
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE mechanic_shop;

-- ============================================================
-- TABLE: customers
-- Referenced by: CustomerDAO.java, VehicleDAO.java
-- ============================================================
CREATE TABLE customers (
    id        INT          NOT NULL AUTO_INCREMENT,
    name      VARCHAR(100) NOT NULL,
    phone     VARCHAR(20)  NOT NULL,
    email     VARCHAR(100) NOT NULL,
    address   VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

-- ============================================================
-- TABLE: mechanics
-- Referenced by: MechanicDAO.java, WorkOrderDAO.java
-- ============================================================
CREATE TABLE mechanics (
    id               INT           NOT NULL AUTO_INCREMENT,
    name             VARCHAR(100)  NOT NULL,
    phone            VARCHAR(20)   NOT NULL,
    email            VARCHAR(100)  NOT NULL,
    specialization   VARCHAR(100)  NOT NULL,
    hourly_rate      DECIMAL(8,2)  NOT NULL,
    PRIMARY KEY (id)
);

-- ============================================================
-- TABLE: vehicles
-- Referenced by: VehicleDAO.java, WorkOrderDAO.java
-- ============================================================
CREATE TABLE vehicles (
    id              INT          NOT NULL AUTO_INCREMENT,
    make            VARCHAR(50)  NOT NULL,
    model           VARCHAR(50)  NOT NULL,
    year            INT          NOT NULL,
    license_plate   VARCHAR(20)  NOT NULL UNIQUE,
    customer_id     INT          NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_vehicle_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- ============================================================
-- TABLE: services
-- Referenced by: Service.java (catalogue)
-- ============================================================
CREATE TABLE services (
    id                 INT           NOT NULL AUTO_INCREMENT,
    name               VARCHAR(100)  NOT NULL,
    price              DECIMAL(8,2)  NOT NULL,
    duration_minutes   INT           NOT NULL,
    PRIMARY KEY (id)
);

-- ============================================================
-- TABLE: work_orders
-- Referenced by: WorkOrderDAO.java
-- ============================================================
CREATE TABLE work_orders (
    id           INT            NOT NULL AUTO_INCREMENT,
    vehicle_id   INT            NOT NULL,
    mechanic_id  INT            NOT NULL,
    status       VARCHAR(20)    NOT NULL DEFAULT 'pending',
    total_cost   DECIMAL(10,2)  NOT NULL DEFAULT 0.00,
    PRIMARY KEY (id),
    CONSTRAINT fk_workorder_vehicle
        FOREIGN KEY (vehicle_id)
        REFERENCES vehicles (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_workorder_mechanic
        FOREIGN KEY (mechanic_id)
        REFERENCES mechanics (id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- ============================================================
-- SAMPLE DATA — customers
-- ============================================================
INSERT INTO customers (name, phone, email, address) VALUES
    ('Alice Johnson',  '403-555-0101', 'alice@email.com',   '12 Maple Ave, Calgary, AB'),
    ('Bob Smith',      '403-555-0202', 'bob@email.com',     '34 Oak Street, Calgary, AB'),
    ('Carol White',    '403-555-0303', 'carol@email.com',   '56 Pine Blvd, Calgary, AB'),
    ('David Brown',    '403-555-0404', 'david@email.com',   '78 Elm Road, Calgary, AB'),
    ('Eva Martinez',   '403-555-0505', 'eva@email.com',     '90 Cedar Lane, Calgary, AB');

-- ============================================================
-- SAMPLE DATA — mechanics
-- ============================================================
INSERT INTO mechanics (name, phone, email, specialization, hourly_rate) VALUES
    ('James Carter',   '403-555-1001', 'james@shop.com',  'Engine Repair',      85.00),
    ('Linda Park',     '403-555-1002', 'linda@shop.com',  'Electrical Systems', 90.00),
    ('Mike Torres',    '403-555-1003', 'mike@shop.com',   'Brake & Suspension', 80.00),
    ('Sara Nguyen',    '403-555-1004', 'sara@shop.com',   'Transmission',       95.00);

-- ============================================================
-- SAMPLE DATA — vehicles
-- ============================================================
INSERT INTO vehicles (make, model, year, license_plate, customer_id) VALUES
    ('Toyota',     'Camry',     2019, 'ABC-1234', 1),
    ('Honda',      'Civic',     2021, 'DEF-5678', 1),
    ('Ford',       'F-150',     2018, 'GHI-9012', 2),
    ('Chevrolet',  'Malibu',    2020, 'JKL-3456', 3),
    ('Nissan',     'Altima',    2017, 'MNO-7890', 4),
    ('Hyundai',    'Elantra',   2022, 'PQR-1122', 5);

-- ============================================================
-- SAMPLE DATA — services
-- ============================================================
INSERT INTO services (name, price, duration_minutes) VALUES
    ('Oil Change',            49.99,  30),
    ('Brake Inspection',      79.99,  60),
    ('Tire Rotation',         29.99,  30),
    ('Engine Diagnostic',    119.99,  90),
    ('Transmission Service', 199.99, 120),
    ('Battery Replacement',   89.99,  45),
    ('AC Recharge',           99.99,  60);

-- ============================================================
-- SAMPLE DATA — work_orders
-- ============================================================
INSERT INTO work_orders (vehicle_id, mechanic_id, status, total_cost) VALUES
    (1, 1, 'completed', 49.99),
    (1, 3, 'completed', 79.99),
    (2, 2, 'pending',    0.00),
    (3, 1, 'completed', 119.99),
    (4, 4, 'pending',    0.00),
    (5, 3, 'completed', 199.99),
    (6, 2, 'pending',    0.00);

-- ============================================================
-- VERIFICATION QUERIES
-- ============================================================

-- Show all customers
SELECT * FROM customers;

-- Show all mechanics
SELECT * FROM mechanics;

-- Show all vehicles with owner name
SELECT
    v.id,
    v.year,
    v.make,
    v.model,
    v.license_plate,
    c.name AS owner
FROM vehicles v
JOIN customers c ON v.customer_id = c.id;

-- Show all work orders with vehicle and mechanic details
SELECT
    wo.id          AS order_id,
    CONCAT(v.year, ' ', v.make, ' ', v.model) AS vehicle,
    v.license_plate,
    m.name         AS mechanic,
    m.specialization,
    wo.status,
    wo.total_cost
FROM work_orders wo
JOIN vehicles  v ON wo.vehicle_id  = v.id
JOIN mechanics m ON wo.mechanic_id = m.id
ORDER BY wo.id;

-- Show pending work orders
SELECT
    wo.id,
    c.name          AS customer,
    CONCAT(v.year, ' ', v.make, ' ', v.model) AS vehicle,
    m.name          AS mechanic,
    wo.status
FROM work_orders wo
JOIN vehicles  v ON wo.vehicle_id  = v.id
JOIN customers c ON v.customer_id  = c.id
JOIN mechanics m ON wo.mechanic_id = m.id
WHERE wo.status = 'pending';

-- Show completed revenue by mechanic
SELECT
    m.name              AS mechanic,
    m.specialization,
    COUNT(wo.id)        AS completed_orders,
    SUM(wo.total_cost)  AS total_revenue
FROM work_orders wo
JOIN mechanics m ON wo.mechanic_id = m.id
WHERE wo.status = 'completed'
GROUP BY m.id, m.name, m.specialization
ORDER BY total_revenue DESC;
