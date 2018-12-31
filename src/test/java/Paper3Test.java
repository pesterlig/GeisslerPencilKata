/*
import org.junit.Test;

import static junit.framework.TestCase.*;

public class Paper3Test {
    */
/*
    As a paper, I want to store individual characters that are written on me at a specific location
    As a writer, I want to use a pencil to put my strings of text to paper -
    I know how many characters I have written, I want to be able to read the characters as a string
    As a pencil, I want to implement the writer interface, to count characters in a string of input text
    As a pencil, I want to write text to paper - take a string of text and use the point to put characters on paper

    *//*

    Pencil3 pencil;
    Paper3 paper;

    @Test
    public void givenACharacter_whenPencilWriteIsCalled_thenPaperStoresCharacterAtIndexEqualToIndexOfThatCharacterInTheInputString() {
        Character testCharacter = 'X';
        String testTextStringToWrite = "Examine your mind!";
        pencil.write(testCharacter);
        pencil.indexOfChar(testTextStringToWrite);
        int indexOfCharacter = paper.indexOf(testCharacter);
        assertEquals(indexOfCharacter, pencil.indexOfCharacter(testTextStringToWrite));
    }
}
*/
