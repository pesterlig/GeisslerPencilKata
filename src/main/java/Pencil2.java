import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Pencil2 {

    private int initialPointDurability;
    private int currentPointDurability;
    private int length;
    private int initialEraserDurability;
    private int currentEraserDurability;

    public Pencil2(int initialPointDurability, int length, int initialEraserDurability) {
        this.initialPointDurability = initialPointDurability;
        this.currentPointDurability = initialPointDurability;
        this.length = length;
        this.initialEraserDurability = initialEraserDurability;
        this.currentEraserDurability = initialEraserDurability;
    }

    //(overloaded constructor, used mainly for testing purposes)

    public Pencil2(int initialPointDurability, int currentPointDurability, int length, int initialEraserDurability, int currentEraserDurability) {
        this.initialPointDurability = initialPointDurability;
        this.currentPointDurability = currentPointDurability;
        this.length = length;
        this.initialEraserDurability = initialEraserDurability;
        this.currentEraserDurability = currentEraserDurability;
    }

    public void write(String text, Paper paper) {

        String visibleText = "";
        for (int i = 0; i < text.length(); i++) {
            Character ch = text.charAt(i);
            if (currentPointDurability >= 1) {
                visibleText += ch.toString();
                currentPointDurability -= 1;
            } else {
                visibleText += " ";
            }
        }
        paper.setText(paper.getText() + visibleText);
    }


    /*public int erase(String text, Paper paper) {
        int beginningIndexForErasableText = paper.getText().lastIndexOf(text);
        //char[] contentArray = content.toCharArray();
        int countErasableChars = 0;
        if (text.length() > currentEraserDurability) {
            countErasableChars = currentEraserDurability;
            char arrayOfReplacementBlankSpaces[] = new char[currentEraserDurability];
        } else {
            countErasableChars = text.length();
            char arrayOfReplacementBlankSpaces[] = new char[countErasableChars];
            Arrays.fill(arrayOfReplacementBlankSpaces, ' ');
            String erasedText = new String(arrayOfReplacementBlankSpaces);
            StringBuilder stringBuilder = new StringBuilder(content);
            stringBuilder.replace(beginningIndexForErasableText, text.length() + beginningIndexForErasableText, erasedText);
            paper.setText(stringBuilder.toString());
            currentEraserDurability -= countErasableChars;
            setCurrentEraserDurability(currentEraserDurability);
            *//*myWord is the original word sayAABDCAADEF. sourceWord is what you want to replace,
            say AA targetWord is what you want to replace it with say BB.
StringBuilder strb=new StringBuilder(myWord);
int index=strb.lastIndexOf(sourceWord);
strb.replace(index,sourceWord.length()+index,targetWord);
return strb.toString();
            *//*
        }
        return beginningIndexForErasableText;
    }*/

    public void erase(String textToErase, Paper paper) {
        //reverse the contents of the text on the paper
        String backwardsContent = reverse(paper.getText());
        //reverse the textToErase
        String backwardsTextToErase = reverse(textToErase);
        //Get the count of chars to replace


        //replace the textToErase with blank spaces
        String contentWithErasedText = backwardsContent.replaceFirst(Pattern.quote(backwardsTextToErase),)



    }

    private String createBlankTextOfProperLength(String text, int length){
        int count = 0;
        String blankText = "";
        for(int i = 0; i < length; i++){
            Character ch = text.charAt(i);
            String stringCharacter = ch.toString();
            stringCharacter.replace(ch, ' ');
            blankText += stringCharacter;
            count++;
        }
        return blankText;
    }

    private String reverse(String text){
        StringBuffer stringBufferContent = new StringBuffer(text);
        stringBufferContent = stringBufferContent.reverse();
        String reversed = stringBufferContent.toString();
        return reversed;
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
