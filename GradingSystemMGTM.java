import java.util.Scanner;

class Student {
    private int studentID;
    private String name;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }
}

class Grade {
    private int studentID;
    private int courseID;
    private char grade;

    public Grade(int studentID, int courseID, char grade) {
        this.studentID = studentID;
        this.courseID = courseID;
        this.grade = grade;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public char getGrade() {
        return grade;
    }
}

class GradingSystem {
    private static final int MAX_STUDENTS = 100;
    private static final int MAX_GRADES = 1000;
    private static final int MAX_COURSES = 10;

    private Student[] students;
    private Grade[] grades;
    private int[] courseCredits;
    private int studentCount;
    private int gradeCount;

    public GradingSystem() {
        students = new Student[MAX_STUDENTS];
        grades = new Grade[MAX_GRADES];
        courseCredits = new int[MAX_COURSES];
        studentCount = 0;
        gradeCount = 0;
    }

    public void addStudent(Student student) {
        if (studentCount < MAX_STUDENTS) {
            students[studentCount++] = student;
        } else {
            System.out.println("Cannot add more students.");
        }
    }

    public void addGrade(Grade grade) {
        if (gradeCount < MAX_GRADES) {
            grades[gradeCount++] = grade;
        } else {
            System.out.println("Cannot add more grades.");
        }
    }

    public void addCourseCredits(int courseID, int credits) {
        if (courseID < MAX_COURSES) {
            courseCredits[courseID] = credits;
        } else {
            System.out.println("Invalid course ID.");
        }
    }

    public double calculateGPA(int studentID) {
        int totalPoints = 0;
        int totalCredits = 0;

        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentID() == studentID) {
                int credits = courseCredits[grades[i].getCourseID()];
                totalPoints += gradeToPoints(grades[i].getGrade()) * credits;
                totalCredits += credits;
            }
        }

        return (totalCredits == 0) ? 0.0 : (double) totalPoints / totalCredits;
    }

    private int gradeToPoints(char grade) {
        switch (grade) {
            case 'A': return 4;
            case 'B': return 3;
            case 'C': return 2;
            case 'D': return 1;
            case 'F': return 0;
            default: return 0;
        }
    }

    public void generateGradeReport(int studentID) {
        System.out.println("Grade Report for Student ID: " + studentID);
        for (int i = 0; i < gradeCount; i++) {
            if (grades[i].getStudentID() == studentID) {
                System.out.println("Course ID: " + grades[i].getCourseID() + ", Grade: " + grades[i].getGrade());
            }
        }
        System.out.println("GPA: " + calculateGPA(studentID));
    }
}

public class GradingSystemMGMT {
    private static GradingSystem gradingSystem = new GradingSystem();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. Add Course Credits");
            System.out.println("4. Calculate GPA");
            System.out.println("5. Generate Grade Report");
            System.out.println("6. Exit");
            System.out.print("Option: ");
            int option = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Student ID: ");
                    int studentID = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    gradingSystem.addStudent(new Student(studentID, name));
                    System.out.println("Student added.");
                    break;

                case 2:
                    System.out.print("Student ID: ");
                    studentID = sc.nextInt();
                    System.out.print("Course ID: ");
                    int courseID = sc.nextInt();
                    System.out.print("Grade: ");
                    char grade = sc.next().charAt(0);
                    gradingSystem.addGrade(new Grade(studentID, courseID, grade));
                    System.out.println("Grade added.");
                    break;

                case 3:
                    System.out.print("Course ID: ");
                    courseID = sc.nextInt();
                    System.out.print("Credits: ");
                    int credits = sc.nextInt();
                    gradingSystem.addCourseCredits(courseID, credits);
                    System.out.println("Course credits added.");
                    break;

                case 4:
                    System.out.print("Student ID: ");
                    studentID = sc.nextInt();
                    double gpa = gradingSystem.calculateGPA(studentID);
                    System.out.println("GPA: " + gpa);
                    break;

                case 5:
                    System.out.print("Student ID: ");
                    studentID = sc.nextInt();
                    gradingSystem.generateGradeReport(studentID);
                    break;

                case 6:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }

        sc.close();
    }
}
