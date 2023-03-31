package LabExerciseThree;

import java.util.Scanner;

public class Lab3 {

    public static void main(String[] args) {

        int option;

        System.out.print("\n");

        Scanner keyboard = new Scanner(System.in);

        System.out.println("\nHello. Welcome to Lab Exercise Two!\n");

        myOptions myOptions = new myOptions();

        do {

            GraduateStudents GraduateStudents = new GraduateStudents();
            Student students = new Student();

            System.out.println("(1) Enter information for a new student.\n"
                    + "(2) Enter information for a new GraduateStudent.\n"
                    + "(3) Show all student information with each attribute on a seperate line.\n"
                    + "(4) Print the average of the average grades for class, as well as the total number of students.\n"
                    + "(5) Enter a specific program and show all student information for that program.\n"
                    + "(6) Load student information from an input file.\n"
                    + "(7) Save all student information to an output file.\n"
                    + "(8) End the program.\n");

            System.out.print("Please select an option above by entering its corresponding number: ");
            option = keyboard.nextInt();
            System.out.print("\n");

            while (option < 1 || option > 8) {
                System.out.println(
                        "That is not a valid entry. Please select an option by entering its corresponding number: ");
                option = keyboard.nextInt();
            }

            if ( option == 1 ) {
                myOptions.option1(students);
            }

            if ( option == 2 ) {
                myOptions.option2(GraduateStudents);
            }

            if ( option == 3 ) {
                myOptions.option3();
            }

            if ( option == 4 ) {
                myOptions.option4(students);
            }

            if ( option == 5 ) {
                myOptions.option5();
            }

            if ( option == 6 ) {
                myOptions.option6();
            }

            if ( option == 7 ) {
                myOptions.option7();
            }

        } while (option != 8);

        keyboard.close();

    }

}