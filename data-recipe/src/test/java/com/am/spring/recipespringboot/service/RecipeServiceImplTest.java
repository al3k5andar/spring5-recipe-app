package com.am.spring.recipespringboot.service;

import com.am.spring.recipespringboot.commands.RecipeCommand;
import com.am.spring.recipespringboot.converters.RecipeCommandToRecipe;
import com.am.spring.recipespringboot.converters.RecipeToRecipeCommand;
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

import static org.mockito.ArgumentMatchers.anyLong;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        recipeService= new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void findByIdTest(){
        Recipe recipe= new Recipe();
        recipe.setId(1L);

        Optional<Recipe> optionalRecipe= Optional.of(recipe);

        Mockito.when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        Recipe recipeReturned= recipeService.findById(1L);

        Assert.assertNotNull("Recipe not found", recipeReturned);
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(anyLong());
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
        Mockito.verify(recipeRepository,Mockito.never()).findById(anyLong());
    }

    @Test
    public void saveRecipeCommand() {
        final Long ID= 1L;
        Recipe recipe= new Recipe();
        recipe.setId(ID);

        Mockito.when(recipeRepository.save(Mockito.any())).thenReturn(recipe);

        Recipe savedRecipe= recipeRepository.save(recipe);
        Mockito.verify(recipeRepository).save(Mockito.any());
        Assert.assertEquals(ID, savedRecipe.getId());
    }

    @Test
    public void findByIdCommand() {
        Recipe recipe= new Recipe();
        recipe.setId(1L);

        Optional<Recipe> optionalRecipe= Optional.of(recipe);

        Mockito.when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        RecipeCommand recipeCommand= new RecipeCommand();
        recipe.setId(1L);

        Mockito.when(recipeToRecipeCommand.convert(Mockito.any())).thenReturn(recipeCommand);

        RecipeCommand commandById= recipeService.findByIdCommand(1L);

        Assert.assertNotNull("Null recipe returned",commandById);
        Mockito.verify(recipeRepository).findById(anyLong());
        Mockito.verify(recipeRepository, Mockito.never()).findAll();
    }

    @Test
    public void deleteById() {
        Long id= 2L;

        recipeRepository.deleteById(id);

        Mockito.verify(recipeRepository,Mockito.times(1)).deleteById(anyLong());
    }

    @Test
    public void findById() {
        Recipe recipe= new Recipe();
        recipe.setId(1L);

        Optional<Recipe> optionalRecipe= Optional.of(recipe);

        Mockito.when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
    }

}