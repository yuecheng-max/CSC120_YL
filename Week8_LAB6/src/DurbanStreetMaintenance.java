import java.util.Scanner;

public class DurbanStreetMaintenance {

    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("WEEK 8 - LAB 6 ");



    }// end of the main method

    private static void inputHouseNumber(int [] houseNumberArray) {

        int index;
        int houseNumber;
        for (index = 0; index < houseNumberArray.length; index++) {
            System.out.println("How many people live in number " +  index + "?");
            houseNumber = keyboard.nextInt();
            houseNumberArray[index] = houseNumber;
        }// end of the for loop

    }// end of the inputHouseNumber method

    private static void inputHouseAge(int [] houseNumberArray, int [][] houseAgeArray) {

        int personIndex;
        int houseAge;
        int ageOfPerson;
        for (personIndex = 0; personIndex < houseNumberArray.length; personIndex++) {
            System.out.println("What is the age of person " +  personIndex + "?");
            houseAge = keyboard.nextInt();
            houseNumberArray[personIndex] = houseAge;

            for ( ageOfPerson = 0; ageOfPerson < houseAgeArray[personIndex].length; ageOfPerson++) {
                houseAgeArray[personIndex][ageOfPerson] = keyboard.nextInt();
            }// end of the inner for loop

        }// end of the outer for loop

    }// end of the inputHouseAge method

}// end of the DurbanStreetMaintenance class
