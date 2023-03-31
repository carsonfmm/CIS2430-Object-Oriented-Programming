package eStoreSearch;

import java.util.Scanner;

/*
 * Name: Carson Mifsud
 * Date: 2020-10-19
 * Description: This program is an eStore simulator which allows a user to add new books and electronics
 *              to the store. The program will read in a file at the start of the program and write any 
 *              new products to the same file throughout the program. The user also has the ability to
 *              search for a book or electronic by a specific productID, keyword(s) and/or date.
 */

public class EStoreSearch {

    public static void main(String[] args) {

        // Initializing an object store of type theSearch
        theSearch store = new theSearch();

        Scanner keyboard = new Scanner(System.in);
        String firstOption;
        String myFile;

        // This initializes the variable myFile with the first argument given in the command line
        myFile = args[0];

        // This line calls the method yourFile and sends it the file name string stored in myFile
        store.yourFile(myFile);

        do {

            // Initializing an object myElectronic of type Electronics
            Electronics myElectronic = new Electronics();
            // Initializing an object myBook of type Books
            Books myBook = new Books();
            // Initializing an object myProducts of type Products
            Products myProducts = new Products();

            String bookOrElectronics;
            String searchChoicesID;
            String searchChoicesDescription;
            String searchChoicesYear;
            int temp = 0;

            System.out.println("\nHello. Welcome to eStoreSearch!\n");

            System.out.println("(1) \'add\'\n" + "(2) \'search\'\n" + "(3) \'quit\'\n");

            System.out.println( "Please select one of the options above by typing the exact word within the single quotations: ");
            firstOption = keyboard.nextLine();
            if ( temp >= 1 ) {
                firstOption = keyboard.nextLine();
            }

            temp = temp + 1;

            // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
            while (!(firstOption.equalsIgnoreCase("add") || firstOption.equalsIgnoreCase("search") || firstOption.equalsIgnoreCase("quit") || firstOption.equalsIgnoreCase("q") )) {
                System.out.println( "That is not a valid input. Please select one of the options above by typing the exact word within the single quotations: ");
                firstOption = keyboard.nextLine();
            }

            if ( firstOption.equalsIgnoreCase("add") ) {

                System.out.println("\n(1) \'book\'\n" + "(2) \'electronic\'\n");

                System.out.print( "Please select one of the options above by typing the exact word within the single quotations: ");
                bookOrElectronics = keyboard.next();

                System.out.print ( "\n" );

                // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
                while (!(bookOrElectronics.equalsIgnoreCase("book") || bookOrElectronics.equalsIgnoreCase("electronic"))) {
                    System.out.println ( "That is not a valid entry. Please select one of the options above by typing the exact word within the single quotations: ");
                    bookOrElectronics = keyboard.next();
                }

                if ( bookOrElectronics.equalsIgnoreCase("book")) {
                    store.yourBook ( myBook, myProducts, myFile );
                }
                else if ( bookOrElectronics.equalsIgnoreCase("electronic")) {
                    store.yourElectronic ( myElectronic, myProducts, myFile );
                }

            }

            else if ( firstOption.equalsIgnoreCase("search") ) {
            
                System.out.println ( "Please enter a productID for the search request, which must be 6 digits long. NOTE: this field may be left blank");
                searchChoicesID = keyboard.nextLine();
                if ( searchChoicesID.equalsIgnoreCase ( "" ) ) {
                    searchChoicesID = "";
                }

                if ( !(searchChoicesID.equalsIgnoreCase ( "" )) ) {
                    // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
                    while ( !( searchChoicesID.length() == 6 || searchChoicesID.equals("")) ) {
                            System.out.println ( "That is not a valid entry. Please enter a productID for the search request, which must be 6 digits long. NOTE: this field may be left blank");
                            searchChoicesID = keyboard.nextLine();
                            if ( searchChoicesID.equalsIgnoreCase ( "" ) ) {
                                searchChoicesID = "";
                            }
                    }
                }

                System.out.println ( "Please enter any keywords for the description for the search request, words must be seperated by spaces. NOTE: this field may be left blank");
                searchChoicesDescription = keyboard.nextLine();
                if ( searchChoicesDescription.equalsIgnoreCase ( "" ) ) {
                    searchChoicesDescription = "";
                }

                String other[] = searchChoicesDescription.split ( "[ ]+");

                System.out.println ( "Please enter the time period for the search request, which must be in the form of startYear-endYear.  NOTE: either year fields may be left blank");
                searchChoicesYear = keyboard.nextLine();
                if ( searchChoicesYear.equalsIgnoreCase ( "" ) ) {
                    searchChoicesYear = "";
                }
                // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
                while ( !(searchChoicesYear.equalsIgnoreCase ("") || searchChoicesYear.length() == 4 || (searchChoicesYear.length() == 5 && searchChoicesYear.indexOf ('-') == 0) || (searchChoicesYear.length() == 5 && searchChoicesYear.indexOf ('-') == 4) || (searchChoicesYear.length() == 9 && searchChoicesYear.indexOf ('-') == 4) ) ) {
                    System.out.println ( "That is not a valid entry. Please enter the time period for the search request, which must be in the form of startYear-endYear.  NOTE: either year fields may be left blank");
                    searchChoicesYear = keyboard.nextLine();
                    if ( searchChoicesYear.equalsIgnoreCase ( "" ) ) {
                        searchChoicesYear = "";
                    }
                }

                // Call the search method to search for the given user inputs
                store.yourSearch ( searchChoicesID, other, searchChoicesYear );
                
            }

        // This while loop checks if the user has entered 'quit' or 'q'. If so the program will end, if not the user will be prompted with the selection menu once again.
        } while ( !(firstOption.equalsIgnoreCase("quit") || firstOption.equalsIgnoreCase ("q") ) );

        System.out.println ( "\nThank you for using eStoreSearch! Enjoy your day!\n" );

        keyboard.close();

    }
}