import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Result;

import static junit.framework.TestCase.*;
/*
Sprint Goals for Friday 12/21
    -0.5) Pomodoro - 25 min on, 5 min break (get a drink of water), 25 etc.  15 min break after 4 pomos (2hrs) then 4 again, then 1/2 hr or 1 hr break - food etc - then 4&4 pomos
    0.) Merge git branches from Wed and Thurs and start a Friday branch - Done
    1.) Make test more general and assert multiple times within the same test for specific cases (per BL advice) - Done-ish - came to a different understanding about this
    2.) Fix the write method in Pencil2 - Done!
    2.5) (Note: Remember the overloaded pencil constructor and make use as needed, remove currentEraserDurability as well) - Done
    3.) Extract the durability method that takes a ch arg from the setter and call it something else (per BL advice) - issue with Pencil, not Pencil2 - skipping for now, but will do correctly later
    4.) Work on the erase method using reverse String - Done..not quite werking :(


*/

public class Pencil2Test {
    //testing the pencil2.write(){String text, Paper paper} which returns paper.getText()
    Paper paper;
    Pencil2 pencil;


    @Test
    public void givenPointDurabilityGreaterThanInputTextLength_whenPencilWrites_thenPaperTextLengthEqualsPointDurability() {
        prepContextForWhenPencilWrites("Blah", 50, 10, 20);
        assertEquals("Blah", paper.getText());
    }

    @Test
    public void givenPointDurabilityGreaterThanLongInputTextLength_whenPencilWrites_thenPaperTextLengthEqualsPointDurability() {
        prepContextForWhenPencilWrites("War does not determine who's right-\nWar determines who's left", 100, 10, 20);
        assertEquals("War does not determine who's right-\nWar determines who's left", paper.getText());
    }


    private void prepContextForWhenPencilWrites(String testText, int initialPointDurability, int length, int initialEraserDurability) {
        pencil = new Pencil2(initialPointDurability, length, initialEraserDurability);
        paper = new Paper("");
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
    public void givenTextStringInput_whenReverseIsCalled_thenTheSameStringBackwardsIsReturned() {
        Pencil2 pencil = new Pencil2(100, 10, 20);
        String testText = "War does";
        pencil.reverse(testText);
        assertEquals("seod raW", pencil.reverse(testText));
    }

    @Test
    public void givenTextStringInput_whenCreateBlankIsCalled_thenAStringOfSpacesOfTextLengthIsReturned() {
        Pencil2 pencil = new Pencil2(100, 10, 20);
        String testText = "War does";
        pencil.createBlankTextOfLength(testText, testText.length());
        assertEquals("        ", pencil.createBlankTextOfLength(testText, testText.length()));

    }

    @Test
    public void givenTextStringInput_whenCreateBlankIsCalled_thenCurrentEraserDurabilityDecreasesByLength() {
        Pencil2 pencil = new Pencil2(100, 10, 20);
        String testText = "War does";
        pencil.createBlankTextOfLength(testText, testText.length());
        assertEquals(12, pencil.getCurrentEraserDurability());
    }


    @Test
    public void whenEraseTextLengthIsLessThanEraserDurability_thenTheLastInstanceOfTheTextIsReplacedWithBlankSpaces() {
        String testText = "OMG! Blah Blah";
        String testTextToErase = "Blah";
        Pencil2 pencil = new Pencil2(50, 50, 10, 50, 50);
        Paper paper = new Paper("");
        pencil.write(testText, paper);
        pencil.erase(testTextToErase, paper);
        assertEquals("OMG! Blah     ", paper.getText());
    }

    @Test
    public void givenEraserDurabilityLessThanTextToErase_whenCalculateLengthOfErasableTextIsCalled_thenIntEraserDurabilityIsReturned() {
        String testText = "OMG! Blah Blah";
        String testTextToErase = "Blah";
        Pencil2 pencil = new Pencil2(100, 10, 3);
        Paper paper = new Paper("");
        pencil.write(testText, paper);
        pencil.calculateLengthOfErasableText(testTextToErase);
        assertEquals(pencil.getCurrentEraserDurability(), pencil.calculateLengthOfErasableText(testTextToErase));
    }

    @Test
    public void givenEraserDurabilityGreaterThanTextToErase_whenEraseIsCalled_thenLastInstanceOfTextReplacedWithBlankSpaces() {
        String testText = "Optimist: She who doesn't understand the complexity of the Pencil Kata yet";
        String testTextToErase = "the";
        Pencil2 pencil = new Pencil2(100, 10, 50);
        Paper paper = new Paper("");
        pencil.write(testText, paper);
        pencil.erase(testTextToErase, paper);
        assertEquals("Optimist: She who doesn't understand the complexity of     Pencil Kata yet", paper.getText());
    }


    @Test
    public void whenEraseTextArgIs4AndEraserDurabilityIs3_thenTheLast3CharsAreReplacedWithBlankSpacesOnPaper() {
        String testText = "OMG! Blah Blah";
        String testTextToErase = "Blah";
        Pencil2 pencil = new Pencil2(100, 10, 3);
        Paper paper = new Paper("");
        pencil.write(testText, paper);
        pencil.erase(testTextToErase, paper);
        assertEquals("-1", paper.getText());
    }
}


