package com.am.spring.recipespringboot.service;

import com.am.spring.recipespringboot.domain.Recipe;

import java.util.Set;

public interface RecipeService
{
    Set<Recipe> findAll();

}
