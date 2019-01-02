import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Paper paper = new Paper("");
        Pencil pencil = new Pencil(100, 10, 20);
        Scanner input = new Scanner(System.in);

        System.out.println("This program simulates a piece of paper and pencil of length " + pencil.getLength() + ", point durability "
                + pencil.getPointDurability() + ", and eraser durability " + pencil.getEraserDurability());

        String choice = "0";
        //Demo Loop
        while (!choice.equalsIgnoreCase("q") || choice.equalsIgnoreCase("0")) {
            displayPencilDemoMenu();
            choice = input.nextLine();
            if (choice.equalsIgnoreCase("w")) {
                System.out.println("Please enter some text for the pencil to write to the paper:");
                String textToWrite = input.nextLine();
                pencil.write(textToWrite, paper);
                System.out.println("On the paper, the pencil wrote:\n" + paper.getText());
                System.out.println("Current point durability is now: " + pencil.getPointDurability());
            } else if (choice.equalsIgnoreCase("e")) {
                System.out.println("Please enter text to erase:");
                String erased = input.nextLine();
                pencil.erase(erased, paper);
                System.out.println("After erasing " + erased + " from the paper, the text now reads:\n" + paper.getText());
                System.out.println("Current eraser durability is now: " + pencil.getEraserDurability());
            } else if (choice.equalsIgnoreCase("r")) {
                System.out.println("This pencil can edit.  Please enter text to replace your erased text:");
                String edited = input.nextLine();
                pencil.edit(edited, paper);
                System.out.println("After editing the paper, the text now reads:\n" + paper.getText());
                System.out.println("Current point durability is now: " + pencil.getPointDurability() + " and eraser durability is now: " + pencil.getEraserDurability());
            } else if (choice.equalsIgnoreCase("s")) {
                pencil.sharpen();
                System.out.println("Current point durability is now: " + pencil.getPointDurability() + " and pencil length is: " + pencil.getLength());
            } else if (choice.contains("q")) {
                System.out.println("Pencil was happy to serve you!");
                System.out.println("Good bye.");
                System.exit(0);
            } else
                System.out.println("That was not a valid choice");
        }
    }

    public static void displayPencilDemoMenu() {
        System.out.println("");
        System.out.println("What would you like to do?");
        System.out.println(" W = Write");
        System.out.println(" E = Erase");
        System.out.println(" R = Replace erased text with new text");
        System.out.println(" S = Sharpen your pencil");
        System.out.println(" Leave the Pencil Durability Demo (Type q to quit)");
    }


}






