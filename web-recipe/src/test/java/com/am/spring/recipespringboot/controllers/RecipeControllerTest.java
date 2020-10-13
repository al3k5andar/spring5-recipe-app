package com.am.spring.recipespringboot.controllers;

import com.am.spring.recipespringboot.domain.Recipe;
import com.am.spring.recipespringboot.service.RecipeService;
import org.junit.Assert;
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

import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void getAllRecipes() {
        String expected= "recipe/index";
        String actual= recipeController.getAllRecipes(model);

        Set<Recipe> recipes= new HashSet<>();

//        Assert if the returned string equals with expected
        Assert.assertEquals(expected,actual);

//        Verify if the model method addAttribute is coled one time
        Mockito.verify(model, Mockito.times(1)).addAttribute("recipes", recipes);

//        Verify if the recipe service is called once
        Mockito.verify(recipeService, Mockito.times(1)).findAll();
    }

//    Test MVC Controller
    @Test
    public void TestRecipeController() throws Exception {
        //Object that test our controller
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe")) // Request controller mapping
                .andExpect(MockMvcResultMatchers.status().isOk()) // Request status -- example 200
                .andExpect(MockMvcResultMatchers.view().name("recipe/index")); // Request index page of controller
    }
}