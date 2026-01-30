## Overview

This application is a backend HTTP service that simulates an order and inventory workflow under concurrent load.  
It is designed as a learning project to explore transactional behavior in Java-based systems.

The system allows users to place and cancel orders for products with limited stock, while ensuring data consistency in
the presence of failures and concurrent requests.

---

## Core Features

- Create orders containing multiple items
- Reserve and restore product inventory
- Cancel orders and compensate inventory
- Simulate payment success and failure
- Handle concurrent order requests against limited stock

---

## Transactional Focus

This project intentionally explores:

- Transaction boundaries and atomicity
- Rollback behavior on failures
- Transaction propagation (`REQUIRED`, `REQUIRES_NEW`)
- Isolation levels and concurrency anomalies
- Optimistic vs pessimistic locking
- Partial failures and compensating actions
- Idempotent request handling

---

## Failure & Concurrency Scenarios

The application includes endpoints and configurations to simulate:

- Inventory shortages
- Mid-transaction failures
- Slow transactions to provoke race conditions
- Concurrent requests competing for the same stock
- Retry scenarios after partial success

These scenarios are meant to be observed through logs, database state, and test results.

---

## Technology Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- Relational database (H2 as a temporary database)
- JUnit for integration testing

---

## Intended Usage

This project is not production-ready software.  
It is intended as a sandbox for experimenting with transaction management, database behavior, and consistency trade-offs
in real-world backend systems.

Feel free to modify transaction settings, introduce failures, and observe how the system behaves under different
conditions.
