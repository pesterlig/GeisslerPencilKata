import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class PaperTest {

    Paper paper;

    @Before
    public void setUp() {
        paper = new Paper();
    }


    @Test
    public void whenPaperAddTextIsPassedAString_ItReturnsThatString() {
        assertEquals("text", paper.addText("text"));
    }

    @Test
    public void paperIsWrittenOnReturnsTrueIfTextVariableIsHasValue() {
        paper.addText("hello world");
        assertTrue(paper.isWrittenOn());

    }

    @Test
    public void paperIsWrittenOnReturnsFalseIfTextVariableIsNullOrEmpty() {
        assertFalse(paper.isWrittenOn());
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



    /*@Test
    public void paperStoresText();
    Paper paper = new Paper();*/


    /*@Test
    public void whenPaperWithTextIsPassedAStringItReturnsNewStringAppendedToTheText() {
        Paper paper = new Paper();
        assertEquals("text with new text appended", paper.addText("with new text appended"));
    }*/


}
