import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;
/*
Sprint Goals for Friday 12/21
    -0.5) Pomodoro - 25 min on, 5 min break (get a drink of water), 25 etc.  15 min break after 4 pomos (2hrs) then 4 again, then 1/2 hr or 1 hr break - food etc - then 4&4 pomos
    0.) Merge git branches from Wed and Thurs and start a Friday branch - Done
    1.) Make test more general and assert multiple times within the same test for specific cases (per BL advice) - Done-ish - came to a different understanding about this
    2.) Fix the write method in Pencil2 - Done!
    2.5) (Note: Remember the overloaded pencil constructor and make use as needed, remove currentEraserDurability as well) - Done
    3.) Extract the durability method that takes a ch arg from the setter and call it something else (per BL advice)
    4.) Work on the erase method using reverse String and split String

*/

public class Pencil2Test {
    //testing the pencil2.write(){String text, Paper paper} which returns paper.getText()
    Paper paper;
    Pencil2 pencil;

    @Before

    @Test
    public void givenPointDurabilityLessThanInputTextLength_whenPencilWrites_thenPaperTextLengthEqualsPointDurability() {
        prepContextGivenPointDurabilityLessThanInputTextLength("Blah",50,10,20);
        assertEquals("Blah", paper.getText());
    }
    //can't assert the same thing multiple times in the same test, but could assert different things to test multiple parts of a complex method?


    private void prepContextGivenPointDurabilityLessThanInputTextLength(String testText, int initialPointDurability, int length, int initialEraserDurability){
        pencil = new Pencil2(initialPointDurability, length, initialEraserDurability);
        paper = new Paper();
        pencil.write(testText, paper);
    }

    //testing the Pencil2.sharpen() method that restores initialPointDurability and reduces length by one
    @Test
    public void whenSharpenIsCalled_thenCurrentPointDurabilityIsSetToInitialPointDurability() {
        Pencil2 pencil = new Pencil2(50, 3, 10, 10, 10);
        pencil.sharpen();
        assertEquals(pencil.getInitialPointDurability(), pencil.getCurrentPointDurability());
    }

    @Test
    public void whenSharpenIsCalled_thenLengthIsDecreasedBy1() {
        Pencil2 pencil = new Pencil2(50, 3, 10, 10, 10);
        pencil.sharpen();
        assertEquals(9, pencil.getLength());
    }

    //Testing the Pencil2.erase(String text, Paper paper) method that removes written characters from paper and compares to paper.getText()
    @Test
    public void givenEraseTextWhichIsASubstringOfPaperGetText_thenReturnAnIntegerValueForIndexOfTheFirstCharacterOfTheLastOccurance() {
        String testText = "OMG! Blah Blah";
        String testErasableText = "Blah";
        Pencil2 pencil = new Pencil2(50, 50, 10, 50, 50);
        Paper paper = new Paper();
        pencil.write(testText, paper);
        pencil.erase(testErasableText, paper);
        assertEquals(10, pencil.erase(testErasableText, paper));
    }

    @Test
    public void whenEraseTextLengthIsLessThanEraserDurability_thenTheLastInstanceOfTheTextIsReplacedWithBlankSpaces() {
        String testText = "OMG! Blah Blah";
        String testErasableText = "Blah";
        Pencil2 pencil = new Pencil2(50, 50, 10, 50, 50);
        Paper paper = new Paper();
        pencil.write(testText, paper);
        pencil.erase(testErasableText, paper);
        assertEquals("OMG! Blah     ", paper.getText());
    }

    @Test
    public void whenEraseTextArgIs4AndEraserDurabilityIs3_thenTheLast3CharsAreReplacedWithBlankSpacesOnPaper() {
        String testText = "Blah";
        Pencil2 pencil = new Pencil2(50, 50, 10, 10, 3);
        Paper paper = new Paper();
        pencil.write(testText, paper);
        pencil.erase(testText, paper);
        assertEquals("B   ", paper.getText());


    }
}

