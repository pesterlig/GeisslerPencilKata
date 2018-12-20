import java.lang.reflect.Array;

public class Pencil2 {

    private int initialPointDurability;
    private int currentPointDurability;
    private int length;
    private int initialEraserDurability;
    private int currentEraserDurability;

    public Pencil2(int initialPointDurability, int currentPointDurability, int length, int initialEraserDurability, int currentEraserDurability) {
        this.initialPointDurability = initialPointDurability;
        this.currentPointDurability = currentPointDurability;
        this.length = length;
        this.initialEraserDurability = initialEraserDurability;
        this.currentEraserDurability = currentEraserDurability;
    }

    public void write(String text, Paper paper) {
        String visibleText;
        for (int i = 0; i < text.length(); i++) {
            Character ch = text.charAt(i);
            if (currentPointDurability >= 1) {
                visibleText = ch.toString();
                currentPointDurability -= 1;
            } else {
                visibleText = " ";
            }
            paper.setText(visibleText);

        }
    }

    public void erase(String text, Paper paper) {
        char[] textArray = text.toCharArray();
        String content = paper.getText();
        char[] contentArray = content.toCharArray();
        int countErasableChars = 0;
        if (text.length() > currentEraserDurability) {
            countErasableChars = currentEraserDurability;
        } else {
            countErasableChars = text.length();
        }
        //traverse the array backwards looking for a pattern match & replace it with blanks
    }


    public void sharpen() {
        currentPointDurability = initialPointDurability;
        setCurrentPointDurability(currentPointDurability);
        length -= 1;
        setLength(length);
    }

    public int getInitialPointDurability() {
        return initialPointDurability;
    }

    public int getCurrentPointDurability() {
        return currentPointDurability;
    }

    public void setCurrentPointDurability(int currentPointDurability) {
        this.currentPointDurability = currentPointDurability;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCurrentEraserDurability() {
        return currentEraserDurability;
    }

    public void setCurrentEraserDurability(int currentEraserDurability) {
        this.currentEraserDurability = currentEraserDurability;
    }
}
