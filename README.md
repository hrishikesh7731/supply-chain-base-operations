# Supply Chain Base Operations

A common, reusable library shared by the [Sourcing and Procurement Operations (SPO)](https://github.com/hrishikesh7731/sourcing-and-procurement-operations) and [Supplier Lifecycle Operations (SLO)](https://github.com/hrishikesh7731/supply-lifecycle-operations) backends. It keeps the shared domain models, repositories, notification service and system utilities in one place so both applications reuse the same code.

## Tech Stack

Java 17, Spring Boot, Spring Data JPA, Maven

## What's Inside

| Area | Description |
|---|---|
| Domain models and repositories | Shared entities and data-access repositories used by both applications |
| Enums and constants | Shared status, type and category enums, plus common configuration and constants |
| Company, employee, role and user management | Reusable services, including user and administrator creation logic |
| Notification service | Extensible, multi-channel notification service with a common handler interface |
| Utilities and transformers | Common system utilities and data transformers |

## Used By

- [Sourcing and Procurement Operations (SPO)](https://github.com/hrishikesh7731/sourcing-and-procurement-operations)
- [Supplier Lifecycle Operations (SLO)](https://github.com/hrishikesh7731/supply-lifecycle-operations)

## Build

```bash
./mvnw clean install
```

## Author

Gaddamedi Hrishikesh | [LinkedIn](https://www.linkedin.com/in/hrishikesh-gaddamedi-6b0a513b0/) | [GitHub](https://github.com/hrishikesh7731)
