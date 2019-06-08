# MASI [![Build Status](https://travis-ci.com/lodz-university-of-technology-masi/Green.svg?branch=master)](https://travis-ci.com/lodz-university-of-technology-masi/Green) [![BCH compliance](https://bettercodehub.com/edge/badge/lodz-university-of-technology-masi/Green?branch=master)](https://bettercodehub.com/)

## Green team members
- Filip Pietrusiak,
- Jakub Cybulski,
- Łukasz Czech.

## Backend app

### Requirements
- Java 8 in PATH.
- Maven in PATH.
- Postgres installed with username ``green`` and password ``dbpass``, it can be changed in ``MASI-Back\src\main\resources\application.properties`` file.
- Created database with name ``green``.

### Run

To run our backend application please run command ``mvn spring-boot:run`` in ``MASI-Back`` directory.
You can also use others standard maven command like
- ``mvn clean``,
- ``mvn compile``,
- ``mvn test``,
- ``mvn verify``.

### API Documentation
To see our API Documentation you should open [http://localhost:6063/swagger-ui.html](http://localhost:6063/swagger-ui.html) in web browser, when backend app is active.
