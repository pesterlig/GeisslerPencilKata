import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class Pencil2Test {
    //testing the pencil2.write(){String text, Paper paper} which returns paper.getText()
    @Test
    public void whenTextArgIs4AndDurabilityIs3_thenPaperHas3CharAnd1Space() {
        String testText = "Blah";
        Pencil2 pencil = new Pencil2(50,3,10,10,10);
        Paper paper = new Paper();
        pencil.write(testText, paper);
        assertEquals("Bla ", paper.getText());
    }

}
