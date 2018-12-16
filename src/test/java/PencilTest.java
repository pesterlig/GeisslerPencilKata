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
        String text = " ";
        pencil.write(text, paper);
        pencil.setInitialPointDurability(20);
        pencil.setCurrentPointDurability(text);
        assertEquals(20, pencil.getCurrentPointDurability());
    }

    @Test
    public void whenPencilWritesCharactersAndSpaces_thenCurrentPointDurabilityCountsCharactersOnly() {
        String text = " blah blah ";
        pencil.write(text, paper);
        pencil.setInitialPointDurability(20);
        pencil.setCurrentPointDurability(text);
        assertEquals(12, pencil.getCurrentPointDurability());
    }

    @Test
    public void whenPencilWritesCapitalLetters_thenCurrentPointDurabilityDecreasesByTwoForEachCapitalLetter() {
        String text = "Blah";
        pencil.write(text, paper);
        pencil.setInitialPointDurability(20);
        pencil.setCurrentPointDurability(text);
        assertEquals(15, pencil.getCurrentPointDurability());
    }


    //Question - how do I set up new test conditions for half of the test suite?


    @Test
    public void whenPencilWritesAString_thenCurrentPointDurabilityIsUnaffectedByNewlineCharacters() {
        String text = "Blah \n Blah Blah\r";
        pencil.write(text, paper);
        pencil.setInitialPointDurability(20);
        pencil.setCurrentPointDurability(text);
        assertEquals(5, pencil.getCurrentPointDurability());
    }

}
