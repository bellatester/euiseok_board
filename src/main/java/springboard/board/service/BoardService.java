package springboard.board.service;

import org.springframework.stereotype.Service;

import springboard.board.domain.entity.Board;
import springboard.board.domain.repository.BoardRepository;
import springboard.board.dto.BoardDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    @Transactional
    public Long savePost(BoardDto boardDto){
        return boardRepository.save(boardDto.toEntity()).getId();
    }
    //게시물을 DB에 저장하는 함수

    @Transactional
    public List<BoardDto> getBoardList(){ //전체 게시판 목록을 List로 가져오는 함수
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for(Board board : boardList){
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build(); //modifiedDate는 자동으로 적용된다. JPA Auditing 기능을 활용해서
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional
    public BoardDto getPost(Long id){ //해당 id에 맞는 BoardDto를 가져오는 함수이다.
        Board board = boardRepository.findById(id).get();
        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createdDate(board.getCreatedDate())
                .build();
        return boardDto;
    }

    @Transactional
    public void deletePost(Long id){
        boardRepository.deleteById(id);
    }
}
