package LabExerciseFour;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class myOptions {

        private ArrayList<Student> students = new ArrayList<>();
    
        Scanner keyboard = new Scanner(System.in);
    
        public void option1 (Student the_students) throws Exception {
    
            String userInput;
            String averageGrade;
            String theLastName;

            boolean valid1;

            // Exception test for proper input of a program and year
            do {
    
                valid1 = true;

                System.out.print("Enter Student Program and Year: ");
                userInput = keyboard.nextLine();
        
                String other[] = userInput.split("[ ]+");

                try {
                    while (other.length != 2) {
                        System.out.println("\nThat is not a valid input. Please enter Student Program and Year: ");
                        userInput = keyboard.nextLine();
            
                        other = userInput.split("[ ]+");
                    }

                    int d1 = Integer.parseInt(other[1]);
    
                    the_students.setProgram(other[0]);
                    the_students.setYear(d1);

                    valid1 = true;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    valid1 = false;
                }

            } while (!valid1);
    
            System.out.print("\n");

            boolean valid;

            // Exception test for proper input of a grade
            do {
    
                valid = true;

                System.out.println("Enter Average grade, or leave blank: ");
                averageGrade = keyboard.nextLine();
                if (averageGrade.equals("")) {
                    averageGrade = "0";
                }

                try {
                    double d2 = Double.parseDouble(averageGrade);
                    the_students.setGrade(d2);
                    valid = true;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    valid = false;
                }

            } while (!valid);

            boolean valid2;

            // Exception test for proper input of a last name
            do {
    
                valid2 = true;

                System.out.print("Enter the last name of the student: ");
                theLastName = keyboard.nextLine();
        
                System.out.print("\n");

                try {
                    the_students.setLastName(theLastName);
                    valid2 = true;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    valid2 = false;
                }

            } while (!valid2);

            // Exception handling for a duplicate lastName
            for ( Student myStudentCounter : students ) {
                if ( myStudentCounter.getLastName().toLowerCase().equals(theLastName.toLowerCase()) ) {
                    System.out.println("\nThat lastName has already been entered.\n\n");
                    return;
                }
            }

            System.out.print("\n");
    
            students.add (the_students);
    
        }
    
        public void option2 (GraduateStudents graduateStudents) throws Exception {
    
            String userInput;
            String averageGrade;
            String theSupervisor;
            int thePhD;
            String theUndergraduateSchool;
            String theLastName;
    
            boolean valid1;

            // Exception test for proper input of a program and year
            do {
    
                valid1 = true;

                System.out.print("Enter Student Program and Year: ");
                userInput = keyboard.nextLine();
        
                String other[] = userInput.split("[ ]+");

                try {
                    while (other.length != 2) {
                        System.out.println("\nThat is not a valid input. Please enter Student Program and Year: ");
                        userInput = keyboard.nextLine();
            
                        other = userInput.split("[ ]+");
                    }

                    int d1 = Integer.parseInt(other[1]);
    
                    graduateStudents.setProgram(other[0]);
                    graduateStudents.setYear(d1);

                    valid1 = true;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    valid1 = false;
                }

            } while (!valid1);

            boolean valid;
    
            // Exception test for proper input of a grade
            do {
    
                valid = true;

                System.out.println("Enter Average grade, or leave blank: ");
                averageGrade = keyboard.nextLine();
                if (averageGrade.equals("")) {
                    averageGrade = "0";
                }

                try {
                    double d2 = Double.parseDouble(averageGrade);
                    graduateStudents.setGrade(d2);
                    valid = true;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    valid = false;
                }

            } while (!valid);
    
            System.out.print("\n");

            boolean valid2;

            // Exception test for proper input of a last name
            do {
    
                valid2 = true;

                System.out.print("Enter the last name of the student: ");
                theLastName = keyboard.nextLine();
        
                System.out.print("\n");

                try {
                    graduateStudents.setLastName(theLastName);
                    valid2 = true;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    valid2 = false;
                }

            } while (!valid2);

            // Exception handling for a duplicate lastName
            for ( Student myStudentCounter : students ) {
                if ( myStudentCounter.getLastName().toLowerCase().equals(theLastName.toLowerCase()) ) {
                    System.out.println("\nThat lastName has already been entered.\n\n");
                    return;
                }
            }
    
            boolean valid3;

            // Exception test for proper input of a last name
            do {
    
                valid3 = true;

                System.out.print( "Enter the supervisor: ");
                theSupervisor = keyboard.nextLine();
                System.out.print("\n");

                try {
                    graduateStudents.setSupervisor(theSupervisor);
                    valid3 = true;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    valid3 = false;
                }

            } while (!valid3);
    
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

            boolean valid4;

            // Exception test for proper input of a last name
            do {
    
                valid4 = true;

                System.out.print( "Enter the undergraduateSchool, or leave blank: ");
                theUndergraduateSchool = keyboard.nextLine();
                System.out.print("\n");

                try {
                    graduateStudents.setUndergraduateSchool(theUndergraduateSchool);
                    valid4 = true;

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    valid4 = false;
                }

            } while (!valid4);
    
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
    
        public void option6 () throws Exception {
    
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
    
                        if ( other.length == 4 ) {
    
                            my_student.setProgram (other[0] );
                            int d1 = Integer.parseInt(other[1]);
                            my_student.setYear ( d1 );
    
                            if (other[2].equals("")) {
                                other[2] = "0";
                            }
                    
                            double d2 = Double.parseDouble(other[2]);
                    
                            my_student.setGrade( d2 );

                            // Exception handling for a duplicate lastName
                            for ( Student myStudentCounter : students ) {
                                if ( myStudentCounter.getLastName().toLowerCase().equals(other[3].toLowerCase()) ) {
                                    System.out.println("\nThat lastName has already been entered.\n\n");
                                    scanner.close();
                                    return;
                                }
                            }

                            my_student.setLastName(other[3]);
    
                            students.add (my_student);
    
                        }
                    else if ( other.length == 7 ) {
    
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

                        // Exception handling for a duplicate lastName
                        for ( Student myStudentCounter : students ) {
                            if ( myStudentCounter.getLastName().toLowerCase().equals(other[6].toLowerCase()) ) {
                                System.out.println("\nThat lastName has already been entered.\n\n");
                                scanner.close();
                                return;
                            }
                        }

                        my_GraduateStudents.setLastName(other[6]);
    
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
                    fileWriter.print ( printer.toString()  + "\n");
                    fileWriter.close();
    
                    i = i + 1;
    
                }
    
            } catch ( Exception e ) {
                
                System.out.println ( "Failed to write.\n\n" );
    
            }
    
        }

        public void option8 ( String theLastName, String theProgram, String theYear ) {

            HashMap<String, ArrayList<Integer>> index = new HashMap<String, ArrayList<Integer>>();
            int i = 0;
            int counter = 0;
            int myChecker = 0;
            int m = 0;
            ArrayList<Integer> list = new ArrayList<Integer>();

            for ( Student searchingMyList : students ) {
                String myLastName = searchingMyList.getLastName().toLowerCase();
                if ( (index.containsKey(myLastName) ) ) {
                    list = index.get(myLastName);
                    list.add(counter);
                    index.put(myLastName, list );
                    list = new ArrayList<Integer>();
                }
                else {
                    String concatProgram = searchingMyList.getProgram().toLowerCase();
                    int concatYear = searchingMyList.getYear();
                    String finalString = concatProgram + concatYear + myLastName;
                    list.add(counter);
                    index.put(finalString, list );
                    list = new ArrayList<Integer>();
                }
                counter = counter + 1;
            }

            String searchingForTheName = theYear.toLowerCase() + theProgram + theLastName;

            for ( Student searchingMyList : students ) {
                String concatProgram = searchingMyList.getProgram().toLowerCase();
                int concatYear = searchingMyList.getYear();
                String concatLastName = searchingMyList.getLastName().toLowerCase();
                String finalString = concatProgram + concatYear + concatLastName;
                if ( finalString.equals(searchingForTheName.toLowerCase()) ) {
                    list = index.get(finalString.toLowerCase());
                    myChecker = 1;
                }
            }

            // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
            if ( myChecker == 1) {
                while ( m < 1 ) {
                    myChecker = 0;
                    Student printer = students.get(list.get(m));
                    myChecker = 2;
                    if ( myChecker == 2 ) {
                        System.out.println ( "----------The following infomation matches the entered last name----------");
                        System.out.println(printer.toString() + "\n");
                        myChecker = 1;
                    }
                    m = m + 1;
                }
            }
            i = i + 1;
    
        }
    
}
