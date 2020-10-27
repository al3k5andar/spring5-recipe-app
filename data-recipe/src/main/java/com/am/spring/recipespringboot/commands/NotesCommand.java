package com.am.spring.recipespringboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotesCommand
{
    private Long id;
    private String recipeNotes;
    private RecipeCommand recipeCommand;
}
