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

    public void write(String text, Paper paper){
        String visibleText;
        for(int i = 0; i < text.length(); i++){
            Character ch = text.charAt(i);
            if (currentPointDurability >= 1){
                 visibleText = ch.toString();
                currentPointDurability -= 1;
            } else {
                visibleText = " ";
            }
            paper.setText(visibleText);

        }

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
