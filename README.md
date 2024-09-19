## Summary
> - 애플리케이션 이름: (미정)
> - 개발 기간: 2024-09-05 ~ 2024-09-19 (총 8일)
> - 개발 환경: IntelliJ IDEA
> - API 테스트 도구: Postman
> - 빌드 도구: Gradle
> - 협업 도구: GitHub
> - JAVA 버전: 17

<br/>

## 🛠️ Backend Tech Stack
> <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"> <img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white">
> <img src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=Java&logoColor=white"> <img src="https://img.shields.io/badge/JSON Web Tokens-000000?style=for-the-badge&logo=JSON Web Tokens&logoColor=white">
> <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">
> <img src="https://img.shields.io/badge/postgresql-4169e1?style=for-the-badge&logo=postgresql&logoColor=white">
> <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white">
> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

<br/>

## Team Members and Roles
### - 강태원
> - 허브, 허브 관리자
> - 배송 담당자
> - 허브 이동 경로

### - 박영무
> - 업체, 업체 관리자
> - 상품
> - Config 서버

### - 강태원
> - 유저
> - 주문
> - 배송
> - Slack

<br/>

## Infrastructure Architecture
>![](https://velog.velcdn.com/images/dmitry__777/post/e09e180f-155b-46b7-8b61-76ff9d81c97b/image.png)

<br/>

## API Document
### - Swagger UI
> - API 문서 생성 자동화 툴
> - 웹 애플리케이션 실행 후 end-point, 요청 파라미터 등에 대한 정보를 확인
> - End-point: /swagger-ui/index.html


<br/>

## DBMS
### - H2
> - 본 프로젝트에서는 배포하지 않기 때문에 개발 단계에서 In-Memory DB를 사용
> - PostgreSQL을 사용

### - ER Diagram
> ![](https://velog.velcdn.com/images/dmitry__777/post/48b905e4-7934-41c6-8853-1742af0e1df0/image.png)


<br/>

## Authentication and Authorization
### - JWT
> - JWT 토큰을 발행함으로써 인증 처리

### - Role-based Routing
> - Gateway에서 요청 API Path에 있는 role 값을 기반으로 라우팅
> - 비즈니스 로직에서 추가적인 인가 처리를 하지 않도록 설계

<br/>

## Trouble Shooting
![](https://velog.velcdn.com/images/dmitry__777/post/665c9278-a7d3-4542-86b8-bed9ef3d64ca/image.png)
![](https://velog.velcdn.com/images/dmitry__777/post/71fc9d8e-7993-4098-837e-a62ff4b114cf/image.png)
![](https://velog.velcdn.com/images/dmitry__777/post/1099be0d-f0b9-49cf-a55b-7a7cb6d1005f/image.png)


