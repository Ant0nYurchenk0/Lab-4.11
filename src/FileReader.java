import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {
  public static String Read(String path){
    try {
      File file = new File(path);
      Scanner reader = new Scanner(file);
      var fileData = new StringBuilder();
      while (reader.hasNextLine()) {
        var newLine = "";
        if (fileData.length() != 0){        
          newLine="\n";
        }
        fileData.append(newLine+reader.nextLine());
      }
      reader.close();
      return fileData.toString();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred: file not found");
      e.printStackTrace();
      System.exit(1);
      return null;
    }
  } 
  public static void Write(String string){
    try {
			FileOutputStream file = new FileOutputStream(new File(Constants.ResultPath));
			file.write(string.getBytes());
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred: file not found");
      e.printStackTrace();
      System.exit(1);
		} catch (IOException e) {
      e.printStackTrace();
    }
  }
}
