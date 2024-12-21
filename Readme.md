# 의식주컴퍼니 과제

## 단축 URL 제공 서비스

#### 사용 기술
- Java, Spring Boot, Gradle, H2, lombok : 기본 셋팅
- Spring Data JPA
- Mockito
- Swagger

#### 결과 확인
- Spring Boot Start 후 swagger에서 확인할 수 있습니다.
- http://localhost:8080/swagger-ui/index.html

### 스키마

<img width="609" alt="image" src="https://github.com/user-attachments/assets/d6b73cf3-1557-4758-ac5b-67cecdba96d5" />

**lg_url 테이블**
- 원본 URL, 단축 URL, 단축 URL 요청 수를 기록합니다.
- 새로운 단축 URL 이 생성되면 단축 URL 요청 수는 0 입니다.
- `schema.sql` 에서 확인할 수 있습니다.

### 요구사항 구현

#### 1. URL(이하 oriUrl) 을 요청 받아 짧은 URL(이하 shortUrl) 을 응답한다.

<img width="1018" alt="image" src="https://github.com/user-attachments/assets/b5c394fb-019f-4164-941f-90194ee86b5e" />

```curl
curl --location --request POST 'http://localhost:8080/api/urls' \
--header 'Content-Type: text/plain' \
--data-raw 'www.laundrygo.com'
```
- shortUrl 은 8자 입니다.
- `.lg` 는 고정값 입니다.
- 동일한 oriUrl 을 요청 받으면 동일한 shortUrl 을 응답합니다.
    - `단축 URL 생성 성공 테스트: 중복 생성` 테스트 케이스 참조


#### 2. shortUrl 을 요청 받아 원래 oriUrl 을 응답한다.

<img width="1017" alt="image" src="https://github.com/user-attachments/assets/8c0b4ab5-13b3-44a2-89b6-a66bf5ce6566" />

```curl
curl --location --request GET 'http://localhost:8080/api/urls/2Zb8O.lg'
```
- shortUrl 요청 수가 기록됩니다.
- oriUrl 을 찾을 수 없으면 `404 Not Found` 가 응답됩니다.
  - `원본 URL 조회 실패 테스트` 테스트 케이스 참조
  <br> <br>
  <img width="1016" alt="image" src="https://github.com/user-attachments/assets/0479679d-6771-47d5-b26d-a0147f7c4855" />

#### 3. 등록된 oriUrl, shortUrl, 요청 수를 조회 할 수 있다.

<img width="1012" alt="image" src="https://github.com/user-attachments/assets/e10bee17-9fcf-44ad-bd2c-0c4b13462796" />

```curl
curl --location --request GET 'http://localhost:8080/api/urls'
```
- 등록된 oriUrl, shortUrl, 요청 수를 조회합니다.


---
## 요구사항

1. 기본 세팅
    - 본 repo.를 fork 하여 개인 repo.에 저장한 후에 아래 요구사항을 구현하여 커밋해주시면 됩니다.
    - Spring boot 실행 후 http://localhost:8080/members 호출시 2건의 회원 정보 Json 응답
    - DB 는 H2 구성
    - Mybatis or JPA or JOOQ 사용
    - 테이블 생성문은 schema.sql 에 작성
    - 사전 데이터는 data.sql 에 작성
2. 요구 사항
    1. URL(이하 oriUrl) 을 요청 받아 짧은 URL(이하 shortUrl) 을 응답한다.
    2. shortUrl 은 8 Character 이내로 생성한다.
    3. shortUrl 은 중복되지 않는다.
    4. 동일한 oriUrl 요청은 동일한 shortUrl 을 응답한다.
    5. shortUrl 을 요청 받아 원래 oriUrl 을 응답한다.
    6. shortUrl 요청 수를 기록한다.
    7. 등록된 oriUrl, shortUrl, 요청 수를 조회 할 수 있다.
3. 결과 평가
    1. 시간 관계상 요구사항을 모두 구현하는 것이 어려울 수도 있습니다. 하지만, 구현 과정을 중요하게 봅니다.
    2. postman, curl, browser, swagger 를 통해 확인 및 테스트 하셔도 됩니다.
    3. test code는 모든 케이스를 테스트할 필요없고, shortUrl 생성 및 원래 Url를 응답하는 테스트 코드는 작성해주십시오.
    4. 부가적인 요소, 즉 성능, 확장성 등은 고려하지 않아도 됩니다. 기본 동작에 충실하게 구현해주시면 됩니다.
