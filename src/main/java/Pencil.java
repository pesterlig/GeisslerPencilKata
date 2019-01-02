import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pencil {

    private int initialPointDurability;
    private int pointDurability;
    private int length;
    private int initialEraserDurability;
    private int eraserDurability;

    public Pencil(int initialPointDurability, int length, int initialEraserDurability) {
        this.initialPointDurability = initialPointDurability;
        this.pointDurability = initialPointDurability;
        this.length = length;
        this.initialEraserDurability = initialEraserDurability;
        this.eraserDurability = initialEraserDurability;
    }

    public Pencil(int initialPointDurability, int PointDurability, int length, int initialEraserDurability, int EraserDurability) {
        this.initialPointDurability = initialPointDurability;
        this.pointDurability = PointDurability;
        this.length = length;
        this.initialEraserDurability = initialEraserDurability;
        this.eraserDurability = EraserDurability;
    }

    public void write(String text, Paper paper) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < text.length(); i++) {
            writeCharacter(stringBuilder, text.charAt(i));
        }
        paper.setText(paper.getText() + stringBuilder);
    }

    public void writeCharacter(StringBuilder stringBuilder, Character ch) {
        if (pointDurability >= 1) {
            adjustPointDurabilityForUppercase(ch);
            stringBuilder.append(ch);
            pointDurability -= 1;
            adjustPointDurabilityForWhitespaceCharacters(ch);
        } else {
            stringBuilder.append(' ');
        }
    }

    public void adjustPointDurabilityForUppercase(Character ch) {
        int pointDurability = getPointDurability();
        if (Character.isUpperCase(ch)) {
            pointDurability -= 1;
            setPointDurability(pointDurability);
        }
    }

    public void adjustPointDurabilityForWhitespaceCharacters(Character ch) {
        int pointDurability = getPointDurability();
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(ch.toString());
        if (matcher.find()) {
            pointDurability += 1;
            setPointDurability(pointDurability);
        }
    }

    public void erase(String textToErase, Paper paper) {
        int indexOfErasableText = findIndexOfLastOccurrenceOfErasableText(textToErase, paper);
        paper.getErasureIndices().add(indexOfErasableText);
        //calculate how much text to replace based on current Eraser Durability
        if (textToErase.length() > getEraserDurability()) {
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

    public int findIndexOfLastOccurrenceOfErasableText(String textToErase, Paper paper) {
        int currentEraserDurability = getEraserDurability();
        int indexOfErasableText = 0;
        if (currentEraserDurability < textToErase.length()) {
            indexOfErasableText = paper.getText().lastIndexOf(textToErase);
            indexOfErasableText += (textToErase.length() - currentEraserDurability);
        } else {
            indexOfErasableText = paper.getText().lastIndexOf(textToErase);
        }
        return indexOfErasableText;
    }


    public void erasePartial(String textToErase, Paper paper) {
        //reverse the contents of the text on the paper
        String backwardsContent = reverse(paper.getText());
        //reverse the textToErase
        String backwardsTextToErase = reverse(textToErase);

        //create a substring which is the end of the backwards textToErase that is unchanged
        String unchangedReversedTextSubstring = backwardsTextToErase.substring(eraserDurability, textToErase.length());

        //create an array of blank spaces of the same length as eraserDurability
        char[] blanksArray = new char[eraserDurability];
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

        //reset eraserDurability to 0
        getEraserDurability();
        eraserDurability = 0;
        setEraserDurability(0);
    }

    public String reverse(String text) {
        StringBuffer stringBufferContent = new StringBuffer(text);
        stringBufferContent = stringBufferContent.reverse();
        String reversed = stringBufferContent.toString();
        return reversed;
    }

    public int calculateLengthOfErasableText(String textToErase) {
        int lengthOfErasableText = 0;
        if (getEraserDurability() < textToErase.length()) {
            lengthOfErasableText = eraserDurability;
        } else {
            lengthOfErasableText = textToErase.length();
        }
        return lengthOfErasableText;
    }

    public String createBlankTextOfLength(String textToErase, int lengthOfErasableText) {
        int count = 0;
        int whitespaceCount = countWhitespaceChars(textToErase);
        String blankText = "";
        eraserDurability = getEraserDurability();

        for (int i = 0; i < lengthOfErasableText; i++) {
            Character ch = textToErase.charAt(i);
            String stringCharacter = ch.toString();
            String blankSpace = stringCharacter.replace(stringCharacter, " ");
            blankText += blankSpace;
            eraserDurability -= 1;
            count++;
        }
        eraserDurability += whitespaceCount;
        setEraserDurability(eraserDurability);
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

    public void edit(String replacementText, Paper paper) {
        int indexOfLastElementInErasureIndices = paper.getErasureIndices().size() - 1;
        int indexOfLastErasure = paper.getErasureIndices().get(indexOfLastElementInErasureIndices);

        int lengthFromIndexOfLastErasureToEndOfMessage = paper.getText().substring(indexOfLastErasure).length();
        int lengthOfExtraReplacementTextToAddIfNeeded = (replacementText.length() - lengthFromIndexOfLastErasureToEndOfMessage);

        if (lengthOfExtraReplacementTextToAddIfNeeded > 0) {
            writeBlanks(lengthOfExtraReplacementTextToAddIfNeeded, paper);
        }

        String textFirstPart = paper.getText().substring(0, (indexOfLastErasure));
        String textLastPart = paper.getText().substring((indexOfLastErasure + replacementText.length()));

        String entireEditedText = "";

        String editedText = "";
        for (int i = 0; i < replacementText.length(); i++) {
            Character ch = replacementText.charAt(i);

            if (pointDurability >= 1) {
                if (Character.isUpperCase(ch)) {
                    pointDurability -= 1;
                }
                Character characterOfOriginalText = (paper.getText().charAt(indexOfLastErasure + i));
                String stringCharacterOfOriginalText = characterOfOriginalText.toString();
                Pattern pattern = Pattern.compile("\\S");
                Matcher matcher = pattern.matcher(stringCharacterOfOriginalText);
                if (matcher.find()) {
                    editedText += "@";
                    pointDurability -= 1;
                } else {
                    editedText += ch.toString();
                    pointDurability -= 1;
                }
                adjustPointDurabilityForWhitespaceCharacters(ch);
                /*//Check for Whitespace Chars
                Matcher nextMatcher = pattern.matcher(replacementText);
                if (!nextMatcher.find()) {
                    pointDurability += 1;
                }*/
            } else {
                editedText += " ";
            }
            String firstHalfEntireEditedText = textFirstPart.concat(editedText);

            entireEditedText = firstHalfEntireEditedText.concat(textLastPart);
        }
        setPointDurability(pointDurability);
        paper.setText(entireEditedText);
    }

    public void writeBlanks(int numBlanks, Paper paper) {
        String writtenText = "";
        for (int i = 0; i < numBlanks; i++) {
            writtenText += " ";
        }
        paper.setText(paper.getText() + writtenText);
    }

    public void sharpen() {
        pointDurability = initialPointDurability;
        setPointDurability(pointDurability);
        length -= 1;
        setLength(length);
    }

    //Getters & Setters

    public int getInitialPointDurability() {
        return initialPointDurability;
    }

    public int getPointDurability() {
        return pointDurability;
    }

    public void setPointDurability(int pointDurability) {
        this.pointDurability = pointDurability;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getEraserDurability() {
        return eraserDurability;
    }

    public void setEraserDurability(int eraserDurability) {
        this.eraserDurability = eraserDurability;
    }
}


