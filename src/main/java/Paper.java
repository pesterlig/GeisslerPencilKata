import java.util.ArrayList;

public class Paper {

    private String text;
    private ArrayList<Integer>erasureIndices;



    public Paper(String text) {
        this.text = text;
        this.erasureIndices = new ArrayList<>();
    }

    public ArrayList<Integer> getErasureIndices() {
        return erasureIndices;
    }

    public void setErasureIndices(ArrayList<Integer> erasureIndices) {
        this.erasureIndices = erasureIndices;
    }



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
