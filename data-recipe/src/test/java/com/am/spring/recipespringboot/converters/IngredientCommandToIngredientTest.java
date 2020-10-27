package com.am.spring.recipespringboot.converters;

import com.am.spring.recipespringboot.commands.IngredientCommand;
import com.am.spring.recipespringboot.commands.UnitOfMeasureCommand;
import com.am.spring.recipespringboot.domain.Ingredient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class IngredientCommandToIngredientTest {

    final Long ID= 1L;
    final Long UOM_ID= 1L;
    final String DESCRIPTION= "Some description";
    final BigDecimal AMOUNT = new BigDecimal("1");


    IngredientCommandToIngredient ingredientCommandToIngredient;

    @Before
    public void setUp() throws Exception {
        ingredientCommandToIngredient= new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullParameter(){
        Assert.assertNull(ingredientCommandToIngredient.convert(null));
    }

    @Test
    public void testEmptyObject(){
        Assert.assertNotNull(ingredientCommandToIngredient.convert(new IngredientCommand()));
    }

    @Test
    public void convert() {
        IngredientCommand ingredientCommand= new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);
        UnitOfMeasureCommand unitOfMeasureCommand= new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        ingredientCommand.setUnitOfMeasureCommand(unitOfMeasureCommand);


        Ingredient ingredient= ingredientCommandToIngredient.convert(ingredientCommand);

        Assert.assertNotNull(ingredient);
        Assert.assertEquals(ID, ingredient.getId());
        Assert.assertEquals(DESCRIPTION, ingredient.getDescription());
        Assert.assertEquals(AMOUNT, ingredient.getAmount());
        Assert.assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
    }

    @Test
    public void testWithEmptyUOM(){
        IngredientCommand ingredientCommand= new IngredientCommand();
        ingredientCommand.setId(ID);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);

        Ingredient ingredient= ingredientCommandToIngredient.convert(ingredientCommand);

        Assert.assertNotNull(ingredient);
        Assert.assertNull(ingredient.getUnitOfMeasure());
        Assert.assertEquals(ID, ingredient.getId());
        Assert.assertEquals(DESCRIPTION, ingredient.getDescription());
        Assert.assertEquals(AMOUNT, ingredient.getAmount());
    }
}