package likelion_swu10.monggni.post.domain;

// Post.java

import likelion_swu10.monggni.post.dto.BoardDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor

@Entity
public class Board extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length=100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String contents;

   @Builder
    public Board(Long id, String title, String contents){
       this.id=id;
       this.title=title;
       this.contents=contents;
   }

   public void update(BoardDto boardDto){
       this.title=boardDto.getTitle();
       this.contents=boardDto.getContents();
   }
   //실제 데이터베이스에 반영되도록 업데이트

}