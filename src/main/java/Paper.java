public class Paper {

    private String text;

    public Paper() {
        this.text = "";
    }

    public void setText(String text) {
        this.text += text;
    }


    public String getText() {
        return text;
    }
}
