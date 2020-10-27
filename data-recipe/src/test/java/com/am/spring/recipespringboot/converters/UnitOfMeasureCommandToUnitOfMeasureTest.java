package com.am.spring.recipespringboot.converters;

import com.am.spring.recipespringboot.commands.UnitOfMeasureCommand;
import com.am.spring.recipespringboot.domain.UnitOfMeasure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    final Long ID= 1L;
    final String DESCRIPTION= "Some description";

    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureCommandToUnitOfMeasure= new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullParameter(){
        Assert.assertNull(unitOfMeasureCommandToUnitOfMeasure.convert(null));
    }

    @Test
    public void testEmptyObject(){
        Assert.assertNotNull(unitOfMeasureCommandToUnitOfMeasure.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() {
        UnitOfMeasureCommand command= new UnitOfMeasureCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        UnitOfMeasure unitOfMeasure= unitOfMeasureCommandToUnitOfMeasure.convert(command);

        Assert.assertNotNull(unitOfMeasure);
        Assert.assertEquals(ID, unitOfMeasure.getId());
        Assert.assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
    }
}