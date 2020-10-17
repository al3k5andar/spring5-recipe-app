package com.am.spring.recipespringboot.controllers;

import com.am.spring.recipespringboot.domain.Recipe;
import com.am.spring.recipespringboot.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

public class RecipeControllerTest {

    RecipeController recipeController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        recipeController= new RecipeController(recipeService);
    }

//    Test MVC Controller
    @Test
    public void TestGetRecipe() throws Exception {
        Recipe recipe= new Recipe();
        recipe.setId(1L);

        //Object that test our controller
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();

        Mockito.when(recipeService.findById(Mockito.anyLong())).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/1")) // Request controller mapping
                .andExpect(MockMvcResultMatchers.status().isOk()) // Request status -- example 200
                .andExpect(MockMvcResultMatchers.view().name("recipe/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe")); // Request index page of controller
    }
}