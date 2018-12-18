import java.util.regex.Pattern;

public class Pencil {

    private int initialPointDurability;
    private int currentPointDurability;

    public Pencil(int initialPointDurability, int currentPointDurability) {
        this.initialPointDurability = initialPointDurability;
        this.currentPointDurability = currentPointDurability;
    }

    public int countNonWhitespaceChars(String text) {

        int count = 0;
        for (int i = 0; i < text.length(); i++) {

            Character ch = text.charAt(i);
            if (!Character.isWhitespace(ch)) {
                count++;
            }
        }
        return count;
    }

    public int countWhitespaceChars(String text) {

        int count = 0;
        for (int i = 0; i < text.length(); i++) {

            Character ch = text.charAt(i);
            if (Character.isWhitespace(ch)) {
                count++;
            }
        }
        return count;
    }

    public int countUppercaseChars(String text) {

        int count = 0;
        for (int i = 0; i < text.length(); i++) {

            Character ch = text.charAt(i);
            if (Character.isUpperCase(ch)) {
                count++;
            }
        }
        return count;
    }


    public void write(String text, Paper paper) {

        for (int i = 0; i < text.length(); i++) {

            Character ch = text.charAt(i);

            if (currentPointDurability >= 2) {
                String visibleText = ch.toString();
                paper.setText(visibleText);
                setCurrentPointDurability(ch);
            }
            if ((currentPointDurability == 1) && ((Character.isWhitespace(ch)) || (!Character.isUpperCase(ch)))) {
                String visibleText = ch.toString();
                paper.setText(visibleText);
                setCurrentPointDurability(ch);
            }
            if ((currentPointDurability == 0) && ((Character.isWhitespace(ch)))) {
                String visibleText = ch.toString();
                paper.setText(visibleText);
                setCurrentPointDurability(ch);
            }
        }
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
        if ((currentPointDurability >= 1) && (!Character.isUpperCase(ch))) {
            currentPointDurability -= 1;
        }
        if ((currentPointDurability >= 1) && (Character.isWhitespace(ch))) {
            currentPointDurability -= 0;
        }

        this.currentPointDurability = currentPointDurability;
    }

}
