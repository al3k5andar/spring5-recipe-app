package com.am.spring.recipespringboot.controllers;

import com.am.spring.recipespringboot.domain.Category;
import com.am.spring.recipespringboot.domain.UnitOfMeasure;
import com.am.spring.recipespringboot.repositories.CategoryRepository;
import com.am.spring.recipespringboot.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @GetMapping({"","/","/index"})
    public String getIndexPage(){
        Optional<Category> categoryOptional= categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional= unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Cat ID is: "+ categoryOptional.get().getId());
        System.out.println("UOF ID is: "+ unitOfMeasureOptional.get().getId());
        return "index";
    }
}
