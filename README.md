# MASI [![Build Status](https://travis-ci.com/lodz-university-of-technology-masi/Green.svg?branch=master)](https://travis-ci.com/lodz-university-of-technology-masi/Green)

## Green team members
- Filip Pietrusiak,
- Jakub Cybulski,
- Łukasz Czech.

## Backend app

### Requirements
- Java 8 in PATH.
- Maven in PATH.
- Postgres installed with username ``postgres`` and password ``postgres``, it can be changed in ``MASI-Back\src\main\resources\application.properties`` file.
- Created database with name ``masi``.

### Run

To run our backend application please run command ``mvn spring-boot:run`` in ``MASI-Back`` directory.

### API Documentation
To see our API Documentation you should open [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) in web browser, when backend app is active.
