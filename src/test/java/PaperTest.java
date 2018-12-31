import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class PaperTest {

    Paper paper;
    Pencil pencil;

    @Before
    public void setUp() {
        paper = new Paper("");
        pencil = new Pencil(100, 10, 20);
    }

    @Test
    public void whenPaperIsPassedAStringOfText_ThenPaperSetsText() {
        paper.setText("content");
        assertEquals("content", "content");
    }

    @Test
    public void whenPaperSetsText_ThenTheGetTextMethodReturnsTheSameTextValue() {
        String testVal = "blah";
        paper.setText(testVal);
        assertEquals(testVal, paper.getText());
    }

    @Test
    public void whenPaperGetsErased_thenErasureIndicesGetReturnsTheIndexOfTheFirstCharacterOfTheLastErasure() {
        String testText = "An apple a day keeps the doctor away";
        String testText1ToErase = "doctor";
        String testText2ToErase = "apple";
        pencil.write(testText, paper);
        pencil.erase(testText1ToErase, paper);
        pencil.erase(testText2ToErase, paper);
        int indexOfLastElementInErasureIndices = paper.getErasureIndices().size()-1;
        int indexOfLastErasure = paper.getErasureIndices().get(indexOfLastElementInErasureIndices);
        assertEquals(3, indexOfLastErasure);
    }


}
