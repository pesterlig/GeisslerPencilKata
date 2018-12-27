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
                if (Character.isUpperCase(ch)) {
                    currentPointDurability -= 1;
                }
                visibleText += ch.toString();
                currentPointDurability -= 1;

                //Check for Whitespace Chars
                Pattern pattern = Pattern.compile("\\s");
                Matcher matcher = pattern.matcher(ch.toString());
                if (matcher.find()) {
                    getCurrentPointDurability();
                    currentPointDurability += 1;
                    setCurrentPointDurability(currentPointDurability);
                }

            } else {
                visibleText += " ";
            }
        }
        paper.setText(paper.getText() + visibleText);
    }


    public void erase(String textToErase, Paper paper) {
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
            String blankText = createBlankTextOfLength(backwardsTextToErase, lengthOfErasableText);
            //replace text to erase in the content
            String backwardsContentWithErasedText = backwardsContent.replaceFirst(backwardsTextToErase, blankText);

            //reverse the backwardsContentWithErasedText
            String forwardsContentWithErasedText = reverse(backwardsContentWithErasedText);
            //set the paper text now for testing purposes
            paper.setText(forwardsContentWithErasedText);
        }
    }


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

        //replace text to erase in the content
        String backwardsContentWithErasedText = backwardsContent.replaceFirst(backwardsTextToErase, partialBlankText);

        //reverse the backwardsContentWithErasedText
        String forwardsContentWithErasedText = reverse(backwardsContentWithErasedText);
        //set the paper text now for testing purposes
        paper.setText(forwardsContentWithErasedText);

        //reset currentEraserDurability to 0
        getCurrentEraserDurability();
        currentEraserDurability = 0;
        setCurrentEraserDurability(0);
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

    /*public int adjustCurrentPointDurabilityForWhitespaceCharacters(String visibleText){
        getCurrentPointDurability();
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(visibleText);

        while (matcher.find()) {
            currentPointDurability = currentPointDurability;
        }
        return currentPointDurability;
    }*/


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


