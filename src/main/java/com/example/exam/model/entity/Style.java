package com.example.exam.model.entity;

import com.example.exam.model.enums.StyleNameEnum;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Style extends BaseEntity{

    private StyleNameEnum name;
    private String description;

    @Column(nullable = false,unique = true)
    public StyleNameEnum getName() {
        return name;
    }

    public void setName(StyleNameEnum name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
