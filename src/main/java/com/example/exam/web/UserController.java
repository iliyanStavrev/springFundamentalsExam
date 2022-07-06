package com.example.exam.web;

import com.example.exam.model.dto.LoginDto;
import com.example.exam.model.dto.RegisterDto;
import com.example.exam.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public RegisterDto registerDto(){
        return new RegisterDto();
    }

    @ModelAttribute
    public LoginDto loginDto(){
        return new LoginDto();
    }

    @GetMapping("/register")
    public String registerPage(){


        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterDto registerDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors() || !registerDto.getPassword()
                .equals(registerDto.getConfirmPassword())){

            redirectAttributes
                    .addFlashAttribute("registerDto",registerDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.registerDto",bindingResult);

            return "redirect:/register";
        }

        if (!userService.register(registerDto)){
            return "redirect:/register";
        }

        userService.register(registerDto);

        return "/login";
    }

    @GetMapping("login")
    public String loginPage(){


        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){


        if (bindingResult.hasErrors()){

            redirectAttributes
                    .addFlashAttribute("loginDto",loginDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.loginDto",bindingResult);
            return "redirect:/login";
        }
        if (!userService.login(loginDto)){
            redirectAttributes.addFlashAttribute("isFound",false);
            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(){

        userService.logout();

        return "redirect:/";
    }
}
