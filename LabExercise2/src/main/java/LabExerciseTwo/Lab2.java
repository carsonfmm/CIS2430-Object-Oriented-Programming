package LabExerciseTwo;

import java.util.Scanner;
import java.util.ArrayList;

public class Lab2 {

    public static void main ( String[] args) {

        int option;
        String userInput;
        String userChoice;
        String averageGrade;
        int index = 0;
        Student yourStudents[] = new Student[100];
        double theAverages = 0;
        double averageTotal;
        int myBoolean = 0;

        for ( int j = 0; j < 100; j++ ) {
            yourStudents[j] = new Student();
        }

        ArrayList<Student> students = new ArrayList<Student>();

        Scanner keyboard = new Scanner(System.in);

        System.out.println ( "\nHello. Welcome to Lab Exercise Two!\n");

        do {

            System.out.println ( "(1) Enter information for a new student.\n" +
                                "(2) Show all student information with program, year, and average grade on seperate lines.\n" +
                                "(3) Print the average of the average grades for class, as well as the total number of students.\n" +
                                "(4) Enter a specific program and show all student information for that program.\n" +
                                "(5) End the program.\n" );

            System.out.print ( "Please select an option above by entering its corresponding number: " );
            option = keyboard.nextInt();
            System.out.print ( "\n" );

            while ( option < 1 || option > 5 ) {
                System.out.println ( "That is not a valid entry. Please select an option by entering its corresponding number: " );
                option = keyboard.nextInt();
            }

            if ( option == 1 ) {
                keyboard.nextLine();

                System.out.print ( "Enter Student Program and Year: ");
                userInput = keyboard.nextLine();

                String other[] = userInput.split ( "[ ]+" );

                System.out.print ( "\n" );

                while ( other.length != 2 ) {
                    System.out.println ( "That is not a valid input. Please enter Student Program and Year: ");
                    userInput = keyboard.nextLine();

                    other = userInput.split ( "[ ]+" );
                }

                int d1 = Integer.parseInt (other[1]);

                yourStudents[index].setProgram ( other[0] );
                yourStudents[index].setYear ( d1 );

                System.out.print ( "Enter Average grade, or leave blank: ");
                averageGrade = keyboard.nextLine();
                if ( averageGrade.equals ( "" ) ) {
                    averageGrade = "0";
                }

                double d2 = Double.parseDouble (averageGrade);

                yourStudents[index].setGrade ( d2 );

                System.out.print ( "\n" );
                index = index + 1;

                students.add ( yourStudents[index] );

            }

            if ( option == 2 ) {

                if ( students.size() == 0 ) {

                    System.out.println ( "No student information has been entered yet.");
                    System.out.print ( "\n" );

                }

                else {
                    for ( int i = 0; i < students.size(); i++ ) {

                        System.out.println(yourStudents[i].toString());

                        // System.out.println ( "Student " + (i+1) + ": " );

                        // System.out.println ( "  Program: " + yourStudents[i].getProgram() );
                        // System.out.println ( "  Year: " + yourStudents[i].getYear() );
                        // System.out.println ( "  Average Grade: " + yourStudents[i].getGrade() );
                        // System.out.print ( "\n" );

                    }
                }

            }

            if ( option == 3 ) {

                if ( students.size() == 0 ) {

                    System.out.println ( "No student information has been entered yet.");
                    System.out.print ( "\n" );

                }

                else {
                    for ( int i = 0; i < students.size(); i++ ) {

                        theAverages = theAverages + yourStudents[i].getGrade();

                    }

                    averageTotal = theAverages / students.size();

                    System.out.println ( "The average grades for class is: " + averageTotal );
                    System.out.println ( "The total number of students is: " + students.size() );

                    System.out.print ( "\n" );

                }

            }

            if ( option == 4 ) {

                myBoolean = 0;

                if ( students.size() == 0 ) {

                    System.out.println ( "No student information has been entered yet.");
                    System.out.print ( "\n" );

                }

                else {
                    keyboard.nextLine();

                    System.out.print ( "Enter a program (case sensitive): ");
                    userChoice = keyboard.nextLine();

                    System.out.print ( "\n" );

                    for ( int i = 0; i < students.size(); i++ ) {

                        if ( userChoice.equals(yourStudents[i].getProgram()) ) {
                            System.out.println ( "Student " + (i+1) + ": " );

                            System.out.println ( "  Program: " + yourStudents[i].getProgram() );
                            System.out.println ( "  Year: " + yourStudents[i].getYear() );
                            System.out.println ( "  Average Grade: " + yourStudents[i].getGrade() );
                            System.out.print ( "\n" );

                            myBoolean = 1;
                        }

                    }

                    if ( myBoolean == 0 ) {
                        System.out.println ( "There are no students enrolled in the program you have entered." );
                    }

                    System.out.print ( "\n" );

                }

            }

        } while ( option != 5 );

        keyboard.close();

    }

	public Object getProgram() {
		return null;
	}

	public Object getP() {
		return null;
	}

}

class Student {
    
    private String program;
    private int year;
    private double grade;

    public String getProgram() {
        return program;
    }
    public int getYear() {
        return year;
    }
    public double getGrade() {
        return grade;
    }

    public void setProgram(String program) {
        this.program = program;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String toString(){
        return "Progrm: " + getProgram() + "\nYear: " + getYear() + "\nGrade: " + getGrade();
    }

}