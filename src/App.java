import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        var scanner = new Scanner(System.in);
        System.out.print("Enter path to Python file: ");
        var filePath = scanner.nextLine();
        scanner.close();
        var all = FileReader.Read(filePath);
        var parser = new Parser();
        var lexems = parser.Parse(all);
        var output = "";
        for (var lexem : lexems) {
            output+=(lexem.Notation + " - " + lexem.LexemType+"\n");
        }
        FileReader.Write(output);
    }
}
