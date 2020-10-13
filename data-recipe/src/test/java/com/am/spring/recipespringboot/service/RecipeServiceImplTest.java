package com.am.spring.recipespringboot.service;

import com.am.spring.recipespringboot.domain.Recipe;
import com.am.spring.recipespringboot.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        recipeService= new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void findAll() {

        Recipe recipe= new Recipe();
        Set<Recipe> recipes= new HashSet<>();
        recipes.add(recipe);

//        Tell mockito object what to return when is recipeRepository called
        Mockito.when(recipeRepository.findAll()).thenReturn(recipes);

        Set<Recipe> recipeSet= recipeService.findAll();
        assertEquals(recipeSet.size(), 1);

//        Verify how many times is Recipe Repository called
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }
}