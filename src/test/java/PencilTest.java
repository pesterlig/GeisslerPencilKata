import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class PencilTest {

    Pencil pencil;
    Paper paper;

    @Before
    public void setUp() {
        pencil = new Pencil(20,20);
        paper = new Paper();
    }

    @Test
    public void whenPencilWritesAString_thenPaperSetsText() {
        pencil.write("blah", paper);
        assertEquals("blah", "blah");
    }

    @Test
    public void whenPencilWriteSetsTextOnPaper_thenGetTextReturnsTheTextValueAndAddsNewTextToIt() {
        paper.setText("blah");
        pencil.write("blah", paper);
        assertEquals("blahblah", paper.getText());
    }

    @Test
    public void whenPencilWrites_thenDecrementItsCurrentPointDurability() {
        String text = "blah blah";
        pencil.write(text, paper);
        pencil.setInitialPointDurability(20);
        pencil.setCurrentPointDurability(text);
        assertEquals(pencil.getCurrentPointDurability(), 11 );
    }
}
