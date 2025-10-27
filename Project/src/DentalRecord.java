import java.util.Scanner;

public class DentalRecord {

    private static final Scanner keyboard = new Scanner(System.in);

    //CONSTANTS
    private static final int MAX_FAMILY = 6;
    private static final int ROWS = 2;
    private static final int MAX_TEETH = 8;

    private static int[][] rowLengths;

    public static void main(String[] args) {
        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("--------------------------------------");

        int n;
        n = readIntInRange(
                "Please enter number of people in the family : ",
                "Invalid number of people, try again         : ",
                1, MAX_FAMILY);

        String[] names = new String[n];
        char[][][] teeth = new char[n][ROWS][MAX_TEETH];
        rowLengths = new int[n][ROWS];

        int p;
        for (p = 0; p < n; p++) {
            int r;
            for (r = 0; r < ROWS; r++) {
                int c;
                for (c = 0; c < MAX_TEETH; c++) {
                    teeth[p][r][c] = 'M';
                }// end of the third for loop
            }// end of the second for loop
        }// end of the first for loop

        int i;
        for (i = 0; i < n; i++) {
            names[i] = readNonEmpty("Please enter the name for family member " + (i + 1) + "   : ");

            String up = readTeethRow("Please enter the uppers for " + names[i] + "       : ");
            loadRow(teeth[i][0], up);
            rowLengths[i][0] = up.length();

            String lo = readTeethRow("Please enter the lowers for " + names[i] + "       : ");
            loadRow(teeth[i][1], lo);
            rowLengths[i][1] = lo.length();
        }// end of the for loop


        label:
        while (true) {
            String choice = readMenu("(P)rint, (E)xtract, (R)oot, e(X)it          : ",
                    "Invalid menu option, try again              : ");
            switch (choice) {
                case "P":
                    printFamily(names, teeth);
                    break;
                case "E":
                    doExtract(names, teeth);
                    break;
                case "R":
                    printRoots(teeth);
                    break;
                default:  // X
                    System.out.println();
                    System.out.println("Exiting the Floridian Tooth Records :-)");
                    break label;
            }// end of switch statement
        }// end of the while loop
    }// end of the main method

    private static int readIntInRange(String firstPrompt, String retryPrompt, int min, int max) {
        System.out.print(firstPrompt);
        int v = keyboard.nextInt();
        keyboard.nextLine();
        while (v < min || v > max) {
            System.out.print(retryPrompt);
            v = keyboard.nextInt();
            keyboard.nextLine();
        }// end of the while loop
        return v;
    }// end of the readIntInRange method

    private static String readNonEmpty(String prompt) {
        System.out.print(prompt);
        String s = keyboard.nextLine().trim();
        while (s.length() == 0) {
            System.out.print(prompt);
            s = keyboard.nextLine().trim();
        }// end of the while loop
        return s;
    }// end of the readNonEmpty method

    private static boolean isIBM(String s) {
        int i;
        for (i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != 'I' && c != 'B' && c != 'M') return false;
        }// end of the for loop
        return true;
    }// end of the isIBM method

    private static String readTeethRow(String firstPrompt) {
        System.out.print(firstPrompt);
        String s = keyboard.nextLine().trim().toUpperCase();

        while (!isIBM(s)) {
            System.out.print("Invalid teeth types, try again              : ");
            s = keyboard.nextLine().trim().toUpperCase();
        }// end of the while loop

        while (s.length() > MAX_TEETH) {
            System.out.print("Too many teeth, try again                   : ");
            s = keyboard.nextLine().trim().toUpperCase();
            while (!isIBM(s)) {
                System.out.print("Invalid teeth types, try again              : ");
                s = keyboard.nextLine().trim().toUpperCase();
            }// end of the internal while loop
        }// end of the external while loop
        return s;
    }// end of the readTeethRow method

    private static void loadRow(char[] row, String s) {
        int i;
        for (i = 0; i < MAX_TEETH; i++) {
            row[i] = 'M';
        }// end of the for loop
        for (i = 0; i < s.length(); i++) {
            row[i] = s.charAt(i);
        }// end of the for loop
    }// end of the loadRow method

    private static String readMenu(String firstPrompt, String retryPrompt) {
        System.out.print(firstPrompt);
        String s = keyboard.nextLine().trim().toUpperCase();
        while (!(s.equals("P") || s.equals("E") || s.equals("R") || s.equals("X"))) {
            System.out.print(retryPrompt);
            s = keyboard.nextLine().trim().toUpperCase();
        }// end of the while loop
        return s;
    }// end of the readMenu method

    private static void printFamily(String[] names, char[][][] teeth) {
        System.out.println();
        int p;
        for (p = 0; p < names.length; p++) {
            System.out.println(names[p]);
            System.out.print("  Uppers: ");
            printRow(teeth[p][0], rowLengths[p][0]);
            System.out.print("  Lowers: ");
            printRow(teeth[p][1], rowLengths[p][1]);
        }// end of the int loop
        System.out.println();
    }// end of the printFamily method

    private static void printRow(char[] row, int enteredLen) {
        if (enteredLen < 1) {
            enteredLen = 1;
            System.out.print(" ");
        }// end of the if statement
        int i;
        for (i = 0; i < enteredLen; i++) {
            if (i > 0) System.out.print("  ");
            System.out.print((i + 1) + ":" + row[i]);
        }// end of the while loop
        System.out.println();
    }// end of the printRow method

    private static void doExtract(String[] names, char[][][] teeth) {
        int person = -1;
        System.out.print("Which family member                         : ");
        String who = keyboard.nextLine().trim();
        person = findPerson(names, who);
        while (person == -1) {
            System.out.print("Invalid family member, try again            : ");
            who = keyboard.nextLine().trim();
            person = findPerson(names, who);
        }// end of the while loop

        int row = -1;
        System.out.print("Which tooth layer (U)pper or (L)ower        : ");
        String layer = keyboard.nextLine().trim().toUpperCase();
        row = toRow(layer);
        while (row == -1) {
            System.out.print("Invalid layer, try again                    : ");
            layer = keyboard.nextLine().trim().toUpperCase();
            row = toRow(layer);
        }// end of the while loop

        int maxLen = rowLengths[person][row];
        System.out.print("Which tooth number                          : ");
        int pos = keyboard.nextInt(); keyboard.nextLine();
        while (pos < 1 || pos > MAX_TEETH || pos > maxLen) {
            System.out.print("Invalid tooth number, try again             : ");
            pos = keyboard.nextInt(); keyboard.nextLine();
        }// end of the while loop

        int idx = pos - 1;

        while (teeth[person][row][idx] == 'M') {
            System.out.print("Missing tooth, try again                    : ");
            pos = keyboard.nextInt(); keyboard.nextLine();
            while (pos < 1 || pos > MAX_TEETH || pos > rowLengths[person][row]) {
                System.out.print("Invalid tooth number, try again             : ");
                pos = keyboard.nextInt(); keyboard.nextLine();
            }// end of the internal while loop
            idx = pos - 1;
        }// end of the external while loop

        teeth[person][row][idx] = 'M';
    }// end of the doExtract method

    private static int findPerson(String[] names, String who) {
        int i;
        for (i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(who)) return i;
        }// end of the for loop
        return -1;
    }// end of the findPerson method

    private static int toRow(String s) {
        if (s.equals("U") || s.equals("UPPER") || s.equals("UPPERS")) return 0;
        if (s.equals("L") || s.equals("LOWER") || s.equals("LOWERS")) return 1;
        return -1;
    }// end of the toRow method

    private static void printRoots(char[][][] teeth) {
        int I = 0, B = 0, M = 0;
        int p;

        for (p = 0; p < teeth.length; p++) {
            int r;
            for (r = 0; r < ROWS; r++) {
                int len = rowLengths[p][r];
                int c;
                for (c = 0; c < len; c++) {
                    char t = teeth[p][r][c];
                    if (t == 'I') I++;
                    else if (t == 'B') B++;
                    else if (t == 'M') M++;
                }// end of the third for loop
            }// end of the second for loop
        }// end of the first for loop

        if (I == 0) {
            if (B == 0) {
                System.out.printf("One root canal at %8.2f%n", 0.0);
                System.out.printf("Another root canal at %8.2f%n", 0.0);
            } else {
                double x = (double) M / (double) B;
                System.out.printf("One root canal at %8.2f%n", x);
                System.out.printf("Another root canal at %8.2f%n", x);
            }// end of the internal if statement
            return;
        }// end of the external if statement

        double a = I, b = B, c = -M;
        double disc = b * b - 4 * a * c;
        if (disc < 0) {
            System.out.printf("One root canal at %8.2f%n", 0.0);
            System.out.printf("Another root canal at %8.2f%n", 0.0);
            return;
        }// end of the if statement
        double sqrtD = Math.sqrt(disc);
        double x1 = (-b + sqrtD) / (2 * a);
        double x2 = (-b - sqrtD) / (2 * a);
        System.out.printf("One root canal at %8.2f%n", x1);
        System.out.printf("Another root canal at %8.2f%n", x2);
    }// end of the printRoots method
}// end of the DentalRecord class