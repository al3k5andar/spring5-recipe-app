package com.am.spring.recipespringboot.converters;

import com.am.spring.recipespringboot.commands.NotesCommand;
import com.am.spring.recipespringboot.domain.Notes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NotesToNotesCommandTest {

    final Long ID= 1L;
    final String RECIPE_NOTES= "Some notes";

    NotesToNotesCommand notesToNotesCommand;

    @Before
    public void setUp() throws Exception {
        notesToNotesCommand= new NotesToNotesCommand();
    }

    @Test
    public void testNullParameter(){
        Assert.assertNull(notesToNotesCommand.convert(null));
    }

    @Test
    public void testEmptyObject(){
        Assert.assertNotNull(notesToNotesCommand.convert(new Notes()));
    }

    @Test
    public void convert() {
        Notes notes= new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(RECIPE_NOTES);

        NotesCommand notesCommand= notesToNotesCommand.convert(notes);

        Assert.assertNotNull(notesCommand);
        Assert.assertEquals(ID, notesCommand.getId());
        Assert.assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }
}