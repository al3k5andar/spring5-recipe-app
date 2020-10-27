package com.am.spring.recipespringboot.converters;

import com.am.spring.recipespringboot.commands.CategoryCommand;
import com.am.spring.recipespringboot.domain.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CategoryToCategoryCommandTest {

    final Long ID= 1L;
    final String DESCRIPTION= "Some description";

    CategoryToCategoryCommand categoryToCategoryCommand;

    @Before
    public void setUp() throws Exception {
        categoryToCategoryCommand= new CategoryToCategoryCommand();
    }

    @Test
    public void testNullParameter(){
        Assert.assertNull(categoryToCategoryCommand.convert(null));
    }

    @Test
    public void testEmptyObject(){
        Assert.assertNotNull(categoryToCategoryCommand.convert(new Category()));
    }

    @Test
    public void convert() {
        Category category= new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);

        CategoryCommand categoryCommand= categoryToCategoryCommand.convert(category);

        Assert.assertNotNull(categoryCommand);
        Assert.assertEquals(ID, categoryCommand.getId());
        Assert.assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}