package likelion_backend.RegisterApi.post2.controller;

import likelion_backend.RegisterApi.post2.dto.RegisterDto;
import likelion_backend.RegisterApi.post2.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService){

        this.registerService=registerService;
    }

    @GetMapping("/")
    public String list(){

        return "information/list.html";
    }

    @GetMapping("/post")
    public String write(){

        return "information/write.html";
    }

    @PostMapping("/post")
    public String write(RegisterDto registerDto){
        registerService.savePost(registerDto);
        return "redirect:/";
    }
}
