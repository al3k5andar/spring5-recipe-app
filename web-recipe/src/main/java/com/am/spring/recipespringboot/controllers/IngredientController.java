package com.am.spring.recipespringboot.controllers;

import com.am.spring.recipespringboot.commands.IngredientCommand;
import com.am.spring.recipespringboot.commands.RecipeCommand;
import com.am.spring.recipespringboot.service.IngredientService;
import com.am.spring.recipespringboot.service.RecipeService;
import com.am.spring.recipespringboot.service.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/ingredient")
public class IngredientController
{
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService,
                                IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService= ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping("/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model){
        log.debug("Getting ingredient list for recipe id: "+ recipeId);
        model.addAttribute("recipe",recipeService.findByIdCommand(Long.valueOf(recipeId)));
        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId,
                                 @PathVariable String ingredientId, Model model){
        log.debug("Getting single ingredient for given recipe id and ingredient id");
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(ingredientId)));
        return "recipe/ingredient/show";

    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable String recipeId,
                                   @PathVariable String ingredientId, Model model){
        log.debug("Getting single ingredient for update");
        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId),Long.valueOf(ingredientId)));
        model.addAttribute("unitsOfMeasure", unitOfMeasureService.listAllUnits());

        return "recipe/ingredient/ingredient-form";
    }

    @PostMapping("/{recipeId}/ingredient")
    public String saveIngredient(@ModelAttribute IngredientCommand ingredientCommand){
        IngredientCommand savedCommand= ingredientService.saveIngredientCommand(ingredientCommand);

        log.debug("Recipe id: "+ savedCommand.getRecipeId());
        log.debug("Ingredient id:"+ savedCommand.getId());

        return "redirect:/ingredient/recipe/"+savedCommand.getRecipeId()+"/ingredient/"+savedCommand.getId()+"/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredientForm(@PathVariable String recipeId, Model model){
//        make sure we have good recipe id
        RecipeCommand recipeCommand= recipeService.findByIdCommand(Long.valueOf(recipeId));
//        todo rise an exception if null

        IngredientCommand ingredientCommand= new IngredientCommand();
//        return recipe id from hidden property
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));

        model.addAttribute("ingredient", ingredientCommand);
        model.addAttribute("unitsOfMeasure", unitOfMeasureService.listAllUnits());

        return "recipe/ingredient/ingredient-form";
    }
}
