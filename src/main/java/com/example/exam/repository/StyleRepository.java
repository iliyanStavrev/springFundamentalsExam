package com.example.exam.repository;

import com.example.exam.model.entity.Style;
import com.example.exam.model.enums.StyleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {

    Style findByName(StyleNameEnum style);
}
