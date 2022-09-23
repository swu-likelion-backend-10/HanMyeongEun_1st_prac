package likelion_backend.RegisterApi.post2.service;

import likelion_backend.RegisterApi.post2.dto.RegisterDto;
import likelion_backend.RegisterApi.post2.repository.RegisterRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
