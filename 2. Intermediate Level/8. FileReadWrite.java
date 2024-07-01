import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileReadWrite {
    public static void main(String[] args) {
        String fileName = "example.txt";
        String content = "Hello, World!\nThis is a sample text file.";

        // Write to file
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            writer.write(content);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        // Read from file
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
