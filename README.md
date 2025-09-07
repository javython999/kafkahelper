# Kafka Helper

## [Kafka-Helper Demo](https://kafka-helper.kro.kr/kafka/brokers)

## 목적

Spring-Kafka 기반의 **Kafka 브로커 관리 웹 애플리케이션**입니다.  
토픽 생성/수정, 메시지 등록 기능을 웹 UI로 제공하여 운영/개발 환경에서 더 직관적이고 빠르게 Kafka를 다룰 수 있습니다.

## 구조
![Image](https://github.com/user-attachments/assets/1057bce6-643a-44f8-86fd-00467845f8e0)
<img width="882" height="720" alt="Image" src="" />
## ✨ 주요 기능
- **토픽 관리**
    - 생성 / 수정 / 삭제
    - 메시지 확인
    ![Image](https://github.com/user-attachments/assets/aba6fb37-dd4e-47de-a4ad-b35f07ba34f7)
- **메시지 등록**
    - 특정 토픽에 메시지 등록
- **브로커 연결 관리**
    - 브로커 주소 및 접속 정보 설정
- **조회**
    - 현재 브로커의 토픽 목록 조회
    - 토픽 상세 정보 및 설정 확인

## 🛠 기술 스택
- **Backend**
    - Java 21
    - Spring Boot 3.x
    - Spring Kafka
- **Frontend**
    - Thymeleaf
    - HTML/CSS/JavaScript
- **Kafka**
    - Confluent kafka 8.0.0
- **Build**
    - Gradle

