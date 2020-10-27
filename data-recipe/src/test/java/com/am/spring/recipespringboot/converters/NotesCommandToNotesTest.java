package com.am.spring.recipespringboot.converters;

import com.am.spring.recipespringboot.commands.NotesCommand;
import com.am.spring.recipespringboot.domain.Notes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NotesCommandToNotesTest {

    final Long ID= 1L;
    final String RECIPE_NOTES= "Some notes";

    NotesCommandToNotes notesCommandToNotes;

    @Before
    public void setUp() throws Exception {
        notesCommandToNotes= new NotesCommandToNotes();
    }

    @Test
    public void testNullParameter(){
        Assert.assertNull(notesCommandToNotes.convert(null));
    }

    @Test
    public void testEmptyObject(){
        Assert.assertNotNull(notesCommandToNotes.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        NotesCommand notesCommand= new NotesCommand();
        notesCommand.setId(ID);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        Notes notes= notesCommandToNotes.convert(notesCommand);

        Assert.assertNotNull(notes);
        Assert.assertEquals(ID, notes.getId());
        Assert.assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}