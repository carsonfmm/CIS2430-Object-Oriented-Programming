package eStoreSearch;

import java.util.Scanner;
import java.util.ArrayList;

/*
 * Name: Carson Mifsud
 * Date: 2020-10-19
 * Description: This program is an eStore simulator which allows a user to add new books and electronics
 *              to the store. The user also has the ability to search for a book or electronic
 *              by a specific productID, keyword9(s) and/or date.
 */

public class EStoreSearch {

    public static void main(String[] args) {

        // Initializing an object store of type theSearch
        theSearch store = new theSearch();

        Scanner keyboard = new Scanner(System.in);
        String firstOption;

        do {

            // Initializing an object myElectronic of type Electronics
            Electronics myElectronic = new Electronics();
            // Initializing an object myBook of type Books
            Books myBook = new Books();

            String bookOrElectronics;
            String searchChoicesID;
            String searchChoicesDescription;
            String searchChoicesYear;
            int checker = 0;

            System.out.println("\nHello. Welcome to eStoreSearch!\n");

            System.out.println("(1) \'add\'\n" + "(2) \'search\'\n" + "(3) \'quit\'\n");

            System.out.println( "Please select one of the options above by typing the exact word within the single quotations: ");
            firstOption = keyboard.nextLine();

            // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
            while (!(firstOption.equalsIgnoreCase("add") || firstOption.equalsIgnoreCase("search") || firstOption.equalsIgnoreCase("quit") || firstOption.equalsIgnoreCase("q") )) {
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
                    store.yourBook ( myBook );
                }
                else if ( bookOrElectronics.equalsIgnoreCase("electronic")) {
                    store.yourElectronic ( myElectronic );
                }

            }

            else if ( firstOption.equalsIgnoreCase("search") ) {
            
                System.out.println ( "Please enter a productID for the search request, which must be 6 digits long. NOTE: this field may be left blank");
                searchChoicesID = keyboard.nextLine();
                if ( searchChoicesID.equalsIgnoreCase ( "" ) ) {
                    searchChoicesID = "1";
                    checker = 1;
                }

                // Parse the variable searchChoicesID to integer data type
                int theSearchIDInteger = Integer.parseInt (searchChoicesID);

                // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
                while ( ( theSearchIDInteger < 99999 || theSearchIDInteger > 999999 ) && checker == 0 ) {
                        System.out.println ( "That is not a valid entry. Please enter a productID for the search request, which must be 6 digits long. NOTE: this field may be left blank");
                        searchChoicesID = keyboard.nextLine();

                        // Parse the variable searchChoicesID to integer data type
                        theSearchIDInteger = Integer.parseInt (searchChoicesID);
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

                store.yourSearch ( theSearchIDInteger, other, searchChoicesYear );
                
            }

        // This while loop checks if the user has entered 'quit' or 'q'. If so the program will end, if not the user will be prompted with the selection menu once again.
        } while ( !(firstOption.equalsIgnoreCase("quit") || firstOption.equalsIgnoreCase ("q") ) );

        System.out.println ( "\nThank you for using eStoreSearch! Enjoy your day!\n" );

        keyboard.close();

    }
}

class theSearch {
    
    private ArrayList<Books> bookList = new ArrayList<>();
    private ArrayList<Electronics> electronicsList = new ArrayList<>();
    private ArrayList<Integer> allIDs = new ArrayList<Integer>();

    /**
     * This method uses the users search input to search through all books and electronics within the store.
     * This method takes all conditions into consideration and prints out any matches found accordingly.
     * 
     * @param ID
     * @param keywords
     * @param years
     */
    public void yourSearch ( int ID, String[] keywords, String years ) {

        int i = 0;

        for ( Books searchTheBooks : bookList ) {

            // This if condition is used if the user leaves all search fields blank
            if ( ID == 1 && (keywords.length == 1 && keywords[0].equalsIgnoreCase("")) && years.equalsIgnoreCase("") ) {
                System.out.println ( "----------The following infomation is present in the store----------");
                System.out.println ( "ID: " + searchTheBooks.getProductID() );
                System.out.println ( "Description: " + searchTheBooks.getDescription() );
                System.out.println ( "Year: " + searchTheBooks.getYear() );
                if ( searchTheBooks.getPrice() == 0 ) {
                    System.out.println ( "Price: N/A" );
                }
                else {
                    System.out.println ( "Price: " + searchTheBooks.getPrice() );
                }
                if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                    System.out.println ( "Authors: N/A" );
                }
                else {
                    System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                }
                if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                    System.out.println ( "Publisher: N/A" );
                }
                else {
                    System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                }
            }

            // This if condition is used if the user enters a productID to be searched
            else if ( ID != 1 && (keywords.length == 1 && keywords[0].equalsIgnoreCase("")) && years.equalsIgnoreCase("") ) {
                if ( ID == searchTheBooks.getProductID() ) {
                    System.out.println ( "----------The following infomation matches the entered productID----------");
                    System.out.println ( "ID: " + searchTheBooks.getProductID() );
                    System.out.println ( "Description: " + searchTheBooks.getDescription() );
                    System.out.println ( "Year: " + searchTheBooks.getYear() );
                    if ( searchTheBooks.getPrice() == 0 ) {
                        System.out.println ( "Price: N/A" );
                    }
                    else {
                        System.out.println ( "Price: " + searchTheBooks.getPrice() );
                    }
                    if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                        System.out.println ( "Authors: N/A" );
                    }
                    else {
                        System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                    }
                    if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                        System.out.println ( "Publisher: N/A" );
                    }
                    else {
                        System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                    }
                }
            }

            // This if condition is used if the user enters a keyword(s) to be searched            
            else if ( ID == 1 && (keywords.length != 1 || !(keywords[0].equalsIgnoreCase(""))) && years.equalsIgnoreCase("") ) {
                int theLength = keywords.length;
                i = 0;
                int myChecker = 0;
                // This while loop checks if any keywords match any stored book
                while ( i < theLength ) {
                    if ( searchTheBooks.getDescription().toUpperCase().contains(keywords[i].toUpperCase()) ) {
                        myChecker = 1;
                    }
                    i = i + 1;
                }
                // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                if ( myChecker == 1) {
                    myChecker = 0;
                    System.out.println ( "----------The following infomation matches the entered keyword(s)----------");
                    System.out.println ( "ID: " + searchTheBooks.getProductID() );
                    System.out.println ( "Description: " + searchTheBooks.getDescription() );
                    System.out.println ( "Year: " + searchTheBooks.getYear() );
                    if ( searchTheBooks.getPrice() == 0 ) {
                        System.out.println ( "Price: N/A" );
                    }
                    else {
                        System.out.println ( "Price: " + searchTheBooks.getPrice() );
                    }
                    if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                        System.out.println ( "Authors: N/A" );
                    }
                    else {
                        System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                    }
                    if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                        System.out.println ( "Publisher: N/A" );
                    }
                    else {
                        System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                    }
                }
            }

            // This if condition is used if the user enters a time period to be searched
            else if ( ID == 1 && (keywords.length == 1 && keywords[0].equalsIgnoreCase("")) && !(years.equalsIgnoreCase("") ) ) {
                // This if condition is used when the user enters one year
                if ( years.length() == 4 ) {

                    // Parse the variable years to integer data type
                    int otherYear = Integer.parseInt ( years );
                    if ( otherYear == searchTheBooks.getYear() ) {
                        System.out.println ( "----------The following infomation matches the entered year----------");
                        System.out.println ( "ID: " + searchTheBooks.getProductID() );
                        System.out.println ( "Description: " + searchTheBooks.getDescription() );
                        System.out.println ( "Year: " + searchTheBooks.getYear() );
                        if ( searchTheBooks.getPrice() == 0 ) {
                            System.out.println ( "Price: N/A" );
                        }
                        else {
                            System.out.println ( "Price: " + searchTheBooks.getPrice() );
                        }
                        if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                            System.out.println ( "Authors: N/A" );
                        }
                        else {
                            System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                        }
                        if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                            System.out.println ( "Publisher: N/A" );
                        }
                        else {
                            System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                        }
                    }
                }
                // This if condition is used when the user enters a year with a '-' at the beginning
                else if ( years.length() == 5 && years.indexOf ('-') == 0 ) {
                    String yearsTwo = years;
                    int editYears = searchTheBooks.getYear();
                    yearsTwo = yearsTwo.replaceAll ( "\\D", "");

                    // Parse the variable yearsTwo to integer data type
                    int otherYear = Integer.parseInt ( yearsTwo );
                    while ( otherYear != 999 ) {
                        if ( otherYear == editYears ) {
                            System.out.println ( "----------The following infomation matches the entered year----------");
                            System.out.println ( "ID: " + searchTheBooks.getProductID() );
                            System.out.println ( "Description: " + searchTheBooks.getDescription() );
                            System.out.println ( "Year: " + searchTheBooks.getYear() );
                            if ( searchTheBooks.getPrice() == 0 ) {
                                System.out.println ( "Price: N/A" );
                            }
                            else {
                                System.out.println ( "Price: " + searchTheBooks.getPrice() );
                            }
                            if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                                System.out.println ( "Authors: N/A" );
                            }
                            else {
                                System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                            }
                            if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                                System.out.println ( "Publisher: N/A" );
                            }
                            else {
                                System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                            }
                        }
                        otherYear = otherYear - 1;;
                    }

                }
                // This if condition checks if the user enters a year with a '-' at the end
                else if ( years.length() == 5 && years.indexOf ('-') == 4 ) {
                    String yearsTwo = years;
                    int editYears = searchTheBooks.getYear();
                    yearsTwo = yearsTwo.replaceAll ( "\\D", "");

                    // Parse the variable yearsTwo to integer data type
                    int otherYear = Integer.parseInt ( yearsTwo );
                    while ( otherYear != 10000 ) {
                        if ( otherYear == editYears ) {
                            System.out.println ( "----------The following infomation matches the entered year----------");
                            System.out.println ( "ID: " + searchTheBooks.getProductID() );
                            System.out.println ( "Description: " + searchTheBooks.getDescription() );
                            System.out.println ( "Year: " + searchTheBooks.getYear() );
                            if ( searchTheBooks.getPrice() == 0 ) {
                                System.out.println ( "Price: N/A" );
                            }
                            else {
                                System.out.println ( "Price: " + searchTheBooks.getPrice() );
                            }
                            if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                                System.out.println ( "Authors: N/A" );
                            }
                            else {
                                System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                            }
                            if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                                System.out.println ( "Publisher: N/A" );
                            }
                            else {
                                System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                            }
                        }
                        otherYear = otherYear + 1;;
                    }
                }
                // This if condition checks is the user has entered two years seperatde by a '-'
                else if ( years.length() == 9 && years.indexOf ('-') == 4 ) {
                    String yearsTwo = years;
                    int editYears = searchTheBooks.getYear();
                    String other[] = yearsTwo.split ("-");

                    // Parse the variable other[0] to integer data type
                    int year1 = Integer.parseInt ( other[0] );
                    // Parse the variable other[1] to integer data type
                    int year2 = Integer.parseInt ( other[1] );
                    while ( year1 != year2 + 1 ) {
                        if ( year1 == editYears ) {
                            System.out.println ( "----------The following infomation matches the entered year----------");
                            System.out.println ( "ID: " + searchTheBooks.getProductID() );
                            System.out.println ( "Description: " + searchTheBooks.getDescription() );
                            System.out.println ( "Year: " + searchTheBooks.getYear() );
                            if ( searchTheBooks.getPrice() == 0 ) {
                                System.out.println ( "Price: N/A" );
                            }
                            else {
                                System.out.println ( "Price: " + searchTheBooks.getPrice() );
                            }
                            if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                                System.out.println ( "Authors: N/A" );
                            }
                            else {
                                System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                            }
                            if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                                System.out.println ( "Publisher: N/A" );
                            }
                            else {
                                System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                            }
                        }
                        year1 = year1 + 1;;
                    }
                }
            }

            // This if condition is used if the user enters a productID and a time period to be searched            
            else if ( ID != 1 && (keywords.length == 1 && keywords[0].equalsIgnoreCase("")) && !(years.equalsIgnoreCase("")) ) {
                if ( ID == searchTheBooks.getProductID() ) {
                    // This if condition is used when the user enters one year
                    if ( years.length() == 4 ) {

                        // Parse the variable years to integer data type
                        int otherYear = Integer.parseInt ( years );
                        if ( otherYear == searchTheBooks.getYear() ) {
                            System.out.println ( "----------The following infomation matches the entered productID and year----------");
                            System.out.println ( "ID: " + searchTheBooks.getProductID() );
                            System.out.println ( "Description: " + searchTheBooks.getDescription() );
                            System.out.println ( "Year: " + searchTheBooks.getYear() );
                            if ( searchTheBooks.getPrice() == 0 ) {
                                System.out.println ( "Price: N/A" );
                            }
                            else {
                                System.out.println ( "Price: " + searchTheBooks.getPrice() );
                            }
                            if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                                System.out.println ( "Authors: N/A" );
                            }
                            else {
                                System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                            }
                            if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                                System.out.println ( "Publisher: N/A" );
                            }
                            else {
                                System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                            }
                        }
                    }
                    // This if condition is used when the user enters a year with a '-' at the beginning
                    else if ( years.length() == 5 && years.indexOf ('-') == 0 ) {
                        String yearsTwo = years;
                        int editYears = searchTheBooks.getYear();
                        yearsTwo = yearsTwo.replaceAll ( "\\D", "");

                        // Parse the variable yearsTwo to integer data type
                        int otherYear = Integer.parseInt ( yearsTwo );
                        while ( otherYear != 999 ) {
                            if ( otherYear == editYears ) {
                                System.out.println ( "----------The following infomation matches the entered productID and year----------");
                                System.out.println ( "ID: " + searchTheBooks.getProductID() );
                                System.out.println ( "Description: " + searchTheBooks.getDescription() );
                                System.out.println ( "Year: " + searchTheBooks.getYear() );
                                if ( searchTheBooks.getPrice() == 0 ) {
                                    System.out.println ( "Price: N/A" );
                                }
                                else {
                                    System.out.println ( "Price: " + searchTheBooks.getPrice() );
                                }
                                if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Authors: N/A" );
                                }
                                else {
                                    System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                                }
                                if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Publisher: N/A" );
                                }
                                else {
                                    System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                                }
                            }
                            otherYear = otherYear - 1;;
                        }
    
                    }
                    // This if condition checks if the user enters a year with a '-' at the end
                    else if ( years.length() == 5 && years.indexOf ('-') == 4 ) {
                        String yearsTwo = years;
                        int editYears = searchTheBooks.getYear();
                        yearsTwo = yearsTwo.replaceAll ( "\\D", "");
                        // Parse the variable yearsTwo to integer data type
                        int otherYear = Integer.parseInt ( yearsTwo );
                        while ( otherYear != 10000 ) {
                            if ( otherYear == editYears ) {
                                System.out.println ( "----------The following infomation matches the entered productID and year----------");
                                System.out.println ( "ID: " + searchTheBooks.getProductID() );
                                System.out.println ( "Description: " + searchTheBooks.getDescription() );
                                System.out.println ( "Year: " + searchTheBooks.getYear() );
                                if ( searchTheBooks.getPrice() == 0 ) {
                                    System.out.println ( "Price: N/A" );
                                }
                                else {
                                    System.out.println ( "Price: " + searchTheBooks.getPrice() );
                                }
                                if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Authors: N/A" );
                                }
                                else {
                                    System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                                }
                                if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Publisher: N/A" );
                                }
                                else {
                                    System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                                }
                            }
                            otherYear = otherYear + 1;;
                        }
                    }
                    // This if condition checks is the user has entered two years seperatde by a '-'
                    else if ( years.length() == 9 && years.indexOf ('-') == 4 ) {
                        String yearsTwo = years;
                        int editYears = searchTheBooks.getYear();
                        String other[] = yearsTwo.split ("-");
                        // Parse the variable other[0] to integer data type
                        int year1 = Integer.parseInt ( other[0] );
                        // Parse the variable other[1] to integer data type
                        int year2 = Integer.parseInt ( other[1] );
                        while ( year1 != year2 + 1 ) {
                            if ( year1 == editYears ) {
                                System.out.println ( "----------The following infomation matches the entered productID and year----------");
                                System.out.println ( "ID: " + searchTheBooks.getProductID() );
                                System.out.println ( "Description: " + searchTheBooks.getDescription() );
                                System.out.println ( "Year: " + searchTheBooks.getYear() );
                                if ( searchTheBooks.getPrice() == 0 ) {
                                    System.out.println ( "Price: N/A" );
                                }
                                else {
                                    System.out.println ( "Price: " + searchTheBooks.getPrice() );
                                }
                                if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Authors: N/A" );
                                }
                                else {
                                    System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                                }
                                if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Publisher: N/A" );
                                }
                                else {
                                    System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                                }
                            }
                            year1 = year1 + 1;;
                        }
                    }
                }
            }

            // This if condition is used if the user enters a productID and a keyword(s) to be searched            
            else if ( ID != 1 && (keywords.length != 1 || !(keywords[0].equalsIgnoreCase(""))) && years.equalsIgnoreCase("") ) {
                if ( ID == searchTheBooks.getProductID() ) {
                    int theLength = keywords.length;
                    i = 0;
                    int myChecker = 0;
                    // This while loop checks if any keywords match any stored book
                    while ( i < theLength ) {
                        if ( searchTheBooks.getDescription().toUpperCase().contains(keywords[i].toUpperCase()) ) {
                            myChecker = 1;
                        }
                        i = i + 1;
                    }
                    // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                    if ( myChecker == 1 ) {
                        myChecker = 0;
                        System.out.println ( "----------The following infomation matches the entered productID and keyword(s)----------");
                        System.out.println ( "ID: " + searchTheBooks.getProductID() );
                        System.out.println ( "Description: " + searchTheBooks.getDescription() );
                        System.out.println ( "Year: " + searchTheBooks.getYear() );
                        if ( searchTheBooks.getPrice() == 0 ) {
                            System.out.println ( "Price: N/A" );
                        }
                        else {
                            System.out.println ( "Price: " + searchTheBooks.getPrice() );
                        }
                        if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                            System.out.println ( "Authors: N/A" );
                        }
                        else {
                            System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                        }
                        if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                            System.out.println ( "Publisher: N/A" );
                        }
                        else {
                            System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                        }
                    }
                }
            }

            // This if condition is used if the user enters a keyword(s) and a time period to be searched            
            else if ( ID == 1 && (keywords.length != 1 || !(keywords[0].equalsIgnoreCase(""))) && !(years.equalsIgnoreCase("")) ) {
                int theLength = keywords.length;
                i = 0;
                int myChecker = 0;
                // This while loop checks if any keywords match any stored book
                while ( i < theLength ) {
                    if ( searchTheBooks.getDescription().toUpperCase().contains(keywords[i].toUpperCase()) ) {
                        myChecker = 1;
                    }
                    i = i + 1;
                }
                // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                if ( myChecker == 1 ) {
                    myChecker = 0;
                    // This if condition is used when the user enters one year
                    if ( years.length() == 4 ) {
                        // Parse the variable years to integer data type
                        int otherYear = Integer.parseInt ( years );
                        if ( otherYear == searchTheBooks.getYear() ) {
                            System.out.println ( "----------The following infomation matches the entered keyword(s) and year----------");
                            System.out.println ( "ID: " + searchTheBooks.getProductID() );
                            System.out.println ( "Description: " + searchTheBooks.getDescription() );
                            System.out.println ( "Year: " + searchTheBooks.getYear() );
                            if ( searchTheBooks.getPrice() == 0 ) {
                                System.out.println ( "Price: N/A" );
                            }
                            else {
                                System.out.println ( "Price: " + searchTheBooks.getPrice() );
                            }
                            if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                                System.out.println ( "Authors: N/A" );
                            }
                            else {
                                System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                            }
                            if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                                System.out.println ( "Publisher: N/A" );
                            }
                            else {
                                System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                            }
                        }
                    }
                    // This if condition is used when the user enters a year with a '-' at the beginning
                    else if ( years.length() == 5 && years.indexOf ('-') == 0 ) {
                        String yearsTwo = years;
                        int editYears = searchTheBooks.getYear();
                        yearsTwo = yearsTwo.replaceAll ( "\\D", "");
                        // Parse the variable yearsTwo to integer data type
                        int otherYear = Integer.parseInt ( yearsTwo );
                        while ( otherYear != 999 ) {
                            if ( otherYear == editYears ) {
                                System.out.println ( "----------The following infomation matches the entered keyword(s) and year----------");
                                System.out.println ( "ID: " + searchTheBooks.getProductID() );
                                System.out.println ( "Description: " + searchTheBooks.getDescription() );
                                System.out.println ( "Year: " + searchTheBooks.getYear() );
                                if ( searchTheBooks.getPrice() == 0 ) {
                                    System.out.println ( "Price: N/A" );
                                }
                                else {
                                    System.out.println ( "Price: " + searchTheBooks.getPrice() );
                                }
                                if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Authors: N/A" );
                                }
                                else {
                                    System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                                }
                                if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Publisher: N/A" );
                                }
                                else {
                                    System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                                }
                            }
                            otherYear = otherYear - 1;;
                        }
    
                    }
                    // This if condition checks if the user enters a year with a '-' at the end
                    else if ( years.length() == 5 && years.indexOf ('-') == 4 ) {
                        String yearsTwo = years;
                        int editYears = searchTheBooks.getYear();
                        yearsTwo = yearsTwo.replaceAll ( "\\D", "");
                        // Parse the variable yearsTwo to integer data type
                        int otherYear = Integer.parseInt ( yearsTwo );
                        while ( otherYear != 10000 ) {
                            if ( otherYear == editYears ) {
                                System.out.println ( "----------The following infomation matches the entered keyword(s) and year----------");
                                System.out.println ( "ID: " + searchTheBooks.getProductID() );
                                System.out.println ( "Description: " + searchTheBooks.getDescription() );
                                System.out.println ( "Year: " + searchTheBooks.getYear() );
                                if ( searchTheBooks.getPrice() == 0 ) {
                                    System.out.println ( "Price: N/A" );
                                }
                                else {
                                    System.out.println ( "Price: " + searchTheBooks.getPrice() );
                                }
                                if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Authors: N/A" );
                                }
                                else {
                                    System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                                }
                                if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Publisher: N/A" );
                                }
                                else {
                                    System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                                }
                            }
                            otherYear = otherYear + 1;;
                        }
                    }
                    // This if condition checks is the user has entered two years seperatde by a '-'
                    else if ( years.length() == 9 && years.indexOf ('-') == 4 ) {
                        String yearsTwo = years;
                        int editYears = searchTheBooks.getYear();
                        String other1[] = yearsTwo.split ("-");
                        // Parse the variable other1[0] to integer data type
                        int year1 = Integer.parseInt ( other1[0] );
                        // Parse the variable other1[1] to integer data type
                        int year2 = Integer.parseInt ( other1[1] );
                        while ( year1 != year2 + 1 ) {
                            if ( year1 == editYears ) {
                                System.out.println ( "----------The following infomation matches the entered keyword(s) and year----------");
                                System.out.println ( "ID: " + searchTheBooks.getProductID() );
                                System.out.println ( "Description: " + searchTheBooks.getDescription() );
                                System.out.println ( "Year: " + searchTheBooks.getYear() );
                                if ( searchTheBooks.getPrice() == 0 ) {
                                    System.out.println ( "Price: N/A" );
                                }
                                else {
                                    System.out.println ( "Price: " + searchTheBooks.getPrice() );
                                }
                                if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Authors: N/A" );
                                }
                                else {
                                    System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                                }
                                if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Publisher: N/A" );
                                }
                                else {
                                    System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                                }
                            }
                            year1 = year1 + 1;;
                        }
                    }
                }
            }

            // This if condition is used if the user enters a productID, keyword(s) and a time period to be searched
            else if ( ID != 1 && (keywords.length != 1 || !(keywords[0].equalsIgnoreCase(""))) && !(years.equalsIgnoreCase(""))) {
                if ( ID == searchTheBooks.getProductID() ) {
                    int theLength = keywords.length;
                    i = 0;
                    int myChecker = 0;
                    // This while loop checks if any keywords match any stored book
                    while ( i < theLength ) {
                        if ( searchTheBooks.getDescription().toUpperCase().contains(keywords[i].toUpperCase()) ) {
                            myChecker = 1;
                        }
                        i = i + 1;
                    }
                    // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                    if ( myChecker == 1 ) {
                        myChecker = 0;
                        // This if condition is used when the user enters one year
                        if ( years.length() == 4 ) {

                            // Parse the variable years to integer data type
                            int otherYear = Integer.parseInt ( years );
                            if ( otherYear == searchTheBooks.getYear() ) {
                                System.out.println ( "----------The following infomation matches the entered productID, keyword(s) and year----------");
                                System.out.println ( "ID: " + searchTheBooks.getProductID() );
                                System.out.println ( "Description: " + searchTheBooks.getDescription() );
                                System.out.println ( "Year: " + searchTheBooks.getYear() );
                                if ( searchTheBooks.getPrice() == 0 ) {
                                    System.out.println ( "Price: N/A" );
                                }
                                else {
                                    System.out.println ( "Price: " + searchTheBooks.getPrice() );
                                }
                                if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Authors: N/A" );
                                }
                                else {
                                    System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                                }
                                if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Publisher: N/A" );
                                }
                                else {
                                    System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                                }
                            }
                        }
                        // This if condition is used when the user enters a year with a '-' at the beginning
                        else if ( years.length() == 5 && years.indexOf ('-') == 0 ) {
                            String yearsTwo = years;
                            int editYears = searchTheBooks.getYear();
                            yearsTwo = yearsTwo.replaceAll ( "\\D", "");
                            // Parse the variable yearsTwo to integer data type
                            int otherYear = Integer.parseInt ( yearsTwo );
                            while ( otherYear != 999 ) {
                                if ( otherYear == editYears ) {
                                    System.out.println ( "----------The following infomation matches the entered productID, keyword(s) and year----------");
                                    System.out.println ( "ID: " + searchTheBooks.getProductID() );
                                    System.out.println ( "Description: " + searchTheBooks.getDescription() );
                                    System.out.println ( "Year: " + searchTheBooks.getYear() );
                                    if ( searchTheBooks.getPrice() == 0 ) {
                                        System.out.println ( "Price: N/A" );
                                    }
                                    else {
                                        System.out.println ( "Price: " + searchTheBooks.getPrice() );
                                    }
                                    if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                                        System.out.println ( "Authors: N/A" );
                                    }
                                    else {
                                        System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                                    }
                                    if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                                        System.out.println ( "Publisher: N/A" );
                                    }
                                    else {
                                        System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                                    }
                                }
                                otherYear = otherYear - 1;;
                            }
        
                        }
                        // This if condition checks if the user enters a year with a '-' at the end
                        else if ( years.length() == 5 && years.indexOf ('-') == 4 ) {
                            String yearsTwo = years;
                            int editYears = searchTheBooks.getYear();
                            yearsTwo = yearsTwo.replaceAll ( "\\D", "");
                            // Parse the variable years to integer data type
                            int otherYear = Integer.parseInt ( yearsTwo );
                            while ( otherYear != 10000 ) {
                                if ( otherYear == editYears ) {
                                    System.out.println ( "----------The following infomation matches the entered productID, keyword(s) and year----------");
                                    System.out.println ( "ID: " + searchTheBooks.getProductID() );
                                    System.out.println ( "Description: " + searchTheBooks.getDescription() );
                                    System.out.println ( "Year: " + searchTheBooks.getYear() );
                                    if ( searchTheBooks.getPrice() == 0 ) {
                                        System.out.println ( "Price: N/A" );
                                    }
                                    else {
                                        System.out.println ( "Price: " + searchTheBooks.getPrice() );
                                    }
                                    if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                                        System.out.println ( "Authors: N/A" );
                                    }
                                    else {
                                        System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                                    }
                                    if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                                        System.out.println ( "Publisher: N/A" );
                                    }
                                    else {
                                        System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                                    }
                                }
                                otherYear = otherYear + 1;;
                            }
                        }
                        // This if condition checks is the user has entered two years seperatde by a '-'
                        else if ( years.length() == 9 && years.indexOf ('-') == 4 ) {
                            String yearsTwo = years;
                            int editYears = searchTheBooks.getYear();
                            String other1[] = yearsTwo.split ("-");
                            // Parse the variable other1[0] to integer data type
                            int year1 = Integer.parseInt ( other1[0] );
                            // Parse the variable other1[1] to integer data type
                            int year2 = Integer.parseInt ( other1[1] );
                            while ( year1 != year2 + 1 ) {
                                if ( year1 == editYears ) {
                                    System.out.println ( "----------The following infomation matches the entered productID, keyword(s) and year----------");
                                    System.out.println ( "ID: " + searchTheBooks.getProductID() );
                                    System.out.println ( "Description: " + searchTheBooks.getDescription() );
                                    System.out.println ( "Year: " + searchTheBooks.getYear() );
                                    if ( searchTheBooks.getPrice() == 0 ) {
                                        System.out.println ( "Price: N/A" );
                                    }
                                    else {
                                        System.out.println ( "Price: " + searchTheBooks.getPrice() );
                                    }
                                    if ( searchTheBooks.getAuthors().equalsIgnoreCase ("") ) {
                                        System.out.println ( "Authors: N/A" );
                                    }
                                    else {
                                        System.out.println ( "Authors: " + searchTheBooks.getAuthors() );
                                    }
                                    if ( searchTheBooks.getPublisher().equalsIgnoreCase ("") ) {
                                        System.out.println ( "Publisher: N/A" );
                                    }
                                    else {
                                        System.out.println ( "Publisher: " + searchTheBooks.getPublisher() );
                                    }
                                }
                                year1 = year1 + 1;;
                            }
                        }
                    }
                }
            }

        }

        for ( Electronics searchTheElectronics : electronicsList ) {

            // This if condition is used if the user leaves all search fields blank
            if ( ID == 1 && (keywords.length == 1 && keywords[0].equalsIgnoreCase("")) && years.equalsIgnoreCase("") ) {
                System.out.println ( "----------The following infomation is present in the store----------");
                System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                System.out.println ( "Year: " + searchTheElectronics.getYear() );
                if ( searchTheElectronics.getPrice() == 0 ) {
                    System.out.println ( "Price: N/A" );
                }
                else {
                    System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                }
                if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                    System.out.println ( "Maker: N/A" );
                }
                else {
                    System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                }
            }

            // This if condition is used if the user enters a productID to be searched
            else if ( ID != 1 && (keywords.length == 1 && keywords[0].equalsIgnoreCase("")) && years.equalsIgnoreCase("") ) {
                if ( ID == searchTheElectronics.getProductID() ) {
                    System.out.println ( "----------The following infomation matches the entered productID----------");
                    System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                    System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                    System.out.println ( "Year: " + searchTheElectronics.getYear() );
                    if ( searchTheElectronics.getPrice() == 0 ) {
                        System.out.println ( "Price: N/A" );
                    }
                    else {
                        System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                    }
                    if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                        System.out.println ( "Maker: N/A" );
                    }
                    else {
                        System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                    }
                }
            }

            // This if condition is used if the user enters a keyword(s) to be searched
            else if ( ID == 1 && (keywords.length != 1 || !(keywords[0].equalsIgnoreCase(""))) && years.equalsIgnoreCase("") ) {
                int theLength = keywords.length;
                i = 0;
                int myChecker = 0;
                // This while loop checks if any keywords match any stored book
                while ( i < theLength ) {
                    if ( searchTheElectronics.getDescription().toUpperCase().contains(keywords[i].toUpperCase()) ) {
                        myChecker = 1;
                    }
                    i = i + 1;
                }
                // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                if ( myChecker == 1) {
                    myChecker = 0;
                    System.out.println ( "----------The following infomation matches the entered keyword(s)----------");
                    System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                    System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                    System.out.println ( "Year: " + searchTheElectronics.getYear() );
                    if ( searchTheElectronics.getPrice() == 0 ) {
                        System.out.println ( "Price: N/A" );
                    }
                    else {
                        System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                    }
                    if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                        System.out.println ( "Maker: N/A" );
                    }
                    else {
                        System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                    }
                }
            }

            // This if condition is used if the user enters a time period to be searched
            else if ( ID == 1 && (keywords.length == 1 && keywords[0].equalsIgnoreCase("")) && !(years.equalsIgnoreCase("") ) ) {
                // This if condition is used when the user enters one year
                if ( years.length() == 4 ) {
                    // Parse the variable years to integer data type
                    int otherYear = Integer.parseInt ( years );
                    if ( otherYear == searchTheElectronics.getYear() ) {
                        System.out.println ( "----------The following infomation matches the entered year----------");
                        System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                        System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                        System.out.println ( "Year: " + searchTheElectronics.getYear() );
                        if ( searchTheElectronics.getPrice() == 0 ) {
                            System.out.println ( "Price: N/A" );
                        }
                        else {
                            System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                        }
                        if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                            System.out.println ( "Maker: N/A" );
                        }
                        else {
                            System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                        }
                    }
                }
                // This if condition is used when the user enters a year with a '-' at the beginning
                else if ( years.length() == 5 && years.indexOf ('-') == 0 ) {
                    String yearsTwo = years;
                    int editYears = searchTheElectronics.getYear();
                    yearsTwo = yearsTwo.replaceAll ( "\\D", "");
                    // Parse the variable yearsTwo to integer data type
                    int otherYear = Integer.parseInt ( yearsTwo );
                    while ( otherYear != 999 ) {
                        if ( otherYear == editYears ) {
                            System.out.println ( "----------The following infomation matches the entered year----------");
                            System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                            System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                            System.out.println ( "Year: " + searchTheElectronics.getYear() );
                            if ( searchTheElectronics.getPrice() == 0 ) {
                                System.out.println ( "Price: N/A" );
                            }
                            else {
                                System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                            }
                            if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                                System.out.println ( "Maker: N/A" );
                            }
                            else {
                                System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                            }
                        }
                        otherYear = otherYear - 1;;
                    }

                }
                // This if condition checks if the user enters a year with a '-' at the end
                else if ( years.length() == 5 && years.indexOf ('-') == 4 ) {
                    String yearsTwo = years;
                    int editYears = searchTheElectronics.getYear();
                    yearsTwo = yearsTwo.replaceAll ( "\\D", "");
                    // Parse the variable yearsTwo to integer data type
                    int otherYear = Integer.parseInt ( yearsTwo );
                    while ( otherYear != 10000 ) {
                        if ( otherYear == editYears ) {
                            System.out.println ( "----------The following infomation matches the entered year----------");
                            System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                            System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                            System.out.println ( "Year: " + searchTheElectronics.getYear() );
                            if ( searchTheElectronics.getPrice() == 0 ) {
                                System.out.println ( "Price: N/A" );
                            }
                            else {
                                System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                            }
                            if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                                System.out.println ( "Maker: N/A" );
                            }
                            else {
                                System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                            }
                        }
                        otherYear = otherYear + 1;;
                    }
                }
                // This if condition checks is the user has entered two years seperatde by a '-'
                else if ( years.length() == 9 && years.indexOf ('-') == 4 ) {
                    String yearsTwo = years;
                    int editYears = searchTheElectronics.getYear();
                    String other[] = yearsTwo.split ("-");
                    // Parse the variable other[0] to integer data type
                    int year1 = Integer.parseInt ( other[0] );
                    // Parse the variable other[1] to integer data type
                    int year2 = Integer.parseInt ( other[1] );
                    while ( year1 != year2 + 1 ) {
                        if ( year1 == editYears ) {
                            System.out.println ( "----------The following infomation matches the entered year----------");
                            System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                            System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                            System.out.println ( "Year: " + searchTheElectronics.getYear() );
                            if ( searchTheElectronics.getPrice() == 0 ) {
                                System.out.println ( "Price: N/A" );
                            }
                            else {
                                System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                            }
                            if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                                System.out.println ( "Maker: N/A" );
                            }
                            else {
                                System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                            }
                        }
                        year1 = year1 + 1;;
                    }
                }
            }

            // This if condition is used if the user enters a productID and a time period to be searched
            else if ( ID != 1 && (keywords.length == 1 && keywords[0].equalsIgnoreCase("")) && !(years.equalsIgnoreCase("")) ) {
                if ( ID == searchTheElectronics.getProductID() ) {
                    // This if condition is used when the user enters one year
                    if ( years.length() == 4 ) {
                        // Parse the variable years to integer data type
                        int otherYear = Integer.parseInt ( years );
                        if ( otherYear == searchTheElectronics.getYear() ) {
                            System.out.println ( "----------The following infomation matches the entered productID and year----------");
                            System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                            System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                            System.out.println ( "Year: " + searchTheElectronics.getYear() );
                            if ( searchTheElectronics.getPrice() == 0 ) {
                                System.out.println ( "Price: N/A" );
                            }
                            else {
                                System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                            }
                            if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                                System.out.println ( "Maker: N/A" );
                            }
                            else {
                                System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                            }
                        }
                    }
                    // This if condition is used when the user enters a year with a '-' at the beginning
                    else if ( years.length() == 5 && years.indexOf ('-') == 0 ) {
                        String yearsTwo = years;
                        int editYears = searchTheElectronics.getYear();
                        yearsTwo = yearsTwo.replaceAll ( "\\D", "");
                        // Parse the variable yeasTwo to integer data type
                        int otherYear = Integer.parseInt ( yearsTwo );
                        while ( otherYear != 999 ) {
                            if ( otherYear == editYears ) {
                                System.out.println ( "----------The following infomation matches the entered productID and year----------");
                                System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                                System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                                System.out.println ( "Year: " + searchTheElectronics.getYear() );
                                if ( searchTheElectronics.getPrice() == 0 ) {
                                    System.out.println ( "Price: N/A" );
                                }
                                else {
                                    System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                                }
                                if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Maker: N/A" );
                                }
                                else {
                                    System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                                }
                            }
                            otherYear = otherYear - 1;;
                        }
    
                    }
                    // This if condition checks if the user enters a year with a '-' at the end
                    else if ( years.length() == 5 && years.indexOf ('-') == 4 ) {
                        String yearsTwo = years;
                        int editYears = searchTheElectronics.getYear();
                        yearsTwo = yearsTwo.replaceAll ( "\\D", "");
                        // Parse the variable yearsTwo to integer data type
                        int otherYear = Integer.parseInt ( yearsTwo );
                        while ( otherYear != 10000 ) {
                            if ( otherYear == editYears ) {
                                System.out.println ( "----------The following infomation matches the entered productID and year----------");
                                System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                                System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                                System.out.println ( "Year: " + searchTheElectronics.getYear() );
                                if ( searchTheElectronics.getPrice() == 0 ) {
                                    System.out.println ( "Price: N/A" );
                                }
                                else {
                                    System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                                }
                                if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Maker: N/A" );
                                }
                                else {
                                    System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                                }
                            }
                            otherYear = otherYear + 1;;
                        }
                    }
                    // This if condition checks is the user has entered two years seperatde by a '-'
                    else if ( years.length() == 9 && years.indexOf ('-') == 4 ) {
                        String yearsTwo = years;
                        int editYears = searchTheElectronics.getYear();
                        String other[] = yearsTwo.split ("-");
                        // Parse the variable other[0] to integer data type
                        int year1 = Integer.parseInt ( other[0] );
                        // Parse the variable other[1] to integer data type
                        int year2 = Integer.parseInt ( other[1] );
                        while ( year1 != year2 + 1 ) {
                            if ( year1 == editYears ) {
                                System.out.println ( "----------The following infomation matches the entered productID and year----------");
                                System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                                System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                                System.out.println ( "Year: " + searchTheElectronics.getYear() );
                                if ( searchTheElectronics.getPrice() == 0 ) {
                                    System.out.println ( "Price: N/A" );
                                }
                                else {
                                    System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                                }
                                if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Maker: N/A" );
                                }
                                else {
                                    System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                                }
                            }
                            year1 = year1 + 1;;
                        }
                    }
                }
            }

            // This if condition is used if the user enters a productID and a keyword(s) to be searched
            else if ( ID != 1 && (keywords.length != 1 || !(keywords[0].equalsIgnoreCase(""))) && years.equalsIgnoreCase("") ) {
                if ( ID == searchTheElectronics.getProductID() ) {
                    int theLength = keywords.length;
                    i = 0;
                    int myChecker = 0;
                    // This while loop checks if any keywords match any stored book
                    while ( i < theLength ) {
                        if ( searchTheElectronics.getDescription().toUpperCase().contains(keywords[i].toUpperCase()) ) {
                            myChecker = 1;
                        }
                        i = i + 1;
                    }
                    // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                    if ( myChecker == 1 ) {
                        myChecker = 0;
                        System.out.println ( "----------The following infomation matches the entered productID and keyworad(s)----------");
                        System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                        System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                        System.out.println ( "Year: " + searchTheElectronics.getYear() );
                        if ( searchTheElectronics.getPrice() == 0 ) {
                            System.out.println ( "Price: N/A" );
                        }
                        else {
                            System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                        }
                        if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                            System.out.println ( "Maker: N/A" );
                        }
                        else {
                            System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                        }
                    }
                }
            }

            // This if condition is used if the user enters a productID, keyword(s) and a time period to be searched
            else if ( ID == 1 && (keywords.length != 1 || !(keywords[0].equalsIgnoreCase(""))) && !(years.equalsIgnoreCase("")) ) {
                int theLength = keywords.length;
                i = 0;
                int myChecker = 0;
                // This while loop checks if any keywords match any stored book
                while ( i < theLength ) {
                    if ( searchTheElectronics.getDescription().toUpperCase().contains(keywords[i].toUpperCase()) ) {
                        myChecker = 1;
                    }
                    i = i + 1;
                }
                // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                if ( myChecker == 1 ) {
                    myChecker = 0;
                    // This if condition is used when the user enters one year
                    if ( years.length() == 4 ) {
                        // Parse the variable years to integer data type
                        int otherYear = Integer.parseInt ( years );
                        if ( otherYear == searchTheElectronics.getYear() ) {
                            System.out.println ( "----------The following infomation matches the entered productID and keyworad(s)----------");
                            System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                            System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                            System.out.println ( "Year: " + searchTheElectronics.getYear() );
                            if ( searchTheElectronics.getPrice() == 0 ) {
                                System.out.println ( "Price: N/A" );
                            }
                            else {
                                System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                            }
                            if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                                System.out.println ( "Maker: N/A" );
                            }
                            else {
                                System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                            }
                        }
                    }
                    // This if condition is used when the user enters a year with a '-' at the beginning
                    else if ( years.length() == 5 && years.indexOf ('-') == 0 ) {
                        String yearsTwo = years;
                        int editYears = searchTheElectronics.getYear();
                        yearsTwo = yearsTwo.replaceAll ( "\\D", "");
                        // Parse the variable yearsTwo to integer data type
                        int otherYear = Integer.parseInt ( yearsTwo );
                        while ( otherYear != 999 ) {
                            if ( otherYear == editYears ) {
                                System.out.println ( "----------The following infomation matches the entered productID and keyworad(s)----------");
                                System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                                System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                                System.out.println ( "Year: " + searchTheElectronics.getYear() );
                                if ( searchTheElectronics.getPrice() == 0 ) {
                                    System.out.println ( "Price: N/A" );
                                }
                                else {
                                    System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                                }
                                if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Maker: N/A" );
                                }
                                else {
                                    System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                                }
                            }
                            otherYear = otherYear - 1;;
                        }
    
                    }
                    // This if condition checks if the user enters a year with a '-' at the end
                    else if ( years.length() == 5 && years.indexOf ('-') == 4 ) {
                        String yearsTwo = years;
                        int editYears = searchTheElectronics.getYear();
                        yearsTwo = yearsTwo.replaceAll ( "\\D", "");
                        // Parse the variable yearsTwo to integer data type
                        int otherYear = Integer.parseInt ( yearsTwo );
                        while ( otherYear != 10000 ) {
                            if ( otherYear == editYears ) {
                                System.out.println ( "----------The following infomation matches the entered productID and keyworad(s)----------");
                                System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                                System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                                System.out.println ( "Year: " + searchTheElectronics.getYear() );
                                if ( searchTheElectronics.getPrice() == 0 ) {
                                    System.out.println ( "Price: N/A" );
                                }
                                else {
                                    System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                                }
                                if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Maker: N/A" );
                                }
                                else {
                                    System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                                }
                            }
                            otherYear = otherYear + 1;;
                        }
                    }
                    // This if condition checks is the user has entered two years seperatde by a '-'
                    else if ( years.length() == 9 && years.indexOf ('-') == 4 ) {
                        String yearsTwo = years;
                        int editYears = searchTheElectronics.getYear();
                        String other1[] = yearsTwo.split ("-");
                        // Parse the variable other1[0] to integer data type
                        int year1 = Integer.parseInt ( other1[0] );
                        // Parse the variable other1[1] to integer data type
                        int year2 = Integer.parseInt ( other1[1] );
                        while ( year1 != year2 + 1 ) {
                            if ( year1 == editYears ) {
                                System.out.println ( "----------The following infomation matches the entered productID and keyworad(s)----------");
                                System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                                System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                                System.out.println ( "Year: " + searchTheElectronics.getYear() );
                                if ( searchTheElectronics.getPrice() == 0 ) {
                                    System.out.println ( "Price: N/A" );
                                }
                                else {
                                    System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                                }
                                if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Maker: N/A" );
                                }
                                else {
                                    System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                                }
                            }
                            year1 = year1 + 1;;
                        }
                    }
                }
            }

            else if ( ID != 1 && (keywords.length != 1 || !(keywords[0].equalsIgnoreCase(""))) && !(years.equalsIgnoreCase(""))) {
                if ( ID == searchTheElectronics.getProductID() ) {
                    int theLength = keywords.length;
                    i = 0;
                    int myChecker = 0;
                    // This while loop checks if any keywords match any stored book
                    while ( i < theLength ) {
                        if ( searchTheElectronics.getDescription().toUpperCase().contains(keywords[i].toUpperCase()) ) {
                            myChecker = 1;
                        }
                        i = i + 1;
                    }
                    // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                    if ( myChecker == 1 ) {
                        myChecker = 0;
                        // This if condition is used when the user enters one year
                        if ( years.length() == 4 ) {
                            // Parse the variable years to integer data type
                            int otherYear = Integer.parseInt ( years );
                            if ( otherYear == searchTheElectronics.getYear() ) {
                                System.out.println ( "----------The following infomation matches the entered productID, keyworad(s) and year----------");
                                System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                                System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                                System.out.println ( "Year: " + searchTheElectronics.getYear() );
                                if ( searchTheElectronics.getPrice() == 0 ) {
                                    System.out.println ( "Price: N/A" );
                                }
                                else {
                                    System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                                }
                                if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                                    System.out.println ( "Maker: N/A" );
                                }
                                else {
                                    System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                                }
                            }
                        }
                        // This if condition is used when the user enters a year with a '-' at the beginning
                        else if ( years.length() == 5 && years.indexOf ('-') == 0 ) {
                            String yearsTwo = years;
                            int editYears = searchTheElectronics.getYear();
                            yearsTwo = yearsTwo.replaceAll ( "\\D", "");
                            // Parse the variable yearsTwo to integer data type
                            int otherYear = Integer.parseInt ( yearsTwo );
                            while ( otherYear != 999 ) {
                                if ( otherYear == editYears ) {
                                    System.out.println ( "----------The following infomation matches the entered productID, keyworad(s) and year----------");
                                    System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                                    System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                                    System.out.println ( "Year: " + searchTheElectronics.getYear() );
                                    if ( searchTheElectronics.getPrice() == 0 ) {
                                        System.out.println ( "Price: N/A" );
                                    }
                                    else {
                                        System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                                    }
                                    if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                                        System.out.println ( "Maker: N/A" );
                                    }
                                    else {
                                        System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                                    }
                                }
                                otherYear = otherYear - 1;;
                            }
        
                        }
                        // This if condition checks if the user enters a year with a '-' at the end
                        else if ( years.length() == 5 && years.indexOf ('-') == 4 ) {
                            String yearsTwo = years;
                            int editYears = searchTheElectronics.getYear();
                            yearsTwo = yearsTwo.replaceAll ( "\\D", "");
                            // Parse the variable yearsTwo to integer data type
                            int otherYear = Integer.parseInt ( yearsTwo );
                            while ( otherYear != 10000 ) {
                                if ( otherYear == editYears ) {
                                    System.out.println ( "----------The following infomation matches the entered productID, keyworad(s) and year----------");
                                    System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                                    System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                                    System.out.println ( "Year: " + searchTheElectronics.getYear() );
                                    if ( searchTheElectronics.getPrice() == 0 ) {
                                        System.out.println ( "Price: N/A" );
                                    }
                                    else {
                                        System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                                    }
                                    if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                                        System.out.println ( "Maker: N/A" );
                                    }
                                    else {
                                        System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                                    }
                                }
                                otherYear = otherYear + 1;;
                            }
                        }
                        // This if condition checks is the user has entered two years seperatde by a '-'
                        else if ( years.length() == 9 && years.indexOf ('-') == 4 ) {
                            String yearsTwo = years;
                            int editYears = searchTheElectronics.getYear();
                            String other1[] = yearsTwo.split ("-");
                            // Parse the variable other1[0] to integer data type
                            int year1 = Integer.parseInt ( other1[0] );
                            // Parse the variable other1[1] to integer data type
                            int year2 = Integer.parseInt ( other1[1] );
                            while ( year1 != year2 + 1 ) {
                                if ( year1 == editYears ) {
                                    System.out.println ( "----------The following infomation matches the entered productID, keyworad(s) and year----------");
                                    System.out.println ( "ID: " + searchTheElectronics.getProductID() );
                                    System.out.println ( "Description: " + searchTheElectronics.getDescription() );
                                    System.out.println ( "Year: " + searchTheElectronics.getYear() );
                                    if ( searchTheElectronics.getPrice() == 0 ) {
                                        System.out.println ( "Price: N/A" );
                                    }
                                    else {
                                        System.out.println ( "Price: " + searchTheElectronics.getPrice() );
                                    }
                                    if ( searchTheElectronics.getMaker().equalsIgnoreCase ("") ) {
                                        System.out.println ( "Maker: N/A" );
                                    }
                                    else {
                                        System.out.println ( "Maker: " + searchTheElectronics.getMaker() );
                                    }
                                }
                                year1 = year1 + 1;;
                            }
                        }
                    }
                }
            }

        }

    }

    /**
     * This method is used to add a new book to the store.
     * The method prompts the user to enter all the required fields for a new book.
     * The method does not allow duplicate productIDs to be entered
     * The method does not allow the productID, description or year fields to be left blank.
     * Once all feilds have been correctly entered by the user, a new book will be added and saved to the store
     * 
     * @param myBook
     */

    Scanner keyboardElectronic = new Scanner(System.in);
    Scanner keyboardBook = new Scanner(System.in);
    
    public void yourBook ( Books myBook ) {

        int theBookID = 0;
        String theDescription;
        int theYear;
        String thePrice;
        String theAuthors;
        String thePublisher;
        int i = 0;

        System.out.print ( "Please enter the required field productID, which must be 6 digits long: ");
        theBookID = keyboardBook.nextInt();

        System.out.print ( "\n" );

        // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
        while ( theBookID < 99999 || theBookID > 999999 ) {
            System.out.println ( "That is not a valid entry. Please enter the required field productID, which must be 6 digits long: ");
            theBookID = keyboardBook.nextInt();
        }

        allIDs.add (theBookID);
        i = allIDs.size()-2; 
        while ( i >= 0 ) {

            // This while loop checks if the user has entered a duplicate ID. If so they will be prompted to enter a valid option.
            while ( theBookID == (allIDs.get(i)) ) {
                System.out.println ( "You have entered a productID which has already been entered" );
                System.out.println ( "Please enter another productID, which must be 6 digits long: ");
                theBookID = keyboardBook.nextInt();

                // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
                while ( theBookID < 99999 || theBookID > 999999 ) {
                    System.out.println ( "That is not a valid entry. Please enter the required field productID, which must be 6 digits long: ");
                    theBookID = keyboardBook.nextInt();
                }
            }

            i = i - 1;

        }

        keyboardBook.nextLine();

        System.out.println ( "Please enter the required field description: ");
        theDescription = keyboardBook.nextLine();
        // This while loop checks if the user has left this field empty. If so they will be prompted to enter a valid option.
        while ( theDescription.equalsIgnoreCase ( "" ) ) {
            System.out.println ( "You cannot leave this field blank. Please enter the required field description: ");
            theDescription = keyboardBook.nextLine();
        }
        
        System.out.print ( "Please enter the required field year, which must be between 1000 and 9999: ");
        theYear = keyboardBook.nextInt();

        System.out.print ( "\n" );

        // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
        while ( theYear < 1000 || theYear > 9999 ) {
            System.out.println ( "That is not a valid entry. Please enter the required field year, which must be between 1000 and 9999: ");
            theYear = keyboardBook.nextInt();
        }

        keyboardBook.nextLine();

        System.out.println ( "Enter price, or leave blank: ");
        thePrice = keyboardBook.nextLine();
        if ( thePrice.equalsIgnoreCase ( "" ) ) {
            thePrice = "0";
        }

        // Parse the variable thePrice to double data type
        double thePriceDouble = Double.parseDouble (thePrice);


        System.out.println ( "Enter the authors, or leave blank: ");
        theAuthors = keyboardBook.nextLine();
        if ( theAuthors.equalsIgnoreCase ( "" ) ) {
            theAuthors = "";
        }

        System.out.println ( "Enter the publisher, or leave blank: ");
        thePublisher = keyboardBook.nextLine();
        if ( thePublisher.equalsIgnoreCase ( "" ) ) {
            thePublisher = "";
        }

        System.out.println ( "You have entered a new book.");

        Books myBooks = new Books ( theBookID, theDescription, theYear, thePriceDouble, theAuthors, thePublisher );
        bookList.add ( myBooks );

    }

    /**
     * This method is used to add a new electronic to the store.
     * The method prompts the user to enter all the required fields for a new electronic.
     * The method does not allow duplicate productIDs to be entered
     * The method does not allow the productID, description or year fields to be left blank.
     * Once all feilds have been correctly entered by the user, a new electronic will be added and saved to the store
     * 
     * @param myBook
     */
    public void yourElectronic ( Electronics myBook ){

        int theElectronicID = 0;
        String theDescription;
        int theYear;
        String thePrice;
        String theMaker;
        int i = 0;

        System.out.print ( "Please enter the required field productID, which must be 6 digits long: ");
        theElectronicID = keyboardElectronic.nextInt();

        System.out.print ( "\n" );

        // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
        while ( theElectronicID < 99999 || theElectronicID > 999999 ) {
            System.out.println ( "That is not a valid entry. Please enter the required field productID, which must be 6 digits long: ");
            theElectronicID = keyboardElectronic.nextInt();
        }

        allIDs.add (theElectronicID);
        i = allIDs.size()-2; 
        while ( i >= 0 ) {

            // This while loop checks if the user has entered a duplicate ID. If so they will be prompted to enter a valid option.
            while ( theElectronicID == (allIDs.get(i)) ) {
                System.out.println ( "You have entered a productID which has already been entered" );
                System.out.println ( "Please enter another productID, which must be 6 digits long: ");
                theElectronicID = keyboardElectronic.nextInt();

                // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
                while ( theElectronicID < 99999 || theElectronicID > 999999 ) {
                    System.out.println ( "That is not a valid entry. Please enter the required field productID, which must be 6 digits long: ");
                    theElectronicID = keyboardElectronic.nextInt();
                }
            }

            i = i - 1;

        }

        keyboardElectronic.nextLine();

        System.out.println ( "Please enter the required field description: ");
        theDescription = keyboardElectronic.nextLine();
        // This while loop checks if the user has left this field empty. If so they will be prompted to enter a valid option.
        while ( theDescription.equalsIgnoreCase ( "" ) ) {
            System.out.println ( "You cannot leave this field blank. Please enter the required field description: ");
            theDescription = keyboardElectronic.nextLine();
        }
        
        System.out.print ( "Please enter the required field year, which must be between 1000 and 9999: ");
        theYear = keyboardElectronic.nextInt();

        System.out.print ( "\n" );

        // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.        
        while ( theYear < 1000 || theYear > 9999 ) {
            System.out.println ( "That is not a valid entry. Please enter the required field year, which must be between 1000 and 9999: ");
            theYear = keyboardElectronic.nextInt();
        }

        keyboardElectronic.nextLine();

        System.out.println ( "Enter price, or leave blank: ");
        thePrice = keyboardElectronic.nextLine();
        if ( thePrice.equalsIgnoreCase ( "" ) ) {
            thePrice = "0";
        }

        // Parse the variable thePric to double data type
        double thePriceDouble = Double.parseDouble (thePrice);

        System.out.println ( "Enter the maker, or leave blank: ");
        theMaker = keyboardElectronic.nextLine();
        if ( theMaker.equalsIgnoreCase ( "" ) ) {
            theMaker = "";
     
        }

        Electronics myElectronic = new Electronics ( theElectronicID, theDescription, theYear, thePriceDouble, theMaker );
        electronicsList.add ( myElectronic );

        System.out.println ( "You have entered a new electronic.");

    }

}

class Electronics {
    
    private int productID;
    private String description;
    private int year;

    private double price;
    private String maker;

    /**
     * This constructor method initializes all instance variables for the Electronics class
     * 
     * @param productID
     * @param description
     * @param year
     * @param price
     * @param maker
     */
    public Electronics ( int productID, String description, int year, double price, String maker ) {
        this.productID = productID;
        this.description = description;
        this.year = year;
        this.price = price;
        this.maker = maker;
    }

    public Electronics() {
        productID = 0;
        description = "";
        year = 0;
        price = 0.0;
        maker = "";
    }

    /**
     * This method returns the productID
     * 
     * @return productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * This method returns the description
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method returns the year
     * 
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * This method returns the price
     * 
     * @return price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * This method returns the maker
     * 
     * @return maker
     */
    public String getMaker() {
        return maker;
    }



    /**
     * This method initializes the productID using the 'this' keyword
     * 
     * @param productID
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * This method initializes the description using the 'this' keyword
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method initializes the year using the 'this' keyword
     * 
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * This method initializes the price using the 'this' keyword
     * 
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * This method initializes the maker using the 'this' keyword
     * 
     * @param maker
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }

}

class Books {
    
    private int productID;
    private String description;
    private int year;

    private double price;
    private String authors;
    private String publisher;

    /**
     * This constructor method initializes all instance variables for the Books class
     * 
     * @param productID
     * @param description
     * @param year
     * @param price
     * @param authors
     * @param publisher
     */
    public Books ( int productID, String description, int year, double price, String authors, String publisher ) {
        this.productID = productID;
        this.description = description;
        this.year = year;
        this.price = price;
        this.authors = authors;
        this.publisher = publisher;
    }

	public Books() {
        productID = 0;
        description = "";
        year = 0;
        price = 0.0;
        authors = "";
        publisher = "";
    }

    /**
     * This method returns the productID
     * 
     * @return productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * This method returns the description
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method returns the year
     * 
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * This method returns the price
     * 
     * @return price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * This method returns the authors
     * 
     * @return authors
     */
    public String getAuthors() {
        return authors;
    }

    /**
     * This method returns the publisher
     * 
     * @return publisher
     */
    public String getPublisher() {
        return publisher;
    }


    /**
     * This method initializes the productID using the 'this' keyword
     * 
     * @param productID
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * This method initializes the description using the 'this' keyword
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method initializes the year using the 'this' keyword
     * 
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * This method initializes the price using the 'this' keyword
     * 
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * This method initializes the authors using the 'this' keyword
     * 
     * @param authors
     */
    public void setAuthors(String authors) {
        this.authors = authors;
    }

    /**
     * This method initializes the publisher using the 'this' keyword
     * 
     * @param publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}