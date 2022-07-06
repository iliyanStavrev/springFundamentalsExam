package com.example.exam.service;

import com.example.exam.model.entity.Style;
import com.example.exam.model.enums.StyleNameEnum;
import com.example.exam.repository.StyleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StyleService {

    private final StyleRepository styleRepository;

    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public void initStyles() {

        if (styleRepository.count() > 0){
            return;
        }

        Arrays.stream(StyleNameEnum
                .values())
                .forEach(s ->{
                    Style style = new Style();
                    style.setName(s);
                    switch (s){
                        case POP -> style.setDescription("That's pop music babe!!!");
                        case JAZZ -> style.setDescription("Let's jazz crazy babe!!!");
                        case ROCK -> style.setDescription("We will rock you!!!");
                    }
                    styleRepository.save(style);
                });
    }

    public Style findByName(String style) {
       return styleRepository
                .findByName(StyleNameEnum.valueOf(style));
    }
}
