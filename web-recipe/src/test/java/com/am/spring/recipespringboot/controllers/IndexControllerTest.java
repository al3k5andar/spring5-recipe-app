package com.am.spring.recipespringboot.controllers;

import com.am.spring.recipespringboot.domain.Recipe;
import com.am.spring.recipespringboot.repositories.CategoryRepository;
import com.am.spring.recipespringboot.repositories.UnitOfMeasureRepository;
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

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        indexController= new IndexController(categoryRepository,unitOfMeasureRepository,recipeService);
    }

    @Test
    public void testIndexController() throws Exception {
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    public void getAllRecipes() {
        String expected= "index";
        String actual= indexController.getAllRecipes(model);

        Set<Recipe> recipes= new HashSet<>();

//        Assert if the returned string equals with expected
        Assert.assertEquals(expected,actual);

//        Verify if the model method addAttribute is coled one time
        Mockito.verify(model, Mockito.times(1)).addAttribute("recipes", recipes);

//        Verify if the recipe service is called once
        Mockito.verify(recipeService, Mockito.times(1)).findAll();
    }
}