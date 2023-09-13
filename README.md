# auction-status-updater

## Overview

Spring Cloud 프로젝트입니다. 이 프로젝트는 다음과 같은 주요 기능 및 라이브러리를 활용하고 있습니다

- Eureka Client for service discovery
- Spring for Kafka
- Spring Cloud Config for centralized configuration

## Requirements

- Java 17
- Spring Cloud

## API (@Scheduler)

### `updateAuctionStatusToEnd` (자정 실행)

Feign 통신으로 [product-service](https://github.com/wooriFisa-Final-Project-F4/product-service)의 `http://PRODUCT-SERVICE/api/product/v1/update-to-end`를 호출하여 경매의 상태가 "END"로 변경된 상품을 Dto로 반환받습니다.
<br>  
변경된 상품에 대해 낙찰자를 Feign통신으로 [user-servce](https://github.com/wooriFisa-Final-Project-F4/user-service)의 `http://USER-SERVICE//api/user/v1/feign/{userId}`를 호출하여 확인합니다. 유저의 유효성 검증까지 마치면, 낙찰 알림 이메일 이벤트를 발행합니다. 발행된 이벤트는 [payment-servce](https://github.com/wooriFisa-Final-Project-F4/payment-service)와 [email-servce](https://github.com/wooriFisa-Final-Project-F4/email-service)가 consume하여 추가 서비스를 수행합니다.

### `updateAuctionStatusToProgress` (오후 2시 실행)

Feign 통신으로 `http://PRODUCT-SERVICE/api/product/v1/update-to-progress`를 호출하여 경매 대기중인 상품을 "PROGRESS"로 상태를 변경하는 [product-service](https://github.com/wooriFisa-Final-Project-F4/product-service)의 API를 실행시킵니다.

## Stack

<p align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" alt="spring" width="40" height="40"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" alt="java" width="40" height="40"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/apachekafka/apachekafka-original.svg" width="40" height="40"/>
</p>

## Mechanism

### Project Architecture

<img width="1618" alt="Architect (2) 복사본" src="https://github.com/wooriFisa-Final-Project-F4/.github/assets/109801772/27ac2b1d-8624-424f-aefb-4ceda4484b63">
