import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// todo ensure constructors are first methods on your class, and if you have overloaded constructors,
//      order them by number of arguments (more args go lower down)
//      Why? The first thing someone wants to see when they open your class is how to make it!
//      Constructors with the fewest parameters give them the bare minimum info about your class to get going
// todo move all getters and setters to the bottom of the file, below methods
//      Why? They don't contain the core behavior of the class, they are mostly for convention, so they create extra noise throughout your class
//      Put that noise at the bottom where it's out of the way!
// todo find "like logic", or duplicated code blocks, and shunt them out to methods, to be called by the things that they were previously in
//      EXCEPTION: Don't do this step for constructor methods: A clean constructor abides by the idea that the instance of the class does not exist
//      until it's done being called, so avoid calling other methods of the class from within the constructor
// todo clean up long argument names! these can just be "pointDurability" and "eraserDurability"
//      Why? you can think of methods as having their own scope, and you only need to be specific enough to not mix things up within that scope

// todo if a block of code is long, it is generally doing a complex task. If you can describe that task succinctly,
//  make a method with that description as the name
// then call that method instead of doing the code block directly. This helps keep the main logic readable, because it breaks it out into steps

// LONG EXAMPLE the logic in this if statement loop is doing what?

// it's writing a character.

   /*  // todo this process can be repeated over and over to create smaller more specific methods!
    private Character writeCharacter(Character ch){

        if (pointDurability >= 1) {
            if (Character.isUpperCase(ch)) {
                pointDurability -= 1;
            }
            writtenCharacter = ch.toString();
            pointDurability -= 1;

            //Check for Whitespace Chars
            Pattern pattern = Pattern.compile("\\s");
            Matcher matcher = pattern.matcher(ch.toString());
            if (matcher.find()) {
                getPointDurability();
                pointDurability += 1;
                setPointDurability(pointDurability);
            }
        } else {
            writtenCharacter = " ";
        }
        return writtenCharacter;
    }
    */

// then the method becomes
    /*
    public void write(String text, Paper paper) {
        String writtenText = "";
        for (int i = 0; i < text.length(); i++) {
            writtenText += writeCharacter(text.charAt(i));
        }
        paper.setText(paper.getText() + writtenText);
    }
    Which is much more readable
    * */

/*
 * When you do this, always put the methods in the order that they are called by the code, so as you read down the page,
 * you see where each one is being used before you read through it, giving you context on the usage
 * */


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
        String writtenText = "";
        for (int i = 0; i < text.length(); i++) {

            Character ch = text.charAt(i);

            if (pointDurability >= 1) {
                if (Character.isUpperCase(ch)) {
                    pointDurability -= 1;
                }
                writtenText += ch.toString();
                pointDurability -= 1;

                adjustPointDurabilityForWhitespaceCharacters(ch);

                //Check for Whitespace Chars
                /*Pattern pattern = Pattern.compile("\\s");
                Matcher matcher = pattern.matcher(ch.toString());
                if (matcher.find()) {
                    getPointDurability();
                    pointDurability += 1;
                    setPointDurability(pointDurability);
                }*/
            } else {
                writtenText += " ";
            }
        }
        paper.setText(paper.getText() + writtenText);
    }

    public void adjustPointDurabilityForWhitespaceCharacters(Character ch){
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(ch.toString());
        if (matcher.find()) {
            getPointDurability();
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


