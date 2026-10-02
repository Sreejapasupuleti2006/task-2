# Student Grade Calculator

A robust, console-based Java application that allows teachers, students, and administrators to enter and evaluate subject marks, calculate total marks, percentage, assign letter grades based on defined grading bands, and determine overall pass/fail status with an elegant report card format.

---

## 📋 Features

- **Dynamic Subject Input**: Handles any positive number of subjects entered by the user. Custom subject names can be entered or default to `"Subject 1"`, `"Subject 2"`, etc.
- **Fail-Safe Input Validation**:
  - Marks must be integers in the range `[0, 100]`.
  - Input with non-numeric characters (e.g., `"abc"`, symbols) or out-of-range values (`-5`, `105`) triggers clear error prompts without crashing the program.
  - Number of subjects must be a positive integer (`> 0`).
  - Student names cannot be left blank.
- **Comprehensive Grade Logic**:
  - `90% and above`: **A+**
  - `80% - 89.99%`: **A**
  - `70% - 79.99%`: **B**
  - `60% - 69.99%`: **C**
  - `50% - 59.99%`: **D**
  - `40% - 49.99%`: **E**
  - `Below 40%`: **F** (Fail)
- **Subject-Wise Pass/Fail Check**: A student only passes overall if they score $\ge 40$ in **every** individual subject and attain an overall grade above `F`.
- **Formatted Report Card**: Prints a clean ASCII table with subject names, marks, individual pass/fail statuses, total marks, percentage (formatted to 2 decimal places), grade, and overall result.
- **Multi-Student Processing**: Loops with a prompt asking `Calculate for another student? (y/n)` after each report.

---

## 🛠️ Concepts Used

| Concept | Implementation in Project |
| :--- | :--- |
| **Variables & Data Types** | `int`, `double`, `boolean`, and `String` variables storing marks, totals, percentages, and status flags. |
| **Arrays** | Fixed-size parallel arrays (`String[] subjectNames`, `int[] marks`) dynamically allocated according to the number of subjects. |
| **Loops** | `for` loops for iterating through subjects and calculating totals; `while` loops for input retry validation and multi-student session control. |
| **Conditionals** | `if-else` ladders to categorize percentages into grade bands and check passing criteria. |
| **Modular Methods** | Dedicated static methods separating calculation logic (`calculateTotal`, `calculatePercentage`, `calculateGrade`, `calculateResult`) from I/O and flow control. |
| **Input Validation & Exception Handling** | `try-catch` blocks catching `NumberFormatException` to safely handle non-integer input from `Scanner`. |

---

## 🚀 How to Compile and Run

### Prerequisites
- Java Development Kit (JDK 17 or higher recommended; JDK 8+ compatible)
- Terminal / Command Prompt / PowerShell

### Steps

1. **Clone or navigate to the project directory**:
   ```bash
   cd path/to/student-grade-calculator
   ```

2. **Compile the Java source file**:
   ```bash
   javac StudentGradeCalculator.java
   ```

3. **Run the application**:
   ```bash
   java StudentGradeCalculator
   ```

---

## 💻 Sample Input / Output

### Scenario 1: Input Validation & Edge Cases

```text
==================================================
          STUDENT GRADE CALCULATOR                
==================================================

Enter Student Name: Alex Morgan
Enter Number of Subjects: 0
    [Error] Number must be greater than 0. You entered: 0
Enter Number of Subjects: abc
    [Error] Invalid input! Please enter a positive integer. You entered: "abc"
Enter Number of Subjects: 3

Enter marks for each subject (0 - 100):
  Subject #1 Name (press Enter for 'Subject 1'): Mathematics
  Enter marks for Mathematics [0-100]: -5
    [Error] Marks must be between 0 and 100. You entered: -5
  Enter marks for Mathematics [0-100]: 105
    [Error] Marks must be between 0 and 100. You entered: 105
  Enter marks for Mathematics [0-100]: abc
    [Error] Invalid input! Marks must be a valid integer number. You entered: "abc"
  Enter marks for Mathematics [0-100]: 100
  Subject #2 Name (press Enter for 'Subject 2'): Physics
  Enter marks for Physics [0-100]: 0
  Subject #3 Name (press Enter for 'Subject 3'): Chemistry
  Enter marks for Chemistry [0-100]: 38

==================================================
               STUDENT REPORT CARD                
==================================================
Student Name  : Alex Morgan
Total Subjects: 3
--------------------------------------------------
No.    | Subject Name             | Marks      | Status
-------+--------------------------+------------+--------
1      | Mathematics              | 100 / 100  | Pass  
2      | Physics                  |   0 / 100  | Fail  
3      | Chemistry                |  38 / 100  | Fail  
==================================================
Total Marks   : 138 / 300
Percentage    : 46.00%
Grade         : E
Overall Result: Fail
Note          : Failed due to scoring below 40 in one or more subjects.
==================================================

Calculate for another student? (y/n): n

Thank you for using the Student Grade Calculator! Goodbye.
```

### Scenario 2: Successful Run (Grade A+)

```text
==================================================
               STUDENT REPORT CARD                
==================================================
Student Name  : Sophia Patel
Total Subjects: 4
--------------------------------------------------
No.    | Subject Name             | Marks      | Status
-------+--------------------------+------------+--------
1      | English                  |  95 / 100  | Pass  
2      | History                  |  88 / 100  | Pass  
3      | Biology                  |  92 / 100  | Pass  
4      | Computer Science         |  98 / 100  | Pass  
==================================================
Total Marks   : 373 / 400
Percentage    : 93.25%
Grade         : A+
Overall Result: Pass
==================================================
```

---

## 📂 Project Structure

```
student-grade-calculator/
├── .gitignore               # Excludes compiled .class and temporary files
├── README.md                # Project documentation and guide
└── StudentGradeCalculator.java # Main application source file
```

---

## 📜 License
This project is open-source and free to use for educational and learning purposes.
