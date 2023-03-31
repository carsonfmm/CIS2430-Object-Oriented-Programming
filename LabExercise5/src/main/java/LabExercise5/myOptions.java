package LabExercise5;

import java.util.Scanner;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class myOptions {

        private ArrayList<Student> students = new ArrayList<>();
    
        Scanner keyboard = new Scanner(System.in);
    
        public void option1 (Student the_students, String program, String lastName, String grade, String year) throws Exception {
    
            String theProgram = program;
            String theYear = year;
            String averageGrade = grade;
            String theLastName = lastName;

            if (theYear.equals("")) {
                JOptionPane.showMessageDialog(null, "You cannot leave the Year blank. Add DECLINED!");
                return;
            }

            if ( theProgram.equals("")) {
                JOptionPane.showMessageDialog(null, "You cannot leave the Program blank. Add DECLINED!");
                return;
            }

            try {

                int d1 = Integer.parseInt(theYear);

                the_students.setProgram(theProgram);
                the_students.setYear(d1);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + "\nInvalid input for the Year. Add DECLINED!");
                return;
            }

            if (averageGrade.equals("")) {
                averageGrade = "0";
            }

            try {
                double d2 = Double.parseDouble(averageGrade);
                the_students.setGrade(d2);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + "\nInvalid input for the Grade. Add DECLINED!");
                return;
            }

            if (theLastName.equals("")) {
                JOptionPane.showMessageDialog(null, "You cannot leave the LastName blank. Add DECLINED!");
                return;
            }

            try {
                the_students.setLastName(theLastName);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + " \nInvalid input for the LastName. Add DECLINED!");
                return;
            }

            for ( Student myStudentCounter : students ) {
                String finalKey = theProgram + theYear + theLastName;
                String otherKey = myStudentCounter.getProgram() + myStudentCounter.getYear() + myStudentCounter.getLastName();
                if ( otherKey.toLowerCase().equals(finalKey.toLowerCase()) ) {
                    JOptionPane.showMessageDialog(null, "That key has already been entered.\n\n");
                    return;
                }
            }
    
            students.add (the_students);
            JOptionPane.showMessageDialog(null, "New Student information saved.");
    
        }
    
        public void option2 (GraduateStudents graduateStudents, String program, String lastName, String grade, String year, String supervisor, String phd, String undergraduateschool) throws Exception {
    
            String theProgram = program;
            String theYear = year;
            String averageGrade = grade;
            String theLastName = lastName;
            String thePhD2 = phd;
            String theSupervisor = supervisor;
            String theUndergraduateSchool = undergraduateschool;

            if (theYear.equals("")) {
                JOptionPane.showMessageDialog(null, "You cannot leave the Year blank. Add DECLINED!");
                return;
            }

            if ( theProgram.equals("")) {
                JOptionPane.showMessageDialog(null, "You cannot leave the Program blank. Add DECLINED!");
                return;
            }

            try {

                int d1 = Integer.parseInt(theYear);

                graduateStudents.setProgram(theProgram);
                graduateStudents.setYear(d1);


            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + "\nInvalid input for the Year. Add DECLINED!");
                return;
            }

            if (averageGrade.equals("")) {
                averageGrade = "0";
            }

            try {
                double d2 = Double.parseDouble(averageGrade);
                graduateStudents.setGrade(d2);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + "\nInvalid input for the Grade. Add DECLINED!");
                return;
            }


            if (theLastName.equals("")) {
                JOptionPane.showMessageDialog(null, "You cannot leave the LastName blank. Add DECLINED!");
                return;
            }

            try {
                graduateStudents.setLastName(theLastName);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + " \nInvalid input for the LastName. Add DECLINED!");
                return;
            }

            if ( theSupervisor.equals("")) {
                JOptionPane.showMessageDialog(null, "You cannot leave the Supervisor blank. Add DECLINED!");
                return;
            }

            try {
                graduateStudents.setSupervisor(theSupervisor);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + "\nInvalid input for the Supervisor. Add DECLINED!");
                return;
            }

            int thePhD;

            try {
    
                thePhD = Integer.parseInt(thePhD2);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + "\nInvalid input for the PhD. Add DECLINED!");
                return;
            }

            if (thePhD != 1 && thePhD != 0) {
                JOptionPane.showMessageDialog(null, "\nPhD must be either 1 or 0. Add DECLINED!");
                return;
            }
    
            if ( thePhD == 1 ) {
                graduateStudents.setIsPhD(true);
            }
            if ( thePhD == 0 ) {
                graduateStudents.setIsPhD(false);
            }

            if ( theUndergraduateSchool.equals("")) {
                JOptionPane.showMessageDialog(null, "You cannot leave the UndergraduateSchool blank. Add DECLINED!");
                return;
            }

            try {
                graduateStudents.setUndergraduateSchool(theUndergraduateSchool);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + "\nInvalid input for the underGraduateSchool. Add DECLINED!");
                return;
            }

            for ( Student myStudentCounter : students ) {
                String finalKey = theProgram + theYear + theLastName;
                String otherKey = myStudentCounter.getProgram() + myStudentCounter.getYear() + myStudentCounter.getLastName();
                if ( otherKey.toLowerCase().equals(finalKey.toLowerCase()) ) {
                    JOptionPane.showMessageDialog(null, "That key has already been entered.\n\n");
                    return;
                }
            }
    
            students.add (graduateStudents);
            JOptionPane.showMessageDialog(null, "New Graduate Student information saved.");
    
        }
    
        public ArrayList<Student> option3 () {

            ArrayList<Student> printer = students;

            return printer;
    
        }
    
        public void option4 (Student the_student) {
    
            double theAverages = 0;
            double averageTotal;
    
            if ( students.size() == 0 ) {

                JOptionPane.showMessageDialog(null, "No student information has been entered yet.");
    
            }
    
            else {
                for ( Student printer: students ) {
    
                    theAverages = theAverages + printer.getGrade();
    
                }
    
                averageTotal = theAverages / students.size();
    
                JOptionPane.showMessageDialog(null, "The average grades for class is: " + averageTotal + "\nThe total number of students is: " + students.size() );
    
            }
    
        }
    
        public void option6 (String usersFile) throws Exception {
    
            String my_line;
    
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
                                    JOptionPane.showMessageDialog(null, "There is a LastName in the File that has already been entered. File Dump DECLINED!");
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
                                JOptionPane.showMessageDialog(null, "There is a LastName in the File that has already been entered. Input file read DECLINED!");
                                scanner.close();
                                return;
                            }
                        }

                        my_GraduateStudents.setLastName(other[6]);
    
                        students.add (my_GraduateStudents);
    
                    }
                    else {
                        JOptionPane.showMessageDialog( null, "The file is not in a valid format.\n\n" );
                        scanner.close();
                        return;
                    }

                }

                scanner.close();
                JOptionPane.showMessageDialog(null, "The input file was read successfully");
    
            } catch (FileNotFoundException e) {
    
                JOptionPane.showMessageDialog( null, "Error opening the file\n" );
                return;
    
            }
    
        }
    
        public void option7 (String usersFile) {
    
            try {
    
                int i = 0;
    
                for ( Student printer: students ) {
    
                    PrintWriter fileWriter = new PrintWriter ( new FileOutputStream ( usersFile, true ) );
                    fileWriter.print ( printer.toString());
                    if ( !(students.size()-1 == i) ) {
                        fileWriter.print ( "\n" );
                    }
                    fileWriter.close();
    
                    i = i + 1;
    
                }
    
            } catch ( Exception e ) {
                
                JOptionPane.showMessageDialog(null, "Failed to write." );
    
            }

            JOptionPane.showMessageDialog(null, "Data written to file!" );
    
        }

        public void option8 ( String theLastName, String theProgram, String theYear ) {

            HashMap<String, ArrayList<Integer>> index = new HashMap<String, ArrayList<Integer>>();
            int i = 0;
            int counter = 0;
            int myChecker = 0;
            int m = 0;
            ArrayList<Integer> list = new ArrayList<Integer>();

            if (theYear.equals("")) {
                JOptionPane.showMessageDialog(null, "You cannot leave any fields blank. Search DECLINED!");
                return;
            }
            if ( theProgram.equals("")) {
                JOptionPane.showMessageDialog(null, "You cannot leave any fields blank. Search DECLINED!");
                return;
            }
            if ( theLastName.equals("")) {
                JOptionPane.showMessageDialog(null, "You cannot leave any fields blank. Search DECLINED!");
                return;
            }

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
                        JOptionPane.showMessageDialog(null,  "The following infomation matches the entered information\n" + printer.toString2() + "\n");
                        myChecker = 1;
                    }
                    m = m + 1;
                }
            }
            else{
                JOptionPane.showMessageDialog(null,  "No match was found.");
                return;
            }
            i = i + 1;
    
        }
    
}
