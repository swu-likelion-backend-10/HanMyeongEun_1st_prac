package likelion_backend.RegisterApi.post.controller;

import likelion_backend.RegisterApi.post.dto.RegisterDto;
import likelion_backend.RegisterApi.post.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService){

        this.registerService=registerService;
    }

    @GetMapping("/")
    public String list(Model model){
        List<RegisterDto> registerDtoList = registerService.getRegisterlist();
        model.addAttribute("registerList", registerDtoList);

        return "register/list.html";
    }

    @GetMapping("/post")
    public String write(){return "register/write.html";}

    @PostMapping("/post")
    public String write(RegisterDto registerDto){
        registerService.savePost(registerDto);
        return "redirect:/";
    }
    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no")Long id, Model model){
        RegisterDto registerDto = registerService.getPost(id);
        model.addAttribute("registerDto", registerDto);
        return"register/detail.html";
    }
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no")Long id, Model model){
        RegisterDto registerDto = registerService.getPost(id);
        model.addAttribute("registerDto", registerDto);
        return"register/update.html";
    }

    @PutMapping("/post/edit/{no}")
    public String update(@PathVariable("no") Long id, RegisterDto registerDto) {
        registerService.updatePost(id,registerDto);
        return "redirect:/post/{no}";
    }

    @DeleteMapping("/post/delete/{no}")
    public String delete(@PathVariable("no") Long id) {
        registerService.deletePost(id);
        return "redirect:/";
    }


}
