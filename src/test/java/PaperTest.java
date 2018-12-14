import org.junit.Test;

import static junit.framework.TestCase.*;

public class PaperTest {


    @Test
    public void whenPaperAddTextIsPassedAString_ItReturnsThatString() {
        Paper paper = new Paper();
        assertEquals("text", paper.addText("text"));
    }

    @Test
    public void paperIsWrittenOnReturnsTrueIfTextVariableIsHasValue() {
        Paper paper = new Paper();
        paper.addText("hello world");
        assertTrue(paper.isWrittenOn());

    }

    @Test
    public void paperIsWrittenOnReturnsFalseIfTextVariableIsNullOrEmpty(){
        Paper paper = new Paper();
        assertFalse(paper.isWrittenOn());
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
