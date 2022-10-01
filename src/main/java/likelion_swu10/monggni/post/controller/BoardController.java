package likelion_swu10.monggni.post.controller;

import likelion_swu10.monggni.post.dto.BoardDto;
import likelion_swu10.monggni.post.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    private BoardController(BoardService boardService){

        this.boardService=boardService;
    }

//    @GetMapping("/")
//    public  String list(Model model){
//        List<BoardDto> boardDtoList = boardService.getBoardlist();
//        model.addAttribute("boardList", boardDtoList);
//        return"board/list.html";
//    }

    @GetMapping("/post")
    public String write(){
        return"board/write.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }
    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no")Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("boardDto", boardDto);
        return"board/detail.html";
    }
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no")Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("boardDto", boardDto);
        return"board/update.html";
    }

    @PutMapping("/post/edit/{no}")
    public String update(@PathVariable("no") Long id, BoardDto boardDto) {
        boardService.updatePost(id,boardDto);
        return "redirect:/post/{no}";
    }

    @DeleteMapping("/post/delete/{no}")
    public String delete(@PathVariable("no") Long id) {
        boardService.deletePost(id);
        return "redirect:/";
    }
    @GetMapping("/post/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model){
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

        model.addAttribute("boardList", boardDtoList);
        return"board/list.html";

    }

    @GetMapping("/")
    public String list(Model model, @RequestParam(value="page", defaultValue = "1")Integer pageNum){
        List<BoardDto> boardList = boardService.getBoardlist(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList",pageList);

        return "board/list.html";
    }


}
