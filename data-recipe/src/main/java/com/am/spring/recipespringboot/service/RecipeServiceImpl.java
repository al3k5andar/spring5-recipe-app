package com.am.spring.recipespringboot.service;

import com.am.spring.recipespringboot.domain.Recipe;
import com.am.spring.recipespringboot.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService
{
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
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
}