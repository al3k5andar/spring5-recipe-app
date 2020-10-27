package com.am.spring.recipespringboot.service;

import com.am.spring.recipespringboot.commands.RecipeCommand;
import com.am.spring.recipespringboot.domain.Recipe;

import java.util.Set;

public interface RecipeService
{
    Set<Recipe> findAll();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findByIdCommand(Long id);

    void deleteById(Long id);

}
