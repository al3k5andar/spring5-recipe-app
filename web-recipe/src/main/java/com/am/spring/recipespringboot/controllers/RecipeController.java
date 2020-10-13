package com.am.spring.recipespringboot.controllers;

import com.am.spring.recipespringboot.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController
{
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"","/","index","index.html"})
    public String getAllRecipes(Model model){
        log.debug("Getting index page");

        model.addAttribute("recipes", recipeService.findAll());

        return "recipe/index";
    }
}
