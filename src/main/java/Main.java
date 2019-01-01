import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Paper paper = new Paper("");
        Pencil pencil = new Pencil(100, 10, 20);

        System.out.println("This program simulates a piece of paper and pencil of length " + pencil.getLength() + ", point durability "
                + pencil.getPointDurability() + ", and eraser durability " + pencil.getEraserDurability());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter some text for the pencil to write to the paper:");
        pencil.write(scanner.nextLine(), paper);

        System.out.println("On the paper, the pencil wrote:\n" + paper.getText());
        System.out.println("Current point durability is now: " + pencil.getPointDurability());

        System.out.println("Please enter text to erase:");
        String erased = scanner.nextLine();
        pencil.erase(erased, paper);

        System.out.println("After erasing from the paper, the text now reads:\n" + paper.getText());

        System.out.println("This pencil can edit.  Please enter text to replace your erased text:");
        String edited = scanner.nextLine();
        pencil.edit(edited, paper);
        System.out.println("After editing the paper, the text now reads:\n" + paper.getText());


    }

}
