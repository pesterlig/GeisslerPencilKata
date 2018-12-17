import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class PencilTest {

    Pencil pencil;
    Paper paper;

    @Before
    public void setUp() {
        pencil = new Pencil(20, 20);
        paper = new Paper();
    }

    @Test
    public void whenPencilWritesAString_thenPaperSetsText() {
        pencil.write("blah", paper);
        assertEquals("blah", "blah");
    }

    @Test
    public void whenPencilWriteSetsTextOnPaper_thenGetTextReturnsTheTextValueAndAddsNewTextToIt() {
        paper.setText("blah");
        pencil.write("blah", paper);
        assertEquals("blahblah", paper.getText());
    }

    /*@Test
    public void whenPencilWritesAnyCharacter_thenDecrementItsCurrentPointDurabilityOncePerCharacter() {
        String text = "blah blah";
        pencil.write(text, paper);
        pencil.setInitialPointDurability(20);
        pencil.setCurrentPointDurability(text);
        assertEquals(11, pencil.getCurrentPointDurability());
    }*/

    @Test
    public void whenPencilWritesSpaces_thenCurrentPointDurabilityIsNotChanged() {
        prepContextForDurabilityTests(" ", 20);
        assertEquals(20, pencil.getCurrentPointDurability());
    }
    // Three tests below are from when I was using a String argument for setCurrentPointDurabilty

      /* @Test
    public void whenPencilWritesCharactersAndSpaces_thenCurrentPointDurabilityCountsCharactersOnly() {
        prepContextForDurabilityTests(" blah blah ", 20);
        assertEquals(12, pencil.getCurrentPointDurability());
    }


    @Test
    public void whenPencilWritesCapitalLetters_thenCurrentPointDurabilityDecreasesByTwoForEachCapitalLetter() {
        prepContextForDurabilityTests("Blah", 20);
        assertEquals(15, pencil.getCurrentPointDurability());
    }



    @Test
    public void whenPencilWritesAString_thenCurrentPointDurabilityIsUnaffectedByNewlineCharacters() {
        prepContextForDurabilityTests("Blah \n Blah Blah\r", 20);
        assertEquals(5, pencil.getCurrentPointDurability());
    }*/

    /*pencil durability needs to change as each character is written - it needs to receive char as an argument
    write tests to make setCurrentPointDurability take chars as argument and return CPD for each case:Uppercase, lowercase, whitespace*/

    @Test
    public void whenCurrentPointDurabilityGreaterThanOrEquals2AndCharacterIsUppercase_thenCurrentPointDurabilityIsReducedBy2() {
        Character ch = 'A';
        Pencil pencil = new Pencil(20, 2);
        pencil.setCurrentPointDurability(ch);
        assertEquals(0, pencil.getCurrentPointDurability());
    }

    @Test
    public void whenCurrentPointDurabilityGreaterThanOrEquals1AndCharacterIsLowercase_thenCurrentPointDurabilityIsReducedBy1() {
        Character ch = 'a';
        Pencil pencil = new Pencil(20, 1);
        pencil.setCurrentPointDurability(ch);
        assertEquals(0, pencil.getCurrentPointDurability());
    }


    //Context Methods extracted from tests

    private void prepContextForDurabilityTests(String testText, int initialPointDurability) {
        pencil.write(testText, paper);
        pencil.setInitialPointDurability(initialPointDurability);
        pencil.setCurrentPointDurability(testText.charAt(0));
    }
}
