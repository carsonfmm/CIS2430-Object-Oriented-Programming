package LabExerciseOne;

import java.util.Scanner;

public class Lab1 {

    public static void main ( String[] args) {

        // Description: This program allows the user to store up to 10 sentences in an array, and select several options in relation to that array.
        // Author: Carson Mifsud
        // Last changed: 24/09/2020

        String[] userInput = new String[10];
        int[] truth1 = new int[10];
        int[] truth2 = new int[10];
        int i = 0;
        int j = 0;
        int option;
        int index = 0;
        int totalChar = 0;
        int totalCharVowel = 0;
        int total7Words = 0;
        int total8Words = 0;
        int counter1 = 0;
        int counter2 = 0;
        String user7;

        Scanner keyboard = new Scanner(System.in);

        System.out.println ( "Hello. Welcome to Lab Exercise One!\n");

        do {

            System.out.println ( "(1) Enter a full sentence.\n" +
                                 "(2) Print out all sentences entered thus far in the given order.\n" +
                                 "(3) Print out the number of sentences that have been entered thus far.\n" +
                                 "(4) Print out all sentences entered thus far in the reverse order.\n" +
                                 "(5) Print out the number of characters in all sentences combined.\n" +
                                 "(6) Calculate the total number of vowels contained in all stored sentences.\n" +
                                 "(7) Perform search for a given word using case insensitive comparisons.\n" +
                                 "(8) Perform search for a given word using case sensitive comparisons.\n" +
                                 "(9) End program.\n" );

            System.out.print ( "Please select an option above by entering its corresponding number: ");
            option = keyboard.nextInt();

            while ( option < 1 || option > 9 ) {
                System.out.print ( "That is not a valid entry. Please select an option by entering its corresponding number: ");
                option = keyboard.nextInt();
            }

            if ( option == 1 ) {
                keyboard.nextLine();

                if ( i > 9 ) {
                    System.out.print ( "Enter sentence : " );
                    keyboard.nextLine();
                    System.out.println ( "You have already entered 10 sentences. Sentence has been rejected.\n" );
                }
                else {
                    System.out.print ( "Enter sentence : " );
                    userInput[i] = keyboard.nextLine();
                    i++;
                    System.out.print ( "Sentence number " + i + " has been saved.\n\n" );
                }
            }

            if ( option == 2 ) {
                if ( i == 0 ) {
                    System.out.println ( "No sentences have been entered thus far." );
                }
                else {
                    System.out.println ( "Sentences entered thus far in the given order:" );
                    for ( index = 0; index < i; index++ ) {
                        System.out.println ( "Sentence number " + ( index + 1 ) + " is: " + userInput[index] );
                    }
                }
                System.out.println ();
            }

            if ( option == 3 ) {
                if ( i == 0 ) {
                    System.out.println ( "No sentences have been entered thus far.\n" );
                }
                System.out.println ( "The number of sentences entered thus far is: " + i + "\n" );
            }

            if ( option == 4 ) {
                if ( i == 0 ) {
                    System.out.println ( "No sentences have been entered thus far." );
                }
                else {
                    System.out.println ( "Sentences entered thus far in reverse order:" );
                    for ( index = i-1; index >= 0; index-- ) {
                        System.out.println ( "Sentence number " + ( index + 1 ) + " is: " + userInput[index] );
                    }
                }
                System.out.println ();
            }

            if ( option == 5 ) {
                if ( i == 0 ) {
                    System.out.println ( "There is a total of 0 characters entered in all sentences combined." );
                }
                else {
                    for ( index = counter1; index < i; index++ ) {
                        totalChar = totalChar + userInput[index].length();
                        counter1++;
                    }
                    System.out.println ( "There are a total of " + totalChar + " characters entered in all sentences combined." );
                }
                System.out.println ();
            }

            if ( option == 6 ) {
                if ( i == 0 ) {
                    System.out.println ( "There is a total of 0 vowels entered in all sentences combined." );
                }
                else {
                    for ( index = counter2; index < i; index++ ) {
                        for ( j = 0; j < userInput[index].length(); j++ ) {
                            if ( userInput[index].charAt(j) == 'a' || userInput[index].charAt(j) == 'e' || userInput[index].charAt(j) == 'i' ||
                            userInput[index].charAt(j) == 'o' || userInput[index].charAt(j) == 'u' || userInput[index].charAt(j) == 'A' ||
                            userInput[index].charAt(j) == 'E' || userInput[index].charAt(j) == 'I' || userInput[index].charAt(j) == 'O' || userInput[index].charAt(j) == 'U') {
                                totalCharVowel = totalCharVowel + 1;
                            }
                        }
                        counter2++;
                    }
                    System.out.println ( "There are a total of " + totalCharVowel + " vowels entered in all sentences combined." );
                }
                System.out.println ();
            }

            if ( option == 7 ) {
                keyboard.nextLine();
                System.out.print ( "Enter a word : " );
                user7 = keyboard.nextLine();
                if ( i == 0 ) {
                    System.out.println ( "No sentences have been entered. Therefore, the given word " + user7 + " has no recurrences. (Case insensitive)" );
                }
                else {
                    for ( index = 0; index < i; index++ ) {
                        String other[] = userInput[index].split ( " " );
                        for ( j = 0; j < other.length; j++ ) {
                            if ( user7.equalsIgnoreCase ( other[j])) {
                                total7Words = total7Words + 1;
                                truth1[index] = 1;
                            }
                        }
                    }
                    System.out.println ( "There are a total of " + total7Words + " recurrence(s) of the word " + user7 + ", entered in all sentences combined. (Case insensitive)" );
                    total7Words = 0;
                }
                System.out.println ( "The following sentences contain the word " + user7 + ": " );
                for ( j = 0; j < 10; j++ ) {
                    if ( truth1[j] == 1 ) {
                        System.out.println ( ( j + 1 ) + ". " + userInput[j] );
                    }
                }
                System.out.println ();
            }

            if ( option == 8 ) {
                keyboard.nextLine();
                System.out.print ( "Enter a word : " );
                user7 = keyboard.nextLine();
                if ( i == 0 ) {
                    System.out.println ( "No sentences have been entered. Therefore, the given word " + user7 + " has no recurrences. (Case sensitive)" );
                }
                else {
                    for ( index = 0; index < i; index++ ) {
                        String other[] = userInput[index].split ( " " );
                        for ( j = 0; j < other.length; j++ ) {
                            if ( user7.equals ( other[j])) {
                                total8Words = total8Words + 1;
                                truth2[index] = 1;
                            }
                        }
                    }
                    System.out.println ( "There are a total of " + total8Words + " recurrence(s) of the word " + user7 + ", entered in all sentences combined. (Case sensitive)" );
                    total8Words = 0;
                }
                System.out.println ( "The following sentences contain the word " + user7 + ": " );
                for ( j = 0; j < 10; j++ ) {
                    if ( truth2[j] == 1 ) {
                        System.out.println ( ( j + 1 ) + ". " + userInput[j] );
                    }
                }
                System.out.println ();
            }

        } while ( option != 9 );

        System.out.println ( "Thank you for using the program.\n" );

        keyboard.close();

    }
    
}
