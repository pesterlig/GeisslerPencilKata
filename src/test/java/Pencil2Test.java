import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class Pencil2Test {
    //testing the pencil2.write(){String text, Paper paper} which returns paper.getText()
    @Test
    public void whenTextArgIs4AndPointDurabilityIs3_thenPaperHas3CharAnd1Space() {
        String testText = "Blah";
        Pencil2 pencil = new Pencil2(50, 3, 10, 10, 10);
        Paper paper = new Paper();
        pencil.write(testText, paper);
        assertEquals("Bla ", paper.getText());
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
    public void whenEraseTextArgIs4AndEraserDurabilityIs3_thenTheLast3CharsAreReplacedWithBlankSpacesOnPaper() {
        String testText = "Blah";
        Pencil2 pencil = new Pencil2(50, 50, 10, 10, 3);
        Paper paper = new Paper();
        pencil.write(testText, paper);
        pencil.erase(testText, paper);
        assertEquals("B   ", paper.getText());


    }
}

