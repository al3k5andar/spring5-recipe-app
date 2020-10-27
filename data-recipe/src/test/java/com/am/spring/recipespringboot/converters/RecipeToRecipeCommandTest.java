package com.am.spring.recipespringboot.converters;

import com.am.spring.recipespringboot.commands.RecipeCommand;
import com.am.spring.recipespringboot.domain.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RecipeToRecipeCommandTest {
    final Long RECIPE_ID= 1L;
    final String DIRECTION= "Some direction";
    final String DESCRIPTION= "Some description";
    final Difficulty DIFFICULTY= Difficulty.EASY;
    final int SERVING= 4;
    final String URL= "Some url";
    final String SOURCE= "Some source";
    final int COOK_TIME= 10;
    final int PREP_TIME= 5;
    final long NOTES_ID= 1L;
    final long CAT_ID_1= 2L;
    final long CAT_ID_2= 3L;
    final long ING_ID_1= 2L;
    final long ING_ID_2= 3L;

    RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        recipeToRecipeCommand= new RecipeToRecipeCommand(
                new NotesToNotesCommand(),
                new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
    }

    @Test
    public void testNullParameter(){
        Assert.assertNull(recipeToRecipeCommand.convert(null));
    }

    @Test
    public void testEmptyObject(){
        Assert.assertNotNull(recipeToRecipeCommand.convert(new Recipe()));
    }

    @Test
    public void convert() {
        Recipe recipe= new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setDirections(DIRECTION);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setUrl(URL);
        recipe.setServing(SERVING);
        recipe.setSource(SOURCE);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);

        Notes notes= new Notes();
        notes.setId(NOTES_ID);
        recipe.setNotes(notes);

        Category category1= new Category();
        category1.setId(CAT_ID_1);
        Category category2= new Category();
        category2.setId(CAT_ID_2);
        recipe.getCategories().add(category1);
        recipe.getCategories().add(category2);

        Ingredient ingredient1= new Ingredient();
        ingredient1.setId(ING_ID_1);
        Ingredient ingredient2= new Ingredient();
        ingredient2.setId(ING_ID_2);
        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);

        RecipeCommand command= recipeToRecipeCommand.convert(recipe);

        Assert.assertNotNull(command);
        Assert.assertNotNull(command.getCategoryCommands());
        Assert.assertNotNull(command.getIngredientCommands());
        Assert.assertEquals(RECIPE_ID, command.getId());
        Assert.assertEquals(DIRECTION, command.getDirections());
        Assert.assertEquals(DESCRIPTION, command.getDescription());
        Assert.assertEquals(DIFFICULTY, command.getDifficulty());
        Assert.assertEquals(SERVING, command.getServing());
        Assert.assertEquals(URL, command.getUrl());
        Assert.assertEquals(SOURCE, command.getSource());
        Assert.assertEquals(COOK_TIME, command.getCookTime());
        Assert.assertEquals(PREP_TIME, command.getPrepTime());
        Assert.assertEquals(2, command.getIngredientCommands().size());
        Assert.assertEquals(2,command.getCategoryCommands().size());
    }
}