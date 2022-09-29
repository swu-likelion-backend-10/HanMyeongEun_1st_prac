package likelion_backend.RegisterApi.post.service;

import likelion_backend.RegisterApi.post.domain.Register;
import likelion_backend.RegisterApi.post.dto.RegisterDto;
import likelion_backend.RegisterApi.post.repository.RegisterRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {
    private  final RegisterRepository registerRepository;

    public RegisterService(RegisterRepository registerRepository){

        this.registerRepository= registerRepository;
    }

    @Transactional
    public Long savePost (RegisterDto registerDto){

        return registerRepository.save(registerDto.toEntity()).getId();
    }
    @Transactional
    public List<RegisterDto> getRegisterlist(){
        List<Register> registers = registerRepository.findAll();
        List<RegisterDto> registerDtoList = new ArrayList<>();

        for(Register register : registers){
            RegisterDto registerDto = RegisterDto.builder()
                    .id(register.getId())
                    .name(register.getName())
                    .age(register.getAge())
                    .major(register.getMajor())
                    .intro(register.getIntro())
                    .createdTime(register.getCreatedTime())
                    .build();

            registerDtoList.add(registerDto);
        }
        return registerDtoList;
    }

    @Transactional
    public RegisterDto getPost(Long id){
        Optional<Register> boardWrapper = registerRepository.findById(id);
        Register register = boardWrapper.get();

        RegisterDto registerDto = RegisterDto.builder()
                .id(register.getId())
                .name(register.getName())
                .age(register.getAge())
                .major(register.getMajor())
                .intro(register.getIntro())
                .createdTime(register.getCreatedTime())
                .modifiedTime(register.getModifiedTime())
                .build();

        return registerDto;

    }

    @Transactional
    public Long updatePost(Long id, RegisterDto registerDto){
        Register register = registerRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("해당 게시글은 존재하지 않습니다. "+id));
        register.update(registerDto);
        return id;
    }

    @Transactional
    public void deletePost(Long id){
        registerRepository.deleteById(id);
    }
}
