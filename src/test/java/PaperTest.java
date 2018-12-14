import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class PaperTest {
    @Test
    public void whenPaperIsPassedAStringItReturnsThatString() {
        Paper paper = new Paper();
        assertEquals("text", paper.acceptText("text"));
    }


}
