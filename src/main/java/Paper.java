public class Paper {

    private String text;

    public Paper(){
        this.text = "";
    }


    public String addText(String handwrittenText) {
        text += handwrittenText;
        return text;
    }

    public Boolean isWrittenOn() {
        return (text != null) && (!text.isEmpty());
    }

}
