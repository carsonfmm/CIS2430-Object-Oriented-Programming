package LabExerciseThree;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class myOptions {

        private ArrayList<Student> students = new ArrayList<>();
    
        Scanner keyboard = new Scanner(System.in);
    
        public void option1 (Student the_students) {
    
            String userInput;
            String averageGrade;
    
            System.out.print("Enter Student Program and Year: ");
            userInput = keyboard.nextLine();
    
            String other[] = userInput.split("[ ]+");
    
            System.out.print("\n");
    
            while (other.length != 2) {
                System.out.println("That is not a valid input. Please enter Student Program and Year: ");
                userInput = keyboard.nextLine();
    
                other = userInput.split("[ ]+");
            }
    
            int d1 = Integer.parseInt(other[1]);
    
            the_students.setProgram(other[0]);
            the_students.setYear(d1);
    
            System.out.print("Enter Average grade, or leave blank: ");
            averageGrade = keyboard.nextLine();
            if (averageGrade.equals("")) {
                averageGrade = "0";
            }
    
            double d2 = Double.parseDouble(averageGrade);
    
            the_students.setGrade(d2);
    
            System.out.print("\n");
    
            students.add (the_students);
    
        }
    
        public void option2 (GraduateStudents graduateStudents) {
    
            String userInput;
            String averageGrade;
            String theSupervisor;
            int thePhD;
            String theUndergraduateSchool;
    
            System.out.print("Enter Student Program and Year: ");
            userInput = keyboard.nextLine();
    
            String other[] = userInput.split("[ ]+");
    
            System.out.print("\n");
    
            while (other.length != 2) {
                System.out.println("That is not a valid input. Please enter Student Program and Year: ");
                userInput = keyboard.nextLine();
    
                other = userInput.split("[ ]+");
            }
    
            int d1 = Integer.parseInt(other[1]);
    
            graduateStudents.setProgram(other[0]);
            graduateStudents.setYear(d1);
    
            System.out.print("Enter Average grade, or leave blank: ");
            averageGrade = keyboard.nextLine();
            if (averageGrade.equals("")) {
                averageGrade = "0";
            }
    
            double d2 = Double.parseDouble(averageGrade);
    
            graduateStudents.setGrade(d2);
    
            System.out.print("\n");
    
            System.out.print( "Enter the supervisor: ");
            theSupervisor = keyboard.nextLine();
            System.out.print("\n");
            while (theSupervisor.equals("")) {
                System.out.println("That is not a valid input. Please enter the supervisor: ");
                theSupervisor = keyboard.nextLine();
            }
    
            graduateStudents.setSupervisor(theSupervisor);
    
            System.out.print( "Enter 1 if the student received a PhD, if not enter 0: ");
            thePhD = keyboard.nextInt();
            System.out.print("\n");
            while (thePhD != 1 && thePhD != 0) {
                System.out.println("That is not a valid input. Enter 1 if the student received a PhD, if not enter 0: ");
                thePhD = keyboard.nextInt();
            }
    
            if ( thePhD == 1 ) {
                graduateStudents.setIsPhD(true);
            }
            if ( thePhD == 0 ) {
                graduateStudents.setIsPhD(false);
            }
    
            keyboard.nextLine();
    
            System.out.print( "Enter the undergraduateSchool, or leave blank: ");
            theUndergraduateSchool = keyboard.nextLine();
            if (theUndergraduateSchool.equals("")) {
                theUndergraduateSchool = "";
            }
    
            graduateStudents.setUndergraduateSchool(theUndergraduateSchool);
    
            System.out.print("\n");
    
            students.add (graduateStudents);
    
        }
    
        public void option3 () {
    
            int i = 0;
    
            if ( students.size() == 0 ) {
    
                System.out.println ( "No student information has been entered yet.");
                System.out.print ( "\n" );
    
            }
    
            else {
                for ( Student printer: students ) {
    
                    System.out.println ( "Student " + (i+1) + ": " );
    
                    System.out.println ( printer.toString() );
    
                    i = i + 1;
                    System.out.print("\n");
    
                }
            }
    
        }
    
        public void option4 (Student the_student) {
    
            double theAverages = 0;
            double averageTotal;
    
            if ( students.size() == 0 ) {
    
                System.out.println ( "No student information has been entered yet.");
                System.out.print ( "\n" );
    
            }
    
            else {
                for ( Student printer: students ) {
    
                    theAverages = theAverages + printer.getGrade();
    
                }
    
                averageTotal = theAverages / students.size();
    
                System.out.println ( "The average grades for class is: " + averageTotal );
                System.out.println ( "The total number of students is: " + students.size() );
    
                System.out.print ( "\n" );
    
            }
    
        }
    
        public void option5 () {
    
            int myBoolean = 0;
    
            if ( students.size() == 0 ) {
    
                System.out.println ( "No student information has been entered yet.");
                System.out.print ( "\n" );
    
            }
    
            else {
    
                String userChoice;
                int i = 0;
    
                System.out.print ( "Enter a program (case sensitive): ");
                userChoice = keyboard.nextLine();
    
                System.out.print ( "\n" );
    
                for ( Student printer: students ) {
    
                    if ( userChoice.equals(printer.getProgram()) ) {
                        System.out.println ( "Student " + (i+1) + ": " );
    
                        System.out.println ( printer.toString() );
    
                        System.out.print ( "\n" );
    
                        myBoolean = 1;
                    }
    
                    i = i + 1;
    
                }
    
                if ( myBoolean == 0 ) {
                    System.out.println ( "There are no students enrolled in the program you have entered." );
                }
    
                System.out.print ( "\n" );
    
            }
    
        }
    
        public void option6 () {
    
            String my_line;
            String usersFile;

            System.out.println ("Enter a file you wish to read form (ending in .txt): ");
            usersFile = keyboard.nextLine();
    
            try {
    
                File f = new File(usersFile);
    
                Scanner scanner = new Scanner( f );
    
                while ( scanner.hasNextLine()) {
    
                    GraduateStudents my_GraduateStudents = new GraduateStudents();
                    Student my_student = new Student();
    
                    my_line = scanner.nextLine();
    
                    String other[] = my_line.split("[ ]+");
    
                        if ( other.length == 3 ) {
    
                            my_student.setProgram (other[0] );
                            int d1 = Integer.parseInt(other[1]);
                            my_student.setYear ( d1 );
    
                            if (other[2].equals("")) {
                                other[2] = "0";
                            }
                    
                            double d2 = Double.parseDouble(other[2]);
                    
                            my_student.setGrade( d2 );
    
                            students.add (my_student);
    
                        }
                    else if ( other.length == 6 ) {
    
                        my_GraduateStudents.setProgram (other[0] );
                        int d1 = Integer.parseInt(other[1]);
                        my_GraduateStudents.setYear ( d1 );
    
                        if (other[2].equals("")) {
                            other[2] = "0";
                        }
                        double d2 = Double.parseDouble(other[2]);
                        my_GraduateStudents.setGrade( d2 );
    
                        my_GraduateStudents.setSupervisor ( other[3] );
    
                        int d3 = Integer.parseInt ( other[4] );
                
                        if ( d3 == 1 ) {
                            my_GraduateStudents.setIsPhD(true);
                        }
                        if ( d3 == 0 ) {
                            my_GraduateStudents.setIsPhD(false);
                        }
                
                        if (other[5].equals("")) {
                            other[5] = "";
                        }
                
                        my_GraduateStudents.setUndergraduateSchool(other[5]);
    
                        students.add (my_GraduateStudents);
    
                    }
                    else {
                        System.out.println ( "The file is not in a valid format.\n\n" );
                    }
    
                    }
    
                scanner.close();
    
            } catch (FileNotFoundException e) {
    
                System.out.println ( "Error opening the file\n" );
    
            }
    
        }
    
        public void option7 () {

            String usersFile;

            System.out.println ("Enter a file you wish to write to (ending in .txt): ");
            usersFile = keyboard.nextLine();
    
            try {
    
                int i = 0;
    
                for ( Student printer: students ) {
    
                    PrintWriter fileWriter = new PrintWriter ( new FileOutputStream ( usersFile, true ) );
                    fileWriter.println ( "Student " + (i+1) + ": \n" + printer.toString() + "\n" );
                    fileWriter.close();
    
                    i = i + 1;
    
                }
    
            } catch ( Exception e ) {
                
                System.out.println ( "Failed to write.\n\n" );
    
            }
    
        }
    
}
