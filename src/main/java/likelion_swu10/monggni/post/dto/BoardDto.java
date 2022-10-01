package likelion_swu10.monggni.post.dto;

import likelion_swu10.monggni.post.domain.Board;
import likelion_swu10.monggni.post.repository.BoardRepository;
import lombok.Builder;
import lombok.Getter;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class BoardDto {

    private Long id;
    private String title;
    private String contents;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public Board toEntity() {
        Board build = Board.builder()
                .id(id)
                .title(title)
                .contents(contents)
                .build();
        return build;
    }
    @Builder
    public BoardDto(Long id, String title, String contents, LocalDateTime createdTime, LocalDateTime modifiedTime){
        this.id=id;
        this.title=title;
        this.contents=contents;
        this.createdTime=createdTime;
        this.modifiedTime=modifiedTime;
    }

}

