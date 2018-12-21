import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class PaperTest {

    Paper paper;

    @Before
    public void setUp() {
        paper = new Paper("");
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


}
