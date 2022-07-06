package com.example.exam.web;

import com.example.exam.model.dto.AddDto;
import com.example.exam.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SongController {

    private final SongService songService;


    public SongController(SongService songService) {
        this.songService = songService;
    }

    @ModelAttribute
    public AddDto addDto(){
        return new AddDto();
    }

    @GetMapping("/add")
    public String addPage(){

        return "song-add";
    }
    @PostMapping("/add")
    public String addSong(@Valid AddDto addDto,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){

            redirectAttributes.addFlashAttribute("addDto",addDto);
            redirectAttributes.addFlashAttribute
                    ("org.springframework.validation.BindingResult.addDto"
                            ,bindingResult);

            return "redirect:/add";
        }
        if (!songService.addSong(addDto)){
            return "redirect:/add";
        }

        songService.addSong(addDto);

        return "redirect:/home";
    }

}
