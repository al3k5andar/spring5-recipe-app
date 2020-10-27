package com.am.spring.recipespringboot.commands;

import com.am.spring.recipespringboot.domain.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

    private Long id;
    private String description;
    private int prepTime;
    private int cookTime;
    private int serving;
    private String source;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private NotesCommand notes;
    private Set<IngredientCommand> ingredientCommands= new HashSet<>();
    private Set<CategoryCommand> categoryCommands= new HashSet<>();
}
