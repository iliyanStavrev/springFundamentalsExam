package com.example.exam.web;

import com.example.exam.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final SongService songService;

    public HomeController(SongService songService) {
        this.songService = songService;
    }


    @GetMapping("/home")
    public String homePage(Model model){

        model.addAttribute("myPlaylist",songService.getMyPlaylist());
        model.addAttribute("pops",songService.getAllPop());
        model.addAttribute("rocks",songService.getAllRocks());
        model.addAttribute("jazz",songService.getAllJazz());
        model.addAttribute("total", songService.getTotal());

        return "home";
    }

    @GetMapping("/add/{id}")
    public String addToMyPlaylist(@PathVariable Long id){

        songService.addToMyPlaylist(id);

        return "redirect:/home";
    }
    @GetMapping("/delete")
    public String deleteMySongs(){

        songService.deleteMyPlaylist();

        return "redirect:/home";
    }
}
