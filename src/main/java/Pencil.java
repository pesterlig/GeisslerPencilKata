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

    public void setCurrentPointDurability(String text) {
        int count = 0;
        int upperCase = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                count++;
            }
            if (Character.isUpperCase(text.charAt(i))) {
                upperCase++;
            }
        }
        this.currentPointDurability = (initialPointDurability - (text.length() + upperCase) + count);
    }
}
