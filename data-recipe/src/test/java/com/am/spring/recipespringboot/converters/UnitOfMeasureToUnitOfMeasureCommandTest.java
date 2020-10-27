package com.am.spring.recipespringboot.converters;

import com.am.spring.recipespringboot.commands.UnitOfMeasureCommand;
import com.am.spring.recipespringboot.domain.UnitOfMeasure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    final Long ID= 1L;
    final String DESCRIPTION= "Some description";

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureToUnitOfMeasureCommand= new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullParameter(){
        Assert.assertNull(unitOfMeasureToUnitOfMeasureCommand.convert(null));
    }

    @Test
    public void testEmptyObject(){
        Assert.assertNotNull(unitOfMeasureToUnitOfMeasureCommand.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() {
        UnitOfMeasure unitOfMeasure= new UnitOfMeasure();
        unitOfMeasure.setId(ID);
        unitOfMeasure.setDescription(DESCRIPTION);

        UnitOfMeasureCommand command= unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure);

        Assert.assertNotNull(command);
        Assert.assertEquals(ID, command.getId());
        Assert.assertEquals(DESCRIPTION, command.getDescription());
    }
}