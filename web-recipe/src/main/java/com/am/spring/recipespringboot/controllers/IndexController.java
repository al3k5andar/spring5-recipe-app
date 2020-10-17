package com.am.spring.recipespringboot.controllers;

import com.am.spring.recipespringboot.repositories.CategoryRepository;
import com.am.spring.recipespringboot.repositories.UnitOfMeasureRepository;
import com.am.spring.recipespringboot.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }


    @GetMapping({"","/","index","index.html"})
    public String getAllRecipes(Model model){
        log.debug("Getting index page");

        model.addAttribute("recipes", recipeService.findAll());

        return "index";
    }
}
