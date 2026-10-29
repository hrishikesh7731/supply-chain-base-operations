# Supply Chain Base Operations

A common, reusable library shared by the [Sourcing and Procurement Operations (SPO)](https://github.com/hrishikesh7731/sourcing-and-procurement-operations) and [Supplier Lifecycle Operations (SLO)](https://github.com/hrishikesh7731/supply-lifecycle-operations) backends. It keeps the shared domain models, repositories, notification service and system utilities in one place so both applications reuse the same code.

## Tech Stack

Java 17, Spring Boot, Spring Data JPA, Maven

## What's Inside

| Package | Contents |
|---|---|
| `models` | Shared entities such as Company, ProcurementCompany, SupplierCompany, Employee, User, Role, Product, ProductCategory, PurchaseRequest, SourcingRequest, Bid, Attachment, Activity and Notification |
| `repositories` | Repositories for companies, procurement and supplier companies, employees, roles and operations |
| `enums` | Shared status, type and category enums (company, request, bid, product, notification, user and more) |
| `services` | Company, employee, role and operation services, including user and administrator creation logic |
| `services` (notification) | Notification service with email, SMS and WhatsApp handlers behind a common `NotificationHandler` interface, plus notification templates, priorities and statuses |
| `configurations`, `constants` | Shared system configuration and constants |
| `transformers`, `utilities` | System transformer and utility classes |

## Used By

- [Sourcing and Procurement Operations (SPO)](https://github.com/hrishikesh7731/sourcing-and-procurement-operations)
- [Supplier Lifecycle Operations (SLO)](https://github.com/hrishikesh7731/supply-lifecycle-operations)

## Build

```bash
./mvnw clean install
```

## Author

Gaddamedi Hrishikesh | [LinkedIn](https://www.linkedin.com/in/hrishikesh-gaddamedi-6b0a513b0/) | [GitHub](https://github.com/hrishikesh7731)
