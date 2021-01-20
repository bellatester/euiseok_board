package springboard.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springboard.board.dto.BoardDto;
import springboard.board.service.BoardService;

import java.util.List;

//service에서 만들어서 Controller에서 사용한다.
@Controller
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String list(Model model){
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList); // postList는 html에 써있는 이름
        return "board/list.html";
    }

    @GetMapping("/post")
    public String post(){
        return "board/post.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }
}
