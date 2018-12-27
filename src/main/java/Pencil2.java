import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Matcher;
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
        //boolean concatenationNeeded = false;


        //calculate how much text to replace based on current Eraser Durability
        if (textToErase.length() > getCurrentEraserDurability()) {
            erasePartial(textToErase, paper);

        } else {
            //reverse the contents of the text on the paper
            String backwardsContent = reverse(paper.getText());
            //reverse the textToErase
            String backwardsTextToErase = reverse(textToErase);
            int lengthOfErasableText = calculateLengthOfErasableText(backwardsTextToErase);
            //Replace the chars with blank spaces and count how many are replaced, reset eraser durability
            String blankText = createBlankTextOfLength(backwardsTextToErase, lengthOfErasableText); //will have to give this another length if durability<text.length

            //if eraser durability < textToErase.length, must somehow split up the replaced text and the text to Erase, calculate a substring of remaining characters, and append them to the blanktext

            //replace text to erase in the content
            String backwardsContentWithErasedText = backwardsContent.replaceFirst(backwardsTextToErase, blankText);


            //reverse the backwardsContentWithErasedText
            String forwardsContentWithErasedText = reverse(backwardsContentWithErasedText);
            //set the paper text now for testing purposes
            paper.setText(forwardsContentWithErasedText);
        }


    }

    /*
    Ideas - 1.) When erasing ALL the instances of the textToErase, have conditional - if find text, run erase method again - no - this is not needed
    2.) How to deal with partial erasing...
        a. If text is longer than durability, have to create a new word to put in place of the text which is a combo of some text & some blanks

    */

    public void erasePartial(String textToErase, Paper paper) {
        //reverse the contents of the text on the paper
        String backwardsContent = reverse(paper.getText());
        //reverse the textToErase
        String backwardsTextToErase = reverse(textToErase);

        //create a substring which is the end of the backwards textToErase that is unchanged
        String unchangedReversedTextSubstring = backwardsTextToErase.substring(currentEraserDurability, textToErase.length());

        //create an array of blank spaces of the same length as currentEraserDurability
        char[] blanksArray = new char[currentEraserDurability];
        //fill array with blank chars
        Arrays.fill(blanksArray, ' ');
        //convert array to a string
        String blanksToSubForPartialText = new String(blanksArray);
        //create a string of blanksToSubForPartialTest + unchangedReversedTextSubstring called partialBlankText
        String partialBlankText = blanksToSubForPartialText.concat(unchangedReversedTextSubstring);


        /*String replacementOfBackwardsTextToErase = "";
        if (concatenationNeeded) {
            String remainderOfBackwardsTextToErase = backwardsTextToErase.substring(((backwardsTextToErase.length() - lengthOfErasableText) - 1));
            replacementOfBackwardsTextToErase = blankText + remainderOfBackwardsTextToErase;
        } else {
            replacementOfBackwardsTextToErase = blankText;
        }*/

        paper.setText(partialBlankText);


        //return unchangedReversedTextSubstring; //if textToErase = "Blah, Durability = 3, should return "B" - write test to check


    }

    public int calculateLengthOfErasableText(String textToErase) {
        int lengthOfErasableText = 0;
        if (getCurrentEraserDurability() < textToErase.length()) {
            lengthOfErasableText = currentEraserDurability;
        } else {
            lengthOfErasableText = textToErase.length();
        }

        return lengthOfErasableText;
    }

    public String createBlankTextOfLength(String textToErase, int lengthOfErasableText) {
        int count = 0;
        int whitespaceCount = countWhitespaceChars(textToErase);
        String blankText = "";
        currentEraserDurability = getCurrentEraserDurability();

       /*
            textToErase = textToErase.substring(startIndex);
        }
        else {textToErase = textToErase;}*/

        for (int i = 0; i < lengthOfErasableText; i++) {
            Character ch = textToErase.charAt(i);
            String stringCharacter = ch.toString();
            String blankSpace = stringCharacter.replace(stringCharacter, " ");
            blankText += blankSpace;
            currentEraserDurability -= 1;
            count++;
        }
        currentEraserDurability += whitespaceCount;

        setCurrentEraserDurability(currentEraserDurability);
        return blankText;
    }

    public int countWhitespaceChars(String text) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(text);
        int whitespaceCount = 0;
        while (matcher.find()) {
            whitespaceCount++;
        }
        return whitespaceCount;
    }

    //Below: test method to return count as int

    /*public int createBlankTextOfLength(String text, int length) {
        int count = 0;
        String blankText = "";
        currentEraserDurability = getCurrentEraserDurability();
        for (int i = 0; i < length; i++) {
            Character ch = text.charAt(i);
            String stringCharacter = ch.toString();
            String blankSpace = stringCharacter.replace(stringCharacter, " ");
            blankText += blankSpace;
            currentEraserDurability -= 1;
            count++;
        }

        setCurrentEraserDurability(currentEraserDurability);
        return count;
    }*/


    public String reverse(String text) {
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


