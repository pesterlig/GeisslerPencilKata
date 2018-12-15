import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class PencilTest {

    Pencil pencil;
    Paper paper;

    @Before
    public void setUp(){
        pencil = new Pencil();
        paper = new Paper();
    }
    @Test
    public void whenPencilWritesAString_ThenPaperSetsText() {
        pencil.write("blah", paper);
        assertEquals("blah", "blah");
    }

    @Test
    public void whenPencilWriteSetsTextOnPaper_ThenGetTextReturnsTheTextValueAndAddsNewTextToIt() {
        paper.setText("blah");
        pencil.write("blah", paper);
        assertEquals("blahblah", paper.getText());
    }
}
