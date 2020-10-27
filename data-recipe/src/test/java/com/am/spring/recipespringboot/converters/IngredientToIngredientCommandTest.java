package com.am.spring.recipespringboot.converters;

import com.am.spring.recipespringboot.commands.IngredientCommand;
import com.am.spring.recipespringboot.domain.Ingredient;
import com.am.spring.recipespringboot.domain.UnitOfMeasure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class IngredientToIngredientCommandTest {

    final Long ID= 1L;
    final String DESCRIPTION= "Some description";
    final BigDecimal AMOUNT= new BigDecimal("1");
    final Long UOF_ID= 1L;

    IngredientToIngredientCommand ingredientToIngredientCommand;

    @Before
    public void setUp() throws Exception {
        ingredientToIngredientCommand= new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullParameter(){
        Assert.assertNull(ingredientToIngredientCommand.convert(null));
    }

    @Test
    public void testEmptyObject(){
        Assert.assertNotNull(ingredientToIngredientCommand.convert(new Ingredient()));
    }

    @Test
    public void convert() {
        Ingredient ingredient= new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        UnitOfMeasure unitOfMeasure= new UnitOfMeasure();
        unitOfMeasure.setId(UOF_ID);
        ingredient.setUnitOfMeasure(unitOfMeasure);

        IngredientCommand command= ingredientToIngredientCommand.convert(ingredient);

        Assert.assertNotNull(command);
        Assert.assertNotNull(command.getUnitOfMeasureCommand());
        Assert.assertEquals(ID, command.getId());
        Assert.assertEquals(DESCRIPTION, command.getDescription());
        Assert.assertEquals(AMOUNT, command.getAmount());
        Assert.assertEquals(UOF_ID, command.getUnitOfMeasureCommand().getId());
    }

    @Test
    public void testConvertWithNullUOM(){
        Ingredient ingredient= new Ingredient();
        ingredient.setId(ID);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);

        IngredientCommand command= ingredientToIngredientCommand.convert(ingredient);

        Assert.assertNotNull(command);
        Assert.assertNull(command.getUnitOfMeasureCommand());
        Assert.assertEquals(ID, command.getId());
        Assert.assertEquals(DESCRIPTION, command.getDescription());
        Assert.assertEquals(AMOUNT, command.getAmount());
    }
}