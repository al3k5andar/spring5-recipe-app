package com.am.spring.recipespringboot.converters;

import com.am.spring.recipespringboot.commands.RecipeCommand;
import com.am.spring.recipespringboot.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    final NotesCommandToNotes notesCommandToNotes;
    final CategoryCommandToCategory categoryCommandToCategory;
    final IngredientCommandToIngredient ingredientCommandToIngredient;

    public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes,
                                 CategoryCommandToCategory categoryCommandToCategory,
                                 IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.notesCommandToNotes = notesCommandToNotes;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {

        if(source== null)
            return null;

        final Recipe recipe= new Recipe();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setDirections(source.getDirections());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setServing(source.getServing());
        recipe.setUrl(source.getUrl());
        recipe.setSource(source.getSource());
        recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());

        if(source.getCategoryCommands() != null && source.getCategoryCommands().size()> 0){
            source.getCategoryCommands().forEach(categoryCommand ->{
                recipe.getCategories().add(categoryCommandToCategory.convert(categoryCommand));
            });
        }

        if(source.getIngredientCommands()!= null && source.getIngredientCommands().size()> 0){
            source.getIngredientCommands().forEach(ingredientCommand ->{
                recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredientCommand));
            });
        }
        return recipe;
    }
}
