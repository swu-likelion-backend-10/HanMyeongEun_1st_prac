package likelion_backend.RegisterApi.post2.dto;

import likelion_backend.RegisterApi.post2.domain.Information;
import lombok.Builder;

import java.time.LocalDateTime;

public class RegisterDto {

    private Long id;
    private String name;

    private String age;
    private String major;
    private String intro;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public Information toEntity() {
        Information build = Information.builder()
                .id(id)
                .name(name)
                .age(age)
                .major(major)
                .intro(intro)
                .build();
        
        return build;
    }
    @Builder
    public RegisterDto(Long id, String name,String age, String major, String intro, LocalDateTime createdTime, LocalDateTime modifiedTime){

        this.id=id;
        this.name=name;
        this.age=age;
        this.major=major;
        this.intro=intro;
        this.createdTime=createdTime;
        this.modifiedTime=modifiedTime;
    }
}

