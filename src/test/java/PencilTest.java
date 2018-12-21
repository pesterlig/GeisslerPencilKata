import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class PencilTest {
    /*
    Leaving this test suite and test class for the time being - want to approach the problem in a more agile way
    do a sprint to make a complete pencil with length, pointDurability, and an eraser
    but with only basic capabilities for each...not taking too much time - maybe just an afternoon sprint
    feels like I'm getting bogged down and haven't used testing wisely to help me step thru the logic
    */
    Paper paper;

    Pencil pencil;


    @Before
    public void setUp() {
        pencil = new Pencil(20);
        paper = new Paper("");
    }

    /*@Test
    public void whenPencilWritesAString_thenPaperSetsText() {
        pencil.write("blah", paper);
        assertEquals("blah", "blah");
    }*/

    /*@Test
    public void whenPencilWriteSetsTextOnPaper_thenGetTextReturnsTheTextValueAndAddsNewTextToIt() {
        paper.setText("blah");
        pencil.write("blah", paper);
        assertEquals("blahblah", paper.getText());
    }*/

    //Tests for the Write() method, which checks currentPointDurability and adds characters to String array called: visibleText and calls paper.setText
    @Test
    public void whenPencilTextIsOneLowercaseCharAndDurabilityIs1_thenPaperGetTextMethodReturnsThatChar() {
        prepContextForWriteMethodTests("b", 20, 1);
        assertEquals("b", paper.getText());
    }

    @Test
    public void whenPencilTextIsAWhitespaceCharAndDurabilityIs0_thenPaperGetTextMethodReturnsThatChar() {
        prepContextForWriteMethodTests("\n", 20, 0);
        assertEquals("\n", paper.getText());
    }

    @Test
    public void whenDurabilityIs2orMore_thenPaperGetTextReturnsAnyOneCharacterExactly() {
        prepContextForWriteMethodTests("P", 20, 2);
        assertEquals("P", paper.getText());
    }

    @Test
    // Nex two tests: cast the blank spaces to ints and compare values so I have integers to compare in failing tests
    public void whenDurabilityIs1AndChIsUppercase_thenPaperGetTextReturns1BlankSpaceChar() {
        prepContextForWriteMethodTests("P", 20, 1);
        assertEquals((int) (" ".charAt(0)), paper.getText().charAt(0));
    }

    @Test
    public void whenDurabilityIs0AndChIsNotWhitespace_thenPaperGetTextReturns1BlankSpaceChar() {
        prepContextForWriteMethodTests("#", 20, 0);
        assertEquals((int) (" ".charAt(0)), paper.getText().charAt(0));
    }

    @Test
    // In progress - aiming for : when text count of lowercase NonWhitespaceChars is equal to currentPointDurability,
    // text still prints exactly as written
    public void whenPencilWritesFewerCharactersAndSpacesThanCurrentDurability_thenPaperGetTextReturnsThoseCharactersAndSpaces() {
        String testText = " blah blah";
        pencil = new Pencil(20, 20);
        pencil.write(testText, paper);
        assertEquals(" blah blah", paper.getText());
    }


    @Test
    public void whenPencilCapitalizesBlahAndDurabilityIs3_thenPaperGetTextReturnsBlPlus2Spaces() {
        prepContextForWriteMethodTests("Blah", 20, 3);
        assertEquals("Bl  ", paper.getText());
    }

    @Test
    public void whenPencilCapitalizesBlahAndDurabilityIs2_thenPaperGetTextReturnsBPlus3Spaces() {
        prepContextForWriteMethodTests("Blah", 20, 2);
        assertEquals("B   ", paper.getText());
    }


    @Test
    public void whenTextIsBlahBlahBlahwithNewlinesAndSpaces_AndNonWhitespaceCharsAreEqualToDurability_thenPaperGetTextReturnsTheEntireText() {
        prepContextForWriteMethodTests("Blah \n Blah Blah\r", 20, 15);
        assertEquals("Blah \n Blah Blah\r", paper.getText());
    }

    @Test //this test found that the method added three whitespace char
    public void whenTextIsBlahBlahBlahwithNewlinesAndSpaces_thenCountWhitespaceIsEqualforTestTextAndVisibleText(){
     prepContextForWriteMethodTests("Blah \n Blah Blah\r",50,15);
     assertEquals(pencil.countWhitespaceChars("Blah \n Blah Blah\r"),pencil.countWhitespaceChars(paper.getText()));
    }

    @Test //this test found that the method lost an Uppercase char
    public void whenTextIsBlahBlahBlahwithNewlinesAndSpaces_thenCountUppercaseIsEqualforTestTextAndVisibleText(){
        prepContextForWriteMethodTests("Blah \n Blah Blah\r",50,15);
        assertEquals(pencil.countUppercaseChars("Blah \n Blah Blah\r"),pencil.countUppercaseChars(paper.getText()));
    }

    @Test //this test found that the method lost three Non Whitespace chars
    public void whenTextIsBlahBlahBlahwithNewlinesAndSpaces_thenCountNonWhitespaceIsEqualforTestTextAndVisibleText(){
        prepContextForWriteMethodTests("Blah \n Blah Blah\r",50,15);
        assertEquals(pencil.countNonWhitespaceChars("Blah \n Blah Blah\r"),pencil.countNonWhitespaceChars(paper.getText()));
    }




    /*
    how do I know the tests above are universal and non-trivial?  They are getting ridiculous...
    Need to count both whitespace and nonwhitespace chars in a given text, plus uppercase chars to use for comparisons
    to see that pencil is writing properly
    */

    @Test
    public void whenPencilTextIsEntered_thenCountNonWhitespaceCharsReturnsACountOfAllNonWhitespaceChars() {
        pencil = new Pencil(40, 40);
        String testText = "Half of Large Intestine\nEquals 1 ; ";
        pencil.countNonWhitespaceChars(testText);
        assertEquals(28, pencil.countNonWhitespaceChars(testText));
    }

    @Test
    public void whenPencilTextIsEntered_thenCountWhitespaceCharsReturnsACountOfAllWhitespaceChars() {
        pencil = new Pencil(40, 40);
        String testText = "Half of Large Intestine\nEquals 1 ; ";
        pencil.countWhitespaceChars(testText);
        assertEquals(7, pencil.countWhitespaceChars(testText));
    }

    @Test
    public void whenPencilTextIsEntered_thenCountUppercaseCharsReturnsACountOfAllUppercaseChars() {
        pencil = new Pencil(40, 40);
        String testText = "Half of Large Intestine\nEquals 1 ; ";
        pencil.countWhitespaceChars(testText);
        assertEquals(4, pencil.countUppercaseChars(testText));
    }

    /*
    Now I can enter visibleText into the count methods and see if things are writing properly.
    Seems like the methods in Pencil for the three above tests are for testing purposes
    and should not exist in production code...refactor later?
    */


    /*pencil durability needs to change as each character is written - it needs to receive char as an argument
    write tests to make setCurrentPointDurability take chars as argument and return CPD for each case:Uppercase, lowercase, whitespace*/

    @Test
    public void whenCurrentPointDurabilityGreaterThanOrEquals2AndCharacterIsUppercase_thenCurrentPointDurabilityIsReducedBy2() {
        prepContextForSetDurabilityTests('A', 20, 2);
        assertEquals(0, pencil.getCurrentPointDurability());
    }

    @Test
    public void whenCurrentPointDurabilityGreaterThanOrEquals1AndCharacterIsLowercase_thenCurrentPointDurabilityIsReducedBy1() {
        prepContextForSetDurabilityTests('a', 20, 1);
        assertEquals(0, pencil.getCurrentPointDurability());
    }

    @Test
    public void whenCurrentPointDurabilityGreaterThanOrEquals0AndCharacterIsNewline_thenCurrentPointDurabilityRemainsTheSame() {
        prepContextForSetDurabilityTests('\n', 20, 0);
        assertEquals(0, pencil.getCurrentPointDurability());
    }

    @Test
    public void whenCurrentPointDurabilityGreaterThanOrEquals1AndCharacterIsNoTUppercaseAndNotWhitespace_thenCurrentPointDurabilityIsReducedBy1() {
        prepContextForSetDurabilityTests('$', 20, 1);
        assertEquals(0, pencil.getCurrentPointDurability());
    }


    //Context Methods extracted from tests

    private void prepContextForSetDurabilityTests(Character testCh, int initialPointDurability, int currentPointDurability) {
        pencil = new Pencil(initialPointDurability, currentPointDurability);
        pencil.setCurrentPointDurability(testCh);

    }

    private void prepContextForWriteMethodTests(String testText, int initialPointDurability, int currentPointDurability) {
        pencil = new Pencil(initialPointDurability, currentPointDurability);
        pencil.write(testText, paper);
    }




}
