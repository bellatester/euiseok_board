package springboard.board.service;

import org.springframework.stereotype.Service;

import springboard.board.domain.entity.Board;
import springboard.board.domain.repository.BoardRepository;
import springboard.board.dto.BoardDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public List<BoardDto> getBoardList(){
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
}
