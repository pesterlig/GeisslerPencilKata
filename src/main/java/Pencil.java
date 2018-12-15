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
        this.currentPointDurability = (initialPointDurability - text.length());
    }
}
