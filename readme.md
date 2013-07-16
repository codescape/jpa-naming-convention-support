# JPA Naming Conventions Support [![Build Status](https://travis-ci.org/stefanscheidt/jpa-naming-convention-support.png)](https://travis-ci.org/stefanscheidt/jpa-naming-convention-support)

This project aims to provide support for a custom naming convention for database artifacts. The following naming conventions apply:

* tables have a four letter alias name, eg. `item`
* fields are prefixed with the alias name, eg. `item_id`
* sequences are prefixed with the alias name and reference the id field, eg. `item_id_seq`
* foreign keys are prefixed with the alias name and include the referenced table alias, eg. `item_ordr_id`

### Requirements

* Java 1.7
* Maven 3.0.5

### Build

* Build with unit tests: `mvn clean verify`
* Build with unit and integration tests: `mvn clean verify -Pintegration`. This starts and stop H2 database using port 9092.
* Start database for development: `./start-db.sh` or `start-db.bat`.
