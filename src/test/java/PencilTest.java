import org.junit.Test;
import static junit.framework.TestCase.*;

public class PencilTest {
    @Test
    public void whenPencilWritesAString_ThenPaperSetsText(){
        Pencil pencil = new Pencil();
        Paper paper = new Paper();
        pencil.write("Merry Christmas", paper );
        assertEquals("Merry Christmas", "Merry Christmas");
    }
}
