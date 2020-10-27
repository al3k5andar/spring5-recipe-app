package com.am.spring.recipespringboot.service;

import com.am.spring.recipespringboot.commands.RecipeCommand;
import com.am.spring.recipespringboot.converters.RecipeCommandToRecipe;
import com.am.spring.recipespringboot.converters.RecipeToRecipeCommand;
import com.am.spring.recipespringboot.domain.Recipe;
import com.am.spring.recipespringboot.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService
{
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> findAll(){
        Set<Recipe> getRecipes= new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(getRecipes::add);

        return getRecipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> optionalRecipe= recipeRepository.findById(id);

        if(!optionalRecipe.isPresent())
            throw new RuntimeException("Recipe not found");

        return  optionalRecipe.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe= recipeCommandToRecipe.convert(recipeCommand);

        Recipe savedRecipe= recipeRepository.save(detachedRecipe);
        log.debug("Saved recipeId: "+ savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findByIdCommand(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
