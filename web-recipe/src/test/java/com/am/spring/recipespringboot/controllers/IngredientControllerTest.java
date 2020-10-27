package com.am.spring.recipespringboot.controllers;

import com.am.spring.recipespringboot.commands.IngredientCommand;
import com.am.spring.recipespringboot.commands.RecipeCommand;
import com.am.spring.recipespringboot.service.IngredientService;
import com.am.spring.recipespringboot.service.RecipeService;
import com.am.spring.recipespringboot.service.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

public class IngredientControllerTest {

    IngredientController ingredientController;

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        ingredientController= new IngredientController(recipeService, ingredientService, unitOfMeasureService);
        mockMvc= MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    public void testListIngredients() throws Exception {
//        given
        RecipeCommand recipeCommand= new RecipeCommand();
        Mockito.when(recipeService.findByIdCommand(Mockito.anyLong())).thenReturn(recipeCommand);

//        when
        mockMvc.perform(MockMvcRequestBuilders.get("/ingredient/1/ingredients"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));

//        then
        Mockito.verify(recipeService, Mockito.times(1)).findByIdCommand(Mockito.anyLong());
    }

    @Test
    public void testShowIngredient() throws Exception {
//        given
        IngredientCommand ingredientCommand= new IngredientCommand();
        Mockito.when(ingredientService.findByRecipeIdAndIngredientId
                (Mockito.anyLong(),Mockito.anyLong())).thenReturn(ingredientCommand);

//        when
        mockMvc.perform(MockMvcRequestBuilders.get("/ingredient/recipe/1/ingredient/2/show"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ingredient"));

//        then
        Mockito.verify(ingredientService, Mockito.times(1))
                .findByRecipeIdAndIngredientId(Mockito.anyLong(),Mockito.anyLong());
    }

    @Test
    public void testNewIngredientForm() throws Exception {
//        Given
        RecipeCommand command= new RecipeCommand();
        command.setId(1L);

//        When
        Mockito.when(recipeService.findByIdCommand(Mockito.anyLong())).thenReturn(command);
        Mockito.when(unitOfMeasureService.listAllUnits()).thenReturn(new HashSet<>());

//        Then
        mockMvc.perform(MockMvcRequestBuilders.get("/ingredient/recipe/1/ingredient/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/ingredient-form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ingredient","unitsOfMeasure"));
    }

    @Test
    public void updateIngredient() throws Exception {
//        Given
        IngredientCommand ingredientCommand= new IngredientCommand();

//        When
        Mockito.when(ingredientService.findByRecipeIdAndIngredientId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(ingredientCommand);
        Mockito.when(unitOfMeasureService.listAllUnits()).thenReturn(new HashSet<>());

//        Then
        mockMvc.perform(MockMvcRequestBuilders.get("/ingredient/recipe/1/ingredient/1/update"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("recipe/ingredient/ingredient-form"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("ingredient", "unitsOfMeasure"));
    }

    @Test
    public void saveIngredient() throws Exception {
//        Given
        IngredientCommand command= new IngredientCommand();
        command.setId(1L);
        command.setRecipeId(1L);

//        When
        Mockito.when(ingredientService.saveIngredientCommand(Mockito.any(IngredientCommand.class))).thenReturn(command);

//        Then
        mockMvc.perform(MockMvcRequestBuilders.post("/ingredient/1/ingredient")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("recipeId","")
                    .param("description","some description"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().
                        name("redirect:/ingredient/recipe/"+command.getId()+"/ingredient/"+command.getRecipeId()+"/show"));
    }
}