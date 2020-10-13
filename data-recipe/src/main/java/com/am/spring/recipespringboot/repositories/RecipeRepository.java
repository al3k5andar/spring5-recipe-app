package com.am.spring.recipespringboot.repositories;

import com.am.spring.recipespringboot.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
