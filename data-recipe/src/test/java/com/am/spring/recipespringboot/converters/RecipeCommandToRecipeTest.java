package com.am.spring.recipespringboot.converters;

import com.am.spring.recipespringboot.commands.CategoryCommand;
import com.am.spring.recipespringboot.commands.IngredientCommand;
import com.am.spring.recipespringboot.commands.NotesCommand;
import com.am.spring.recipespringboot.commands.RecipeCommand;
import com.am.spring.recipespringboot.domain.Difficulty;
import com.am.spring.recipespringboot.domain.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RecipeCommandToRecipeTest {

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


    RecipeCommandToRecipe recipeCommandToRecipe;


    @Before
    public void setUp() throws Exception {
        recipeCommandToRecipe= new RecipeCommandToRecipe(
                new NotesCommandToNotes(),
                new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()));
    }

    @Test
    public void testNullParameter(){
        Assert.assertNull(recipeCommandToRecipe.convert(null));
    }

    @Test
    public void testEmptyObject(){
        Assert.assertNotNull(recipeCommandToRecipe.convert(new RecipeCommand()));
    }

    @Test
    public void convert() {
        RecipeCommand command= new RecipeCommand();
        command.setId(RECIPE_ID);
        command.setDirections(DIRECTION);
        command.setDescription(DESCRIPTION);
        command.setDifficulty(DIFFICULTY);
        command.setUrl(URL);
        command.setServing(SERVING);
        command.setSource(SOURCE);
        command.setCookTime(COOK_TIME);
        command.setPrepTime(PREP_TIME);

        NotesCommand notesCommand= new NotesCommand();
        notesCommand.setId(NOTES_ID);
        command.setNotes(notesCommand);

        CategoryCommand categoryCommand1= new CategoryCommand();
        categoryCommand1.setId(CAT_ID_1);
        CategoryCommand categoryCommand2= new CategoryCommand();
        categoryCommand1.setId(CAT_ID_2);

        command.getCategoryCommands().add(categoryCommand1);
        command.getCategoryCommands().add(categoryCommand2);

        IngredientCommand ingredientCommand1= new IngredientCommand();
        ingredientCommand1.setId(ING_ID_1);
        IngredientCommand ingredientCommand2= new IngredientCommand();
        ingredientCommand2.setId(ING_ID_2);

        command.getIngredientCommands().add(ingredientCommand1);
        command.getIngredientCommands().add(ingredientCommand2);

        Recipe recipe= recipeCommandToRecipe.convert(command);

        Assert.assertNotNull(recipe);
        Assert.assertNotNull(recipe.getCategories());
        Assert.assertNotNull(recipe.getIngredients());
        Assert.assertEquals(RECIPE_ID, (Long) recipe.getId());
        Assert.assertEquals(DIRECTION, recipe.getDirections());
        Assert.assertEquals(DESCRIPTION, recipe.getDescription());
        Assert.assertEquals(DIFFICULTY, recipe.getDifficulty());
        Assert.assertEquals(SERVING, recipe.getServing());
        Assert.assertEquals(URL, recipe.getUrl());
        Assert.assertEquals(SOURCE, recipe.getSource());
        Assert.assertEquals(COOK_TIME, recipe.getCookTime());
        Assert.assertEquals(PREP_TIME, recipe.getPrepTime());
        Assert.assertEquals(2, recipe.getIngredients().size());
        Assert.assertEquals(2,recipe.getCategories().size());
    }

}