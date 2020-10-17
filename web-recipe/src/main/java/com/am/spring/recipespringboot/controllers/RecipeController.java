package com.am.spring.recipespringboot.controllers;

import com.am.spring.recipespringboot.domain.Recipe;
import com.am.spring.recipespringboot.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping({"show/{id}"})
    public String getRecipeById(Model model, @PathVariable("id") String id){
        Recipe recipe= recipeService.findById(Long.valueOf(id));

        model.addAttribute("recipe", recipe);

        return "recipe/show";
    }
}
