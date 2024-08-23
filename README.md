# SQL ERD

scheduleUser 테이블의 ERD

![image](/images/SQL%20erd.PNG)


필수 구현:
 1. 일정 작성 (완)
 2. 선택한 일정 조회 (완)
 3. 일정 목록 조회 (완)
 4. 선택한 일정 수정 (완)
 5. 선택한 일정 삭제 (완)


[APi 명세서 가기](https://documenter.getpostman.com/view/37561614/2sA3s4nB2S)


DB : MySQL 사용
개발 : Java Spring Boot 사용
구현 : Restful APi 구현하려 했으나 경로를 보면 아직 Restful하지 않다
POST /api/add -- 이런 식으로 되어있음 
POST /api/schedule 이런 식으로 해두면 좋을 듯
