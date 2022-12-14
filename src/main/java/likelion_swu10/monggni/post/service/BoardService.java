package likelion_swu10.monggni.post.service;


import likelion_swu10.monggni.post.domain.Board;
import likelion_swu10.monggni.post.dto.BoardDto;
import likelion_swu10.monggni.post.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private  final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){
        this.boardRepository=boardRepository;
    }

    @Transactional
    public Long savePost (BoardDto boardDto){
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public List<BoardDto> getBoardlist(){
        List<Board> boards = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(Board board : boards){
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .contents(board.getContents())
                    .createdTime(board.getCreatedTime())
                    .build();

            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional
    public BoardDto getPost(Long id){
        Optional<Board> boardWrapper = boardRepository.findById(id);
        Board board = boardWrapper.get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .contents(board.getContents())
                .createdTime(board.getCreatedTime())
                .modifiedTime(board.getModifiedTime())
                .build();

        return boardDto;

    }

    @Transactional
    public Long updatePost(Long id, BoardDto boardDto){
        Board board = boardRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("?????? ???????????? ???????????? ????????????. "+id));
        board.update(boardDto);
        return id;
    }

    @Transactional
    public void deletePost(Long id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public List<BoardDto> searchPosts(String keyword) {
        List<Board> boards = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> boardDtoList = new ArrayList<>();

        if (boards.isEmpty()) return boardDtoList;

        for (Board board : boards) {
            boardDtoList.add(this.covertEntityToDto(board));
        }
        return boardDtoList;
    }

    private BoardDto covertEntityToDto(Board board){
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .contents(board.getContents())
                .createdTime(board.getCreatedTime())
                .modifiedTime(board.getModifiedTime())
                .build();

    }

//    private static final int BLOCK_PAGE_NUM_COUNT =5; //????????? ???????????? ????????? ??????
//    private static final int PAGE_POST_COUNT = 4; // ??? ???????????? ???????????? ????????? ???
//
//    @Transactional
//    public List<BoardDto> getBoardlist(Integer pageNum){
//        Page<Board> page = boardRepository.findAll(PageRequest.of(pageNum - 1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdTime")));
//
//        List<Board> boards = page.getContent();
//        List<BoardDto> boardDtoList = new ArrayList<>();
//
//        for(Board board:boards){
//            boardDtoList.add(this.covertEntityToDto(board));
//        }
//        return boardDtoList;
//    }
//    @Transactional
//    public Long getBoardCount() {
//        return boardRepository.count();
//    }
//    public Integer[] getPageList(Integer curPageNum){
//        Integer[]pageList = new Integer[BLOCK_PAGE_NUM_COUNT];
//        //??? ????????? ??????
//        Double postsTotalCount = Double.valueOf(this.getBoardCount());
//        //??? ????????? ???????????? ????????? ????????? ????????? ?????? ??????(??????)
//        Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));
//        //?????? ???????????? ???????????? ????????? ????????? ????????? ?????? ??????
//        Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT)
//                ? curPageNum + BLOCK_PAGE_NUM_COUNT
//                :totalLastPageNum;
//        //????????? ?????? ?????? ??????
//        curPageNum = (curPageNum <=3) ? 1:curPageNum-2;
//        //????????? ?????? ??????
//        for(int val=curPageNum, idx=0; val<=blockLastPageNum; val++, idx++){
//            pageList[idx]=val;
//        }
//        return pageList;
//    }


}
