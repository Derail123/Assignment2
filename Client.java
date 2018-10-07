import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Client {
  public static void main(String[] args) {
    try {
      Socket echoSocket= new Socket("localhost", 1026);
      PrintStream out= new PrintStream(echoSocket.getOutputStream());
      Scanner scan= new Scanner(System.in);

      String userInput;
      String echoServerInput;

      Supplier<String> scanInput = () -> scan.next();

      System.out.print("Enter text: ");
      Stream.generate(scanInput)
      .map(s -> {
        System.out.println(s);
        System.out.println("Server response: " + s);
        if(!"quit".equalsIgnoreCase(s)){
          System.out.println("Enter text: ");
        }
        return s;
      })
      .allMatch(s -> !"quit".equalsIgnoreCase(s));
    } catch (Exception e) {
        e.printStackTrace();
    } //end of try-catch
  }//end of main
} //end of class
