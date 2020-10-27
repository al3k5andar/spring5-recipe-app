package com.am.spring.recipespringboot.service;

import com.am.spring.recipespringboot.commands.IngredientCommand;
import com.am.spring.recipespringboot.converters.IngredientCommandToIngredient;
import com.am.spring.recipespringboot.converters.IngredientToIngredientCommand;
import com.am.spring.recipespringboot.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.am.spring.recipespringboot.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.am.spring.recipespringboot.domain.Ingredient;
import com.am.spring.recipespringboot.domain.Recipe;
import com.am.spring.recipespringboot.repositories.RecipeRepository;
import com.am.spring.recipespringboot.repositories.UnitOfMeasureRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    //init converters
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient= new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        ingredientService = new IngredientServiceImpl(recipeRepository,
                ingredientToIngredientCommand,
                unitOfMeasureRepository,
                ingredientCommandToIngredient);
    }

    @Test
    public void findByRecipeIdAndRecipeIdHappyPath() throws Exception {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(1L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //when
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testSaveIngredientCommand(){
//        Given
        IngredientCommand ingredientCommand= new IngredientCommand();
        ingredientCommand.setId(3L);
        ingredientCommand.setRecipeId(2L);

        Optional<Recipe> optionalRecipe= Optional.of(new Recipe());

        Recipe savedRecipe= new Recipe();
        savedRecipe.getIngredients().add(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        Mockito.when(recipeRepository.findById(Mockito.anyLong())).thenReturn(optionalRecipe);
        Mockito.when(recipeRepository.save(Mockito.any(Recipe.class))).thenReturn(savedRecipe);

//        When
        IngredientCommand command= ingredientService.saveIngredientCommand(ingredientCommand);

//        Then
        Assert.assertNotNull(command);
        Assert.assertEquals(Long.valueOf(3L),command.getId());
        Mockito.verify(recipeRepository, Mockito.times(1)).findById(Mockito.anyLong());
        Mockito.verify(recipeRepository, Mockito.times(1)).save(Mockito.any(Recipe.class));
    }
}