import org.junit.Test;

public class PaperTest {
    @Test
    public void whenPaperIsPassedAStringItReturnsThatString() {
        Paper paper = new Paper();
        assertEquals("written text", paper.acceptText("written text"));
    }


}
