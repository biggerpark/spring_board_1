package com.green.board;


    /*


   1.Controller 의 역할: 요청(request)을 받고 응답(Response)처리하는 객체
                      로직처리는 하지 않는다. 로직담당은 Service 이다.

      Annotation(애노테이션)
      @Controller - 응답을 html 으로 한다 (데이터로 만든 화면을 리턴)
      @RestController - 응답을 json 으로 한다(데이터만 응답)

      @RequestMapping - URL 과 클래스 아래에 있는 Method 맵핑(연결)
                        class 에 RequestMapping 전체 메소드 주소가 맵핑

      @PostMapping - URL+Post 방식으로 FE에서 요청이 왔을 시 담당하겠다.

   2.우리가 브라우저를 통해 요청을 보낼때 URL과 method를 함께 요청을 보낸다.
     ex)브라우저의 주소창에 주소값을 적고 엔터를 치면  [URL+GET 방식+데이터 보내는 방식(key,value)]으로 요청을 보낸다.

    2.1 데이터를 보낼때, 보여지나 안보여지나 차이가 있다. 이런 데이터를 보내는 방식에는 두가지가 있다.
     1)쿼리스트링 방식(파라미터라고 부르기도 함)- header 에 데이터를 담아서 보내는 방식(url 다음에 오는 쿼리스트링에 데이터를 포함하는 방식), body를 안쓴다고 봐도 된다.
      url은 길이제한이 있기 때문에 url에 데이터를 포함하는 쿼리스트링은 대용량을 보낼 수 없다. 그래서 대용량을 보내고 싶을때는 body로 보낸다.
     2)body 에 담아서 보내는 방식(FormData,JSON)
      여기서 body 란 요청과 응답은 header,body 로 이루어져 있음
      header 에는 목적지(url)
      body 에는 값,데이터 담겨져있음

      ❗❗쿼리스트링 모양: url+쿼리스트링(쿼리스트링은 ?로 시작, key=value,여러개라면 &로 구분)❗❗
                       ex)www.naver.com?name=홍길동&age=12&height=172,1

       JSON(JavaScript Object Notation): 자바스크립트에서 객체를 만들때 사용하는 문법을 이용하여, 데이터를 표현하는 포맷(형식), key와 value로 이루어져 있음
         ex) name은 홍길동, age는 22살, height는 178.2 데이터를 json으로 표현을 하면
         {
               "name":"홍길동"
               "age":22,
               "height":178.2
         }
          이렇게 표현하는 문자열이다.
          {}은 객체를 의미하고, []은 배열을 의미한다. ""은 문자열, 숫자형은 ""없이 표현한다. key는 무조건 "" 감싸줘야 한다.




    3.Restful 이전에는 get,post 방식 밖에 없었음.
      get 방식은 주로 쿼리스트링 방식을 사용하고(데이터를 읽어올때)
      post 방식은 body 에 데이터를 담아서 보내는 방식을 사용했었다.(데이터를 저장/수정/삭제할때)
      데이터가 있었을때, get 방식이 처리속도가 빠르다, 데이터 처리가 아닌 단순화면을 띄울때도 get 방식을 사용함


      ex) 로그인을 하는 상황에서 로그인을 하는 화면이 띄워져야 한다.
          작업(1): 로그인 하는 화면은  get 방식으로 url은 /login 을 요청하면 로그인 하는 화면이 화면에 나타났다.
          (get)/login 이렇게 표현한다.
          작업(2):  아이디/비번을 작성하고 로그인 버튼을 누르면 (post)/login 이렇게 요청을 보냈다.
          , 즉 아이디/비번은 body 에 담아서 요청을 보냈다.
          url은 같은데 method(get,post) 로 작업을 구분을 했다.(마치 if문 처럼)
          위 작업은 2가지 밖에 없었기 때문에 같은 주소값으로 method를 구분할 수 있었다.

       그런데 CRUD(작업이 4가지)를 해야되는 상황에서는 작업 구분을 주소값으로 해야했었다.

   (get)/board -게시판 리스트 보기 화면 띄우기
   (get)/board_detail-게시판 글 하나 보기 화면 띄우기
   (post)/board_create-게시판 글 등록하는 작업 처리
   (get)/board_modify-게시판 글 수정하는 화면 띄우기
   (post)/board_modify-게시판 글 수정하는 작업 처리
   (get/post) /board_delete -게시판 글 삭제한는 작업 처리

   즉 Restful 이전에는 위에 6개 처럼 주소값이 지저분해진다.
   Restful 은 주소값은 하나이고, 메소드로 작업을 구분해주는것.


    4. URL 과 METHOD 를 함께 요청을 보낸다.
    [1]Restful 이해가 필요하다.
    -Restful 방식은 화면은 없고 작업만 신경쓰면 된다.
    -첫 페이지(index 화면)를 띄울 때, 화면을 띄울 수 있는 소프트웨어(프론트엔드(FE) 작업 코드)가 모두 다운로드 됨.
    -화면 이동은 모두 FE코드가 작동하는것. 화면 만들기는 Client 리소스를 사용하여 그린다.(Rendering)
    -Client 리소스: Client, 즉 요청을 보낸 컴퓨터 자원을 사용한다(cpu,ram 등등)
    -화면마다 데이터가 필요하면 백엔드(BE)에 요청을 한다. 누가? FE 작업코드가 요청을 보낸다. 그래서 BE는 화면은 신경쓰지 않아도 된다.
    -FE 코드가 요청한 작업에 응답만 잘해주면 된다.


     1)요청의 메소드는 크게 4가지로 나뉘어진다.
      1.POST 방식: CREATE(DDL)-INSERT(DML) 작업
      2.GET 방식 :SELECT(DML) 작업
      3.PUT/PATCH 방식: UPDATE(DML) 작업
      4.DELETE 방식

      POST,PUT/PATCH 방식은 주로 데이터를 body 에 담아서 보내고
      GET,DELETE 방식은 Query Stirng or Path Variable 을 사용해서 데이터를 보낸다
      FE가 BE한테 (URL+METHOD+데이터) 이렇게 요청을 하고, BE는 JSON으로 응답(RESPONSE)한다.


      (post)/board- 글 등록
      (get)/board?page=1 - 리스트 데이터(투플이 여러개)(row가 여러개)
      (get) /board/ - 끝에 / 만 붙어도 위 url과 다른 요청이 된다(tip)
      (get) /board?aaa=2 - board?page=1 과 같은 요청이다. url이 같으면 같은 요청이다
      (get)/board/1- 투플 1개 데이터(row가 1줄),1은 pk이다, 이런 pk를 Path Variable 이라 한다.
      (put/patch) /board - 글 수정
      (delete/board - 글 삭제(Path Variable/Query String 으로 pk값 전달)


     */

import com.green.board.model.BoardInsReq;
import com.green.board.model.BoardSelOneRes;
import com.green.board.model.BoardSelRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor // 이 에노테이션 붙이면 필수로 무조건 있어야 하는 생성자를 만들어준다. 즉, final 붙은 멤버필드 DI(=객체화된 주소값) 받을 수 있게 생성자를 만든다.
// 위 에노테이션 생략하면 오버로딩된 생성자를 직접 만들어 주면 된다.
@RestController// 이 친구는 controller 라는 걸 알 수 있도록 달아준다, 응답을 JSON 으로 하겠다., 빈(bean)등록+컨트롤러 임명-빈등록을 했다는건 스프링 컨테이너가 직접 객체화를 하겠다 의미
@RequestMapping("/board") // 이렇게 해주면 아래에서 나올 모든 메소드에서 URL 을 이 board 로 통일해주겠다 라는 의미,
// 이 주소값을 통해 들어오는 프론트엔드의 데이터를 받으면 그에 맞게 처리하는것.
// 만약 RequestMapping을 안써주엇으면 밑에서 insBoard 적기 전에 @PostMapping("/board") 이렇게 또 써줘야한다.
public class BoardController {
    private final BoardService service; // private final 을 붙여서 객체생성을 한번만 한다.
    private final BoardService boardService;


    //   @RequiredArgsConstructor 에노테이션을 붙이면 아래 생성자가 자동으로 만들어진다.
//    public BoardController(BoardService boardService) {
//        this.boardService = boardService;
//    }

    //insert(Create)
    @PostMapping// FE에서 (post)/board 요청이 오면 이 메소드가 응답을 담당하겠다 라는 것을 의미.
    //@RequestBody 는 요청이 올때 데이터가 json 형태로 오니까 거기에 맞춰서 데이터를 받으라는 의미
    public int insBoard(@RequestBody BoardInsReq p){ // @RequestBody 는 옆에 나오는 매개변수 타입의 body 데이터를 프론트엔드에서 받겟다라는 의미
//        System.out.println(p);
        return boardService.insBoard(p);
    }


    //객체 > JSON 바꾸는 직렬화 작업 자동으로 해준다.
    @GetMapping
    public List<BoardSelRes> selBoardList(){
        return service.selBoardList();
    }

    @GetMapping("{boardId}") // Path variable을 사용하겠다는 의미
    public BoardSelOneRes selBoardOne(@PathVariable int boardId){
        return service.selOneBoard(boardId);
    }


}
