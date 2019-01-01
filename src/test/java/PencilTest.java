import org.junit.Test;

import static junit.framework.TestCase.*;


public class PencilTest {
    //testing the pencil.write(){String text, Paper paper} which returns paper.getText()
    Paper paper;
    Pencil pencil;


    @Test
    public void givenPointDurabilityGreaterThanInputTextLength_whenPencilWrites_thenPaperHasSameText() {
        prepContextForWhenPencilWrites("Blah", 50, 10, 20);
        assertEquals("Blah", paper.getText());
    }

    @Test
    public void givenPointDurabilityGreaterThanLongInputTextLength_whenPencilWrites_thenPaperHasSameText() {
        prepContextForWhenPencilWrites("War does not determine who's right-\nWar determines who's left", 100, 10, 20);
        assertEquals("War does not determine who's right-\nWar determines who's left", paper.getText());
    }

    @Test
    public void givenInputTextWithWhitespaceChars_whenWriteIsCalled_thenCurrentPointDurabilityIsNotDecreasedForWhitespaceChars() {
        prepContextForWhenPencilWrites("if a woodchuck could chuck wood?", 100, 10, 20);
        assertEquals(73, pencil.getPointDurability());
    }

    @Test
    public void givenInputTextWithUppercaseChars_whenWriteIsCalled_thenCurrentPointDurabilityIsDecreasedOneExtraForUppercaseChars() {
        prepContextForWhenPencilWrites("If a Woodchuck could chuck wood?", 100, 10, 20);
        assertEquals(71, pencil.getPointDurability());
    }

    @Test
    public void givenInputTextWithVariousTypesOfChars_whenWriteIsCalledMultipleTimes_thenNewTextOnPaperIsAppendedImmediatelyAfterPreviousText() {
        prepContextForWhenPencilWrites("How much wood would a WoodChuck chuck, ", 100, 10, 20);
        pencil.write("If a Woodchuck could chuck wood?", paper);
        assertEquals("How much wood would a WoodChuck chuck, If a Woodchuck could chuck wood?", paper.getText());
    }


    //testing the Pencil.sharpen() method that restores initialPointDurability and reduces length by one

    @Test
    public void whenSharpenIsCalled_thenCurrentPointDurabilityIsSetToInitialPointDurability() {
        Pencil pencil = new Pencil(50, 3, 10, 10, 10);
        pencil.sharpen();
        assertEquals(pencil.getInitialPointDurability(), pencil.getPointDurability());
    }

    @Test
    public void whenSharpenIsCalled_thenLengthIsDecreasedBy1() {
        Pencil pencil = new Pencil(50, 3, 10, 10, 10);
        pencil.sharpen();
        assertEquals(9, pencil.getLength());
    }

    //Testing the Pencil.erase(String text, Paper paper) method that removes written characters from paper and compares to paper.getText()


    @Test
    public void givenTextToErase_whenCalculateLengthOfErasableTextisCalled_thenIntOfTextLengthIsReturnedWhicheverIsLess() {
        Pencil pencil = new Pencil(100, 10, 20);
        String testTextToErase = "does";
        pencil.calculateLengthOfErasableText(testTextToErase);
        assertEquals(4, pencil.calculateLengthOfErasableText(testTextToErase));
    }

    @Test
    public void givenTextToEraseLongerThanEraserDurability_whenCalculateLengthOfErasableTextisCalled_thenIntEraserDurabilityIsReturnedWhicheverIsLess() {
        Pencil pencil = new Pencil(100, 10, 3);
        String testTextToErase = "does";
        pencil.calculateLengthOfErasableText(testTextToErase);
        assertEquals(3, pencil.calculateLengthOfErasableText(testTextToErase));
    }

    @Test
    public void givenTextStringInput_whenCreateBlankIsCalled_thenAStringOfSpacesOfTextLengthIsReturned() {
        Pencil pencil = new Pencil(100, 10, 20);
        String testText = "War does";
        pencil.createBlankTextOfLength(testText, testText.length());
        assertEquals("        ", pencil.createBlankTextOfLength(testText, testText.length()));

    }

    @Test
    public void givenTextStringInput_whenCreateBlankIsCalled_thenCurrentEraserDurabilityDecreasesByLengthNotIncludingWhitespaceCharacters() {
        Pencil pencil = new Pencil(100, 10, 20);
        String testText = "War does";
        pencil.createBlankTextOfLength(testText, testText.length());
        assertEquals(13, pencil.getEraserDurability());
    }

    @Test
    public void whenEraseTextLengthIsLessThanEraserDurability_thenTheLastInstanceOfTheTextIsReplacedWithBlankSpaces() {
        String testText = "OMG! Blah Blah";
        String testTextToErase = "Blah";
        Pencil pencil = new Pencil(50, 50, 10, 50, 50);
        Paper paper = new Paper("");
        pencil.write(testText, paper);
        pencil.erase(testTextToErase, paper);
        assertEquals("OMG! Blah     ", paper.getText());
    }

    @Test
    public void givenEraserDurabilityLessThanTextToErase_whenCalculateLengthOfErasableTextIsCalled_thenIntEraserDurabilityIsReturned() {
        String testText = "OMG! Blah Blah";
        String testTextToErase = "Blah";
        Pencil pencil = new Pencil(100, 10, 3);
        Paper paper = new Paper("");
        pencil.write(testText, paper);
        pencil.calculateLengthOfErasableText(testTextToErase);
        assertEquals(pencil.getEraserDurability(), pencil.calculateLengthOfErasableText(testTextToErase));
    }

    @Test
    public void givenEraserDurabilityGreaterThanTextToErase_whenEraseIsCalled_thenLastInstanceOfTextReplacedWithBlankSpaces() {
        String testText = "Optimist: She who doesn't understand the complexity of the Pencil Kata yet";
        String testTextToErase = "the";
        Pencil pencil = new Pencil(100, 10, 50);
        Paper paper = new Paper("");
        pencil.write(testText, paper);
        pencil.erase(testTextToErase, paper);
        assertEquals("Optimist: She who doesn't understand the complexity of     Pencil Kata yet", paper.getText());
    }

    @Test
    public void givenEraserDurabilityGreaterThanTextToErase_whenEraseIsCalledTwice_thenLastTwoInstancesOfTextReplacedWithBlankSpaces() {
        String testText = "War does not determine who's right-\nWar determines who's left";
        String testTextToErase = "who";
        Pencil pencil = new Pencil(100, 10, 50);
        Paper paper = new Paper("");
        pencil.write(testText, paper);
        pencil.erase(testTextToErase, paper);
        pencil.erase(testTextToErase, paper);

        assertEquals("War does not determine    's right-\nWar determines    's left", paper.getText());
    }

    @Test
    public void givenEraserDurabilitylessThanTextToErase_whenEraseIsCalledTwice_thenLastTwoInstancesOfTextReplacedWithBlankSpacesAndRemainingText() {
        String testText = "War does not determine who's right-\nWar determines who's left";
        String testTextToErase = "who";
        Pencil pencil = new Pencil(100, 10, 5);
        Paper paper = new Paper("");
        pencil.write(testText, paper);
        pencil.erase(testTextToErase, paper);
        pencil.erase(testTextToErase, paper);

        assertEquals("War does not determine w  's right-\nWar determines    's left", paper.getText());
    }

    @Test
    public void givenEraserDurabilityEqualTo1andHalfOfText_whenEraseCalledThrice_then1stInstanceErased2ndPartiallyErased3rdNotErased() {
        String testText = "How much wood could a wood chuck chuck,\nif a wood chuck could chuck wood?";
        String testTextToErase = "chuck";
        Pencil pencil = new Pencil(100, 10, 7);
        Paper paper = new Paper("");
        pencil.write(testText, paper);
        pencil.erase(testTextToErase, paper);
        pencil.erase(testTextToErase, paper);
        pencil.erase(testTextToErase, paper);
        assertEquals("How much wood could a wood chuck chuck,\nif a wood chu   could       wood?", paper.getText());
    }

    @Test
    public void whenEraseTextArgIs4AndEraserDurabilityIs3_thenTheLast3CharsAreReplacedWithBlankSpacesOnPaper() {
        String testText = "OMG! Blah Blah";
        String testTextToErase = "Blah";
        Pencil pencil = new Pencil(100, 10, 3);
        Paper paper = new Paper("");
        pencil.write(testText, paper);
        pencil.erase(testTextToErase, paper);
        assertEquals("OMG! Blah B   ", paper.getText());
    }


    //Testing the edit method


    @Test
    public void givenReplacementText_whenEditIsCalled_thenPaperHasReplacementTextWhereErasedTextWas() {
        String testText = "OMG! Blah Blah";
        String testTextToErase = "Blah";
        String testReplacementText = "Owls";
        Pencil pencil = new Pencil(100, 10, 20);
        Paper paper = new Paper("");
        pencil.write(testText, paper);
        pencil.erase(testTextToErase, paper);
        pencil.edit(testReplacementText, paper);
        assertEquals("OMG! Blah Owls", paper.getText());
    }

    @Test
    public void givenReplacementTextLongerThanErasedTExt_whenEditIsCalledThenPaperHasReplacementTextWhereErasedTextWasWithOverwriteSymbols(){
        String testText = "An apple a day keeps the doctor away";
        String testTextToErase = "apple";
        String testReplacementText = "artichoke";
        Pencil pencil = new Pencil(100, 10, 20);
        Paper paper = new Paper("");
        pencil.write(testText, paper);
        pencil.erase(testTextToErase, paper);
        pencil.edit(testReplacementText, paper);
        assertEquals("An artich@k@ay keeps the doctor away", paper.getText());
    }

    @Test
    public void givenTextToErase_whenFindIndexOfLastOccurrenceOfErasableTextIsCalled_thenIntegerOfFirstIndexIsReturnedForEraserDurabilityGreaterThanTextLength() {
        String testText = "OMG! Blah Blah";
        String testTextToErase = "Blah";
        Pencil pencil = new Pencil(100, 10, 20);
        Paper paper = new Paper("");
        pencil.write(testText, paper);
        pencil.findIndexOfLastOccurrenceOfErasableText(testTextToErase, paper);
        assertEquals(10, pencil.findIndexOfLastOccurrenceOfErasableText(testTextToErase, paper));

    }

    @Test
    public void givenTextToErase_whenFindIndexOfLastOccurrenceOfErasableTextIsCalled_thenIntegerOfFirstIndexIsReturnedForAnyEraserDurabilityGreaterThan0() {
        String testText = "OMG! Blah Blah";
        String testTextToErase = "Blah";
        Pencil pencil = new Pencil(100, 10, 3);
        Paper paper = new Paper("");
        pencil.write(testText, paper);
        pencil.findIndexOfLastOccurrenceOfErasableText(testTextToErase, paper);
        assertEquals(11, pencil.findIndexOfLastOccurrenceOfErasableText(testTextToErase, paper));

    }

    //Below: private methods to create context for some tests

    private void prepContextForWhenPencilWrites(String testText, int initialPointDurability, int length, int initialEraserDurability) {
        pencil = new Pencil(initialPointDurability, length, initialEraserDurability);
        paper = new Paper("");
        pencil.write(testText, paper);
    }

}


