package com.am.spring.recipespringboot.service;

import com.am.spring.recipespringboot.commands.IngredientCommand;
import com.am.spring.recipespringboot.converters.IngredientCommandToIngredient;
import com.am.spring.recipespringboot.converters.IngredientToIngredientCommand;
import com.am.spring.recipespringboot.domain.Ingredient;
import com.am.spring.recipespringboot.domain.Recipe;
import com.am.spring.recipespringboot.repositories.RecipeRepository;
import com.am.spring.recipespringboot.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService
{
    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 UnitOfMeasureRepository unitOfMeasureRepository,
                                 IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()){
            //todo impl error handling
            log.error("recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            //todo impl error handling
            log.error("Ingredient id not found: " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Optional<Recipe> recipeOptional= recipeRepository.findById(ingredientCommand.getRecipeId());

        if(!recipeOptional.isPresent()){
//            todo toss error if not found
            log.debug("Recipe not found for id: "+ ingredientCommand.getRecipeId());
            return new IngredientCommand();
        }
        else {
            Recipe recipe= recipeOptional.get();

            Optional<Ingredient> optionalIngredient= recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                    .findFirst();
            if(optionalIngredient.isPresent()){
                Ingredient ingredient= optionalIngredient.get();
                ingredient.setId(ingredientCommand.getId());
                ingredient.setAmount(ingredientCommand.getAmount());
                ingredient.setDescription(ingredientCommand.getDescription());
                log.debug("Unit of measure command id: "+ ingredientCommand.getUnitOfMeasureCommand().getId());
                ingredient.setUnitOfMeasure(unitOfMeasureRepository
                        .findById(ingredientCommand.getUnitOfMeasureCommand()
                                .getId())
                        .orElseThrow(()-> new RuntimeException("Unit Of Measure not found!") // todo address this
                ));
            }
            else {
//            add new ingredient
                Ingredient ingredient= ingredientCommandToIngredient.convert(ingredientCommand);
                ingredient.setRecipe(recipe);
                recipe.getIngredients().add(ingredient);
            }
            Recipe savedRecipe= recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional= savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientCommand.getId()))
                    .findFirst();
            if(!savedIngredientOptional.isPresent()){
                savedIngredientOptional= savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(ingredientCommand.getUnitOfMeasureCommand().getId()))
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientCommand.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientCommand.getAmount()))
                        .findFirst();

            }
//            todo check for fail
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }
    }
}
