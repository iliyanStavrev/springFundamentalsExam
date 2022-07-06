package com.example.exam.init;

import com.example.exam.service.StyleService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InitDB implements CommandLineRunner {

    private final StyleService styleService;

    public InitDB(StyleService styleService) {
        this.styleService = styleService;
    }

    @Override
    public void run(String... args) throws Exception {

      styleService.initStyles();

    }


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
