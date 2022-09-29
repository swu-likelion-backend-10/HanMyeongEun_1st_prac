package likelion_backend.RegisterApi.post.domain;

// Post.java

import likelion_backend.RegisterApi.post.dto.RegisterDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity

public class Register extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length=5, nullable = false)
    private String name;

    @Column(length=5, nullable = false)
    private String age;

    @Column(length=50, nullable = false)
    private String major;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String intro;

   @Builder
    public Register(Long id, String name, String age, String major, String intro){
       this.id=id;
       this.name=name;
       this.age=age;
       this.major=major;
       this.intro=intro;
   }

   public void update(RegisterDto registerDto){
       this.name = registerDto.getName();
       this.age = registerDto.getAge();
       this.major = registerDto.getMajor();
       this.intro = registerDto.getIntro();
   }

}
