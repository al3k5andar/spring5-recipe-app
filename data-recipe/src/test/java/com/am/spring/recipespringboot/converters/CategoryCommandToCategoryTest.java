package com.am.spring.recipespringboot.converters;

import com.am.spring.recipespringboot.commands.CategoryCommand;
import com.am.spring.recipespringboot.domain.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CategoryCommandToCategoryTest {

    final Long ID= 1L;
    final String DESCRIPTION= "Some Category";

    CategoryCommandToCategory categoryCommandToCategory;

    @Before
    public void setUp() throws Exception {
        categoryCommandToCategory= new CategoryCommandToCategory();
    }

    @Test
    public void testNullParameter() throws Exception{
        Assert.assertNull(categoryCommandToCategory.convert(null));
    }

    @Test
    public void testEmptyObject(){
        Assert.assertNotNull(categoryCommandToCategory.convert(new CategoryCommand()));
    }

    @Test
    public void convert() {

        CategoryCommand categoryCommand= new CategoryCommand();
        categoryCommand.setId(ID);
        categoryCommand.setDescription(DESCRIPTION);

        Category category= categoryCommandToCategory.convert(categoryCommand);

        Assert.assertNotNull(category);
        Assert.assertEquals(ID, category.getId());
        Assert.assertEquals(DESCRIPTION, category.getDescription());
    }
}