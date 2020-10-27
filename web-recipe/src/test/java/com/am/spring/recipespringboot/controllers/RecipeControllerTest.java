package com.am.spring.recipespringboot.controllers;

import com.am.spring.recipespringboot.commands.RecipeCommand;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    public void testGetRecipe() throws Exception {
        RecipeCommand recipe= new RecipeCommand();
        recipe.setId(1L);

        //Object that test our controller
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();

        Mockito.when(recipeService.findByIdCommand((Mockito.anyLong()))).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show")) // Request controller mapping
                .andExpect(status().isOk()) // Request status -- example 200
                .andExpect(view().name("recipe/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe")); // Request index page of controller
    }

    @Test
    public void testNewRecipeForm() throws Exception {
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));
    }

    @Test
    public void testUpdateRecipe() throws Exception {
        RecipeCommand command= new RecipeCommand();
        command.setId(2L);

        Mockito.when(recipeService.findByIdCommand(Mockito.any())).thenReturn(command);

        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));


    }

    @Test
    public void deleteRecipe() throws Exception {
        MockMvc mockMvc= MockMvcBuilders.standaloneSetup(recipeController).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/index"));

        Mockito.verify(recipeService, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
}