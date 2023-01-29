# PORTFOLIO

## 📗 목차


- [개요](#-포트폴리오-개요)
- [설계](#-설계/개발)
- [기능 구현](#-기능-구현)

## **포트폴리오 개요**

> **프로젝트:** 리뷰 체험단 플랫폼[모티브 - 퍼그샵]
>
> **분류:** 팀 프로젝트[5명]
>
> **제작 기간:** 2022.11 ~ 2022.12
>
> **개발 목표** 
> 웹 쇼핑몰 에서 물건을 구매할 때 리뷰 인증 등 미션을 통해 포인트를 받아 환금이 가능하여 저렴하게 구매 가능한 **웹 사이트**
판매자는 사이트 측에 홍보 금액을 지불하여 물품의 판매율 및 홍보가 가능
구매자는 사이트 측에서 미션을 수행하여 저렴한 금액에 구매 가능
>
> **담당 기능:** 상담 챗봇, 실시간 채팅 및 상담, 상담방 관리
>
> **사용 기술 및 도구** 
> 
> 기반  : ![SpringBoot](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=SpringBoot&logoColor=white) ![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=flat-square&logo=MariaDB&logoColor=white) ![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=GitHub&logoColor=white)
> 
> 프론트 : ![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=white) ![JQuery](https://img.shields.io/badge/JQuery-0769AD?style=flat-square&logo=JQuery&logoColor=white) ![Ajax](https://img.shields.io/badge/Ajax-0063CB?style=flat-square&logo=Ajax&logoColor=white) ![WebSocket](https://img.shields.io/badge/WebSocket-FF6A00?style=flat-square&logo=WebSocket&logoColor=white)
>
> 백     : ![Java](https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white) ![JSON](https://img.shields.io/badge/JSON-000000?style=flat-square&logo=JSON&logoColor=white)
>
>추가 API : spring security oauth2, CLOVA chatbot (Naver)
## **설계/개발**
- #### 기능 구조
<img src=".img/diagram.JPG">
* Non DB

## **기능 구현**

#### 화면 구현

<img src=".img/main1.JPG">

- 스크롤 상관없이 우측하단에 버튼 유지


#### 챗봇 상담

<img src=".img/chatbot1.JPG">

- 대형 카테고리 6가지, 소형 카테고리 18개, 웰컴 메세지, 종료 메세지 
- 총 시나리오 26개 (엔티티 X)


#### 상담사와 실시간 상담

<img src=".img/chat1.JPG">

- 해당 페이지에서 바로 채팅 시작
- 상담사와 내담자 메세지 구분

#### 관리자 페이지 상담 목록

<img src=".img/adminchat1.JPG">

- 관리자 페이지에서 상담방 확인가능
- 상담방 역시 메세지 구분


## **고려 사항**
* spring security를 이용하는 경우 소켓 접속방식 고려
(관리자 페이지 역시 시큐리티 권한을 통해 접속가능)
* 챗봇의 데이터 전달 방식
(해당 프로젝트의 CLOVA 챗봇은 JSON형태의 데이터로 전송)