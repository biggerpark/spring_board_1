package com.green.board;

import com.green.board.model.BoardInsReq;
import com.green.board.model.BoardSelOneRes;
import com.green.board.model.BoardSelRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/*
    @Service - 빈등록,서비스가 로직처리 담당. 로직처리가 있다면 여기서 처리한다. 없으면 연결 작업만. 연결작업이 Controller 와 Persistence(DB) 연결

    빈 등록: 스프링 컨테이너에게 객체 생성을 대리로 맡기는 것, 기본적으로 싱글톤(생성자에 private 을 붙여 객체화가 딱 1개만 가능하게 함)으로 객체화
*/
@RequiredArgsConstructor // 이 에노테이션 붙이면 필수로 무조건 있어야 하는 생성자를 만들어준다. 즉, final 붙은 멤버필드 DI(=객체화된 주소값) 받을 수 있게 생성자를 만든다
@Service
public class BoardService {
    private final BoardMapper mapper; // DI 받을 수 있게 세팅해 주세요.

    public int insBoard(BoardInsReq p){
        return mapper.insBoard(p);
    }

//    public BoardService(BoardMapper mapper){
//        this.mapper=mapper;
//    }

    public List<BoardSelRes> selBoardList(){
        return mapper.selBoardList();
    }

    public BoardSelOneRes selOneBoard(int p){
        return mapper.selBoardOne(p);
    }






}
