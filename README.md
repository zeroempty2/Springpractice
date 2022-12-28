스프링부트 2.7.6 사용
# ERD
![image](https://user-images.githubusercontent.com/117061586/209421252-88fec28d-a5ed-4d17-8375-e9f4feaa8334.png)

Post 엔티티에서 @ManyToOne해준 User 객체는 save할때 코드상으로는 User객체를 넣어주지만 데이터베이스 상으로는 User의 pk만 가지고 저장된다.
# API

스프링 부트 실행 후 http://localhost:8080/swagger-ui/index.html
![image](https://user-images.githubusercontent.com/117061586/208656309-c555a829-02aa-4de2-a513-31eb58512bf0.png)
# 질문 답변
1. 처음 설계한 API 명세서에 변경사항이 있었나요? 변경 되었다면 어떤 점 때문 일까요? 첫 설계의 중요성에 대해 작성해 주세요!
- 새로 만든 계정 생성과 로그인 때문에 API를 추가해야 했다. 첫 설계때 각종 기능을 고려해서 설계해야 할 것 같다.
2. ERD를 먼저 설계한 후 Entity를 개발했을 때 어떤 점이 도움이 되셨나요?
- ERD를 먼저 설계하지 않고 Entity를 개발했는데 참조해야 될 key에 대해서 헷갈렸던 것 같다.
3. JWT를 사용하여 인증/인가를 구현 했을 때의 장점은 무엇일까요?
- 기존의 쿠키방식에 비해 보안성이 높아졌다. 서버가 적고 동시 접속자 수가 많을때 값을 효율적으로 처리 할 수 있다.
4. 반대로 JWT를 사용한 인증/인가의 한계점은 무엇일까요?
- secret key가 유출 될 경우 보안의 안정성을 보장할 수 없다.
5. 만약 댓글 기능이 있는 블로그에서 댓글이 달려있는 게시글을 삭제하려고 한다면 무슨 문제가 발생할까요? Database 테이블 관점에서 해결방법이 무엇일까요?
- 게시글은 삭제가 되겠지만 댓글은 그대로 데이터베이스에 남아있을것이다. SQL쿼리로 보자면 두 테이블의 FK를 DROP시킨후 DELETE CASCADE하고 삭제해주어야 할 것이다.
6. IoC / DI 에 대해 간략하게 설명해 주세요!
- 의존성 주입(DI)은 객체간의 결합을 느슨하게 하는것이다. 예를 들어 Cup이라는 객체가 Water라는 객체를 사용할때 Water의 속성이 무엇인지 신경쓰지 않아도 되게 만드는 것이다.
- 의존관계 역전 원칙(은 상위 객체가 하위객체에 의지하지 않게 하는것이다(관계 역전). 위의 예시처럼 느슨한 결합을 하면 Cup이라는 상위 객체가 Water라는 하위 객체에 의존하지 않고 독립되게 된다.


!!exceptionHandle 리팩토링(DefaultRes이용해서 set안쓰고 출력, (정규님 코드 + https://velog.io/@kiiiyeon/%EC%8A%A4%ED%94%84%EB%A7%81-ExceptionHandler%EB%A5%BC-%ED%86%B5%ED%95%9C-%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC) 해서 리팩토링 해보기)
- 굳이 바꿀 필요없음. Exception마다 메세지를 설정해주고 싶었으면 설정하고 싶은 익셉션만 수정해주었으면 되나, 같은 illegalexception에 다른 메세지를 던져주고싶은 경우에는 각각의 클래스를 생성해야함.
