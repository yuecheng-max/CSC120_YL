import java.util.Scanner;

public class Iam {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("WEEK 10 - LAB 7 I am");
        System.out.println("Please enter sentences, . to end.");
        String result = "";

        while (true){
            String input = scanner.nextLine();

            if (input.equals(".")){
                break;
            }// end of the if statement

            if (input.startsWith("I am")){
                input = input.substring(5);
                result += input + ",";

            }// end of the if statement

        }// end of the while loop

        System.out.println("The qualities are " + result );

    }// end of the main method
}// end of the Iam class
