# ERD
![image](https://user-images.githubusercontent.com/117061586/208061157-02f8f714-3bf1-4490-bf00-826cc7b611c5.png)
# API
![image](https://user-images.githubusercontent.com/117061586/208063528-1b6ca561-a5c8-4060-9a57-555aa0a5e5f2.png)

username = @Size(min = 4, max = 10), @Pattern(regexp = "^[0-9a-z]*$") 

password =  @Size(min = 8, max = 15),@Pattern(regexp = "^[0-9a-zA-z]*$")

#숙련 1주차 질문 답변
1. 처음 설계한 API 명세서에 변경사항이 있었나요? 
변경 되었다면 어떤 점 때문 일까요? 첫 설계의 중요성에 대해 작성해 주세요!
 -새로 만든 계정 생성과 로그인 때문에 API를 추가해야 했다. 첫 설계때 각종 기능을 고려해서 설계해야 할 것 같다.
2. ERD를 먼저 설계한 후 Entity를 개발했을 때 어떤 점이 도움이 되셨나요?
 - ERD를 먼저 설계하지 않고 Entity를 개발했는데 참조해야 될 key에 대해서 헷갈렸던 것 같다.
3. JWT를 사용하여 인증/인가를 구현 했을 때의 장점은 무엇일까요?
 - 기존의 쿠키방식에 비해 보안성이 높아졌다. 서버가 적고 동시 접속자 수가 많을때 값을 효율적으로 처리 할 수있 다.
4. 반대로 JWT를 사용한 인증/인가의 한계점은 무엇일까요?
 - secret key가 유출 될 경우 보안의 안정성을 보장할 수 없다. 
5. 만약 댓글 기능이 있는 블로그에서 댓글이 달려있는 게시글을 삭제하려고 한다면 무슨 문제가 발생할까요? Database 테이블 관점에서 해결방법이 무엇일까요?
 - 
8. IoC / DI 에 대해 간략하게 설명해 주세요!
