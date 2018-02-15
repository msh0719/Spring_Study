# Spring-MVC

Spring MVC 구조
- Controller : Dispathcer에서 전달된 요청을 처리
- servlet-context.xml : 스프링 컨테이너 설정 파일
- DispatcherServlet : 클라이언트의 요청을 최초 받아 컨트롤러에게 전달
- web.xml : DispatcherServlet 서블릿 맵핑 / 스프링 설정 파일 위치 정의
- view

![MVC](/Users/myeong/Desktop/Myeong_Su/Spring-Study/screen-shot/mvc.png)


Controller
- 최초 클라이언트로부터 요청이 들어왔을 때, 컨트롤러로 진입하게 된다.
- 그리고 컨트롤러는 요청에 대한 작읍을 한 후 뷰쪽으로 데이터를 전달한다.
