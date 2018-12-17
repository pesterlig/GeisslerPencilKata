import java.util.regex.Pattern;

public class Pencil {

    private int initialPointDurability;
    private int currentPointDurability;

    public Pencil(int initialPointDurability, int currentPointDurability) {
        this.initialPointDurability = initialPointDurability;
        this.currentPointDurability = currentPointDurability;
    }

    public void write(String content, Paper paper) {
        paper.setText(content);
    }


    //getters & setters

    public int getInitialPointDurability() {
        return initialPointDurability;
    }

    public void setInitialPointDurability(int initialPointDurability) {
        this.initialPointDurability = initialPointDurability;
    }

    public int getCurrentPointDurability() {
        return currentPointDurability;
    }


    /*public void setCurrentPointDurability(String text) {
        String textChars = text.replaceAll("\\s+", "");
        int upperCase = 0;

        for (int i = 0; i < textChars.length(); i++) {
            if (Character.isUpperCase(textChars.charAt(i))) {
                upperCase++;
            }
        }
        this.currentPointDurability = (initialPointDurability - (textChars.length() + upperCase));
    }*/

    public void setCurrentPointDurability(Character ch) {
        int currentPointDurability = getCurrentPointDurability();
        if ((currentPointDurability >= 2) && (Character.isUpperCase(ch))) {
            currentPointDurability -= 2;
        }
        this.currentPointDurability =  currentPointDurability;
    }

}
