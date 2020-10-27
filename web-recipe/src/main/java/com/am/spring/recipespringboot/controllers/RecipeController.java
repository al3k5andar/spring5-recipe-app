package com.am.spring.recipespringboot.controllers;

import com.am.spring.recipespringboot.commands.RecipeCommand;
import com.am.spring.recipespringboot.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController
{
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"{id}/show"})
    public String getRecipeById(Model model, @PathVariable("id") String id){
        RecipeCommand recipeCommand= recipeService.findByIdCommand(Long.valueOf(id));

        model.addAttribute("recipe", recipeCommand);

        return "recipe/show";
    }

    @GetMapping("/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/form";
    }

    @GetMapping("/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findByIdCommand(Long.valueOf(id)));
        return "recipe/form";
    }

    @PostMapping(name = "/recipe")
    public String saveRecipeCommand(@ModelAttribute RecipeCommand recipeCommand){
        RecipeCommand savedCommand= recipeService.saveRecipeCommand(recipeCommand);

        String redirection= "/recipe/"+ savedCommand.getId()+"/show";
        return "redirect:"+ redirection;
    }

    @GetMapping("/{id}/delete")
    public String deleteRecipe(@PathVariable String id){

        log.debug("deleting recipe with ID: "+ id);

        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/index";

    }
}
