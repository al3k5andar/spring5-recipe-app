package com.am.spring.recipespringboot.service;

import com.am.spring.recipespringboot.domain.Recipe;
import com.am.spring.recipespringboot.repositories.RecipeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        recipeService= new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void findByIdTest(){
        Recipe recipe= new Recipe();
        recipe.setId(1L);

        Optional<Recipe> optionalRecipe= Optional.of(recipe);

        Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(optionalRecipe);

        Recipe recipeReturned= recipeService.findById(1L);

        Assert.assertNotNull("Recipe not found", recipeReturned);
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(recipeRepository,Mockito.never()).findAll();

    }

    @Test
    public void getRecipeTest(){
        Recipe recipe= new Recipe();
        recipe.setId(1L);

        Set<Recipe> recipes= new HashSet<>();
        recipes.add(recipe);

        Mockito.when(recipeRepository.findAll()).thenReturn(recipes);

        Set<Recipe> myRecipes= recipeService.findAll();

        Assert.assertEquals(1, myRecipes.size());
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
        Mockito.verify(recipeRepository,Mockito.never()).findById(Mockito.anyLong());
    }
}