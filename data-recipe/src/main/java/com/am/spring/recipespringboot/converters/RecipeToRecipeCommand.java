package com.am.spring.recipespringboot.converters;

import com.am.spring.recipespringboot.commands.RecipeCommand;
import com.am.spring.recipespringboot.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    final NotesToNotesCommand notesToNotesCommand;
    final CategoryToCategoryCommand categoryToCategoryCommand;
    final IngredientToIngredientCommand ingredientToIngredientCommand;

    public RecipeToRecipeCommand(NotesToNotesCommand notesToNotesCommand,
                                 CategoryToCategoryCommand categoryToCategoryCommand,
                                 IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.notesToNotesCommand = notesToNotesCommand;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {

        if(source== null)
            return null;

        RecipeCommand recipeCommand= new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setServing(source.getServing());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));

        if(source.getCategories()!= null && source.getCategories().size()> 0){
            source.getCategories().forEach(category -> {
                recipeCommand.getCategoryCommands().add(categoryToCategoryCommand.convert(category));
            });
        }

        if(source.getIngredients()!= null && source.getIngredients().size()> 0){
            source.getIngredients().forEach(ingredient -> {
                recipeCommand.getIngredientCommands().add(ingredientToIngredientCommand.convert(ingredient));
            });
        }
        return recipeCommand;
    }
}
