import java.util.Scanner;

/**
 * Student Grade Calculator
 * 
 * A console application that reads student marks across multiple subjects,
 * validates user inputs, calculates total marks, percentage, letter grade,
 * and pass/fail status, and prints a formatted report.
 */
public class StudentGradeCalculator {

    public static void main(String[] args) {
        // Initialize Scanner for reading user input from console
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("          STUDENT GRADE CALCULATOR                ");
        System.out.println("==================================================");

        // Control variable for repeating the calculation for another student
        boolean continueCalculating = true;

        // Outer while loop handles flow for multiple students
        while (continueCalculating) {
            // 1. Read student name (validated to ensure non-empty)
            String studentName = readNonEmptyString(scanner, "\nEnter Student Name: ");

            // 2. Read number of subjects (validated positive integer)
            int numSubjects = readPositiveInt(scanner, "Enter Number of Subjects: ");

            // 3. Arrays to store subject names and their corresponding marks
            // Demonstrates array declaration, instantiation, and parallel indexing
            String[] subjectNames = new String[numSubjects];
            int[] marks = new int[numSubjects];

            // 4. For loop to read subject names and marks with validation
            System.out.println("\nEnter marks for each subject (0 - 100):");
            for (int i = 0; i < numSubjects; i++) {
                String subjectPrompt = String.format("  Subject #%d Name (press Enter for 'Subject %d'): ", (i + 1), (i + 1));
                String nameInput = readLine(scanner, subjectPrompt).trim();
                if (nameInput.isEmpty()) {
                    subjectNames[i] = "Subject " + (i + 1);
                } else {
                    subjectNames[i] = nameInput;
                }

                // Read and validate marks for the current subject
                marks[i] = readValidMarks(scanner, "  Enter marks for " + subjectNames[i] + " [0-100]: ");
            }

            // 5. Perform calculations using modular methods
            int totalMarks = calculateTotal(marks);
            double percentage = calculatePercentage(totalMarks, numSubjects);
            String grade = calculateGrade(percentage);
            String result = calculateResult(marks, grade);

            // 6. Display a neat, formatted report
            displayReport(studentName, subjectNames, marks, totalMarks, percentage, grade, result);

            // 7. Ask user if they wish to process another student
            continueCalculating = askToContinue(scanner);
        }

        System.out.println("\nThank you for using the Student Grade Calculator! Goodbye.");
        scanner.close();
    }

    // ==========================================
    // CALCULATION METHODS
    // ==========================================

    /**
     * Calculates the total marks accumulated across all subjects.
     * Demonstrates enhanced for-loop (for-each) over an array.
     *
     * @param marks Array of integer marks
     * @return Sum of all marks
     */
    public static int calculateTotal(int[] marks) {
        int total = 0;
        // Enhanced for-loop iterates through each mark in the array
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    /**
     * Calculates the percentage from the total marks and number of subjects.
     *
     * @param total       Total marks scored
     * @param numSubjects Total number of subjects
     * @return Percentage as a double
     */
    public static double calculatePercentage(int total, int numSubjects) {
        if (numSubjects <= 0) {
            return 0.0;
        }
        // Explicit cast to double ensures floating-point division rather than integer division
        return (double) total / numSubjects;
    }

    /**
     * Determines the letter grade based on the overall percentage using if-else statements.
     * 
     * Grade Scale:
     * - 90 and above : A+
     * - 80 to 89.99  : A
     * - 70 to 79.99  : B
     * - 60 to 69.99  : C
     * - 50 to 59.99  : D
     * - 40 to 49.99  : E
     * - Below 40     : F (Fail)
     *
     * @param percentage Overall percentage
     * @return Letter grade as a String
     */
    public static String calculateGrade(double percentage) {
        // Conditionals: Evaluating grade tiers in descending order
        if (percentage >= 90.0) {
            return "A+";
        } else if (percentage >= 80.0) {
            return "A";
        } else if (percentage >= 70.0) {
            return "B";
        } else if (percentage >= 60.0) {
            return "C";
        } else if (percentage >= 50.0) {
            return "D";
        } else if (percentage >= 40.0) {
            return "E";
        } else {
            return "F";
        }
    }

    /**
     * Determines the overall Pass/Fail status.
     * Requirement: Fail if any individual subject mark is below 40 or overall grade is F.
     *
     * @param marks Array of individual subject marks
     * @param grade Overall calculated grade
     * @return "Pass" or "Fail"
     */
    public static String calculateResult(int[] marks, String grade) {
        // If overall grade is F, automatically Fail
        if ("F".equals(grade)) {
            return "Fail";
        }

        // Check if any individual subject is below passing threshold (40)
        for (int mark : marks) {
            if (mark < 40) {
                return "Fail";
            }
        }

        return "Pass";
    }

    // ==========================================
    // INPUT VALIDATION & HELPER METHODS
    // ==========================================

    /**
     * Reads a valid mark between 0 and 100 from the user.
     * Uses a while loop and try-catch to prevent crashing on invalid inputs (like letters or out-of-range values).
     *
     * @param scanner Scanner instance
     * @param prompt  User prompt message
     * @return Validated mark (0 to 100)
     */
    public static int readValidMarks(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int mark = Integer.parseInt(input);
                if (mark >= 0 && mark <= 100) {
                    return mark;
                } else {
                    System.out.println("    [Error] Marks must be between 0 and 100. You entered: " + mark);
                }
            } catch (NumberFormatException e) {
                System.out.println("    [Error] Invalid input! Marks must be a valid integer number. You entered: \"" + input + "\"");
            }
        }
    }

    /**
     * Reads a positive integer (> 0) from the user.
     * Validates input using a while loop and try-catch.
     *
     * @param scanner Scanner instance
     * @param prompt  User prompt message
     * @return Validated positive integer
     */
    public static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("    [Error] Number must be greater than 0. You entered: " + value);
                }
            } catch (NumberFormatException e) {
                System.out.println("    [Error] Invalid input! Please enter a positive integer. You entered: \"" + input + "\"");
            }
        }
    }

    /**
     * Reads a non-empty string from the user.
     *
     * @param scanner Scanner instance
     * @param prompt  User prompt message
     * @return Non-empty string
     */
    public static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("    [Error] Input cannot be blank. Please enter a valid name.");
        }
    }

    /**
     * Helper to read a line with prompt.
     *
     * @param scanner Scanner instance
     * @param prompt  User prompt message
     * @return Raw line input
     */
    public static String readLine(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Asks user if they want to calculate for another student.
     * Validates input until 'y' or 'n' is entered.
     *
     * @param scanner Scanner instance
     * @return true if user enters 'y' or 'yes', false if 'n' or 'no'
     */
    public static boolean askToContinue(Scanner scanner) {
        while (true) {
            System.out.print("\nCalculate for another student? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (choice.equals("y") || choice.equals("yes")) {
                return true;
            } else if (choice.equals("n") || choice.equals("no")) {
                return false;
            } else {
                System.out.println("    [Error] Please enter 'y' for yes or 'n' for no.");
            }
        }
    }

    // ==========================================
    // OUTPUT DISPLAY METHOD
    // ==========================================

    /**
     * Prints a neat, formatted report displaying student information,
     * subject-wise marks table, total, percentage, grade, and pass/fail result.
     */
    public static void displayReport(String studentName, String[] subjects, int[] marks,
                                     int totalMarks, double percentage, String grade, String result) {
        int maxMarks = subjects.length * 100;

        System.out.println();
        System.out.println("==================================================");
        System.out.println("               STUDENT REPORT CARD                ");
        System.out.println("==================================================");
        System.out.printf("Student Name  : %s%n", studentName);
        System.out.printf("Total Subjects: %d%n", subjects.length);
        System.out.println("--------------------------------------------------");
        System.out.printf("%-6s | %-24s | %-10s | %-6s%n", "No.", "Subject Name", "Marks", "Status");
        System.out.println("-------+--------------------------+------------+--------");

        for (int i = 0; i < subjects.length; i++) {
            String subjectStatus = (marks[i] >= 40) ? "Pass" : "Fail";
            System.out.printf("%-6d | %-24s | %3d / 100  | %-6s%n",
                    (i + 1),
                    truncateOrPad(subjects[i], 24),
                    marks[i],
                    subjectStatus);
        }

        System.out.println("==================================================");
        System.out.printf("Total Marks   : %d / %d%n", totalMarks, maxMarks);
        System.out.printf("Percentage    : %.2f%%%n", percentage);
        System.out.printf("Grade         : %s%n", grade);
        System.out.printf("Overall Result: %s%n", result);

        if (result.equals("Fail") && percentage >= 40.0) {
            System.out.println("Note          : Failed due to scoring below 40 in one or more subjects.");
        }
        System.out.println("==================================================");
    }

    /**
     * Helper to truncate long subject names or pad them for neat table alignment.
     */
    private static String truncateOrPad(String text, int maxLength) {
        if (text == null) {
            return "";
        }
        if (text.length() > maxLength) {
            return text.substring(0, maxLength - 3) + "...";
        }
        return text;
    }
}
