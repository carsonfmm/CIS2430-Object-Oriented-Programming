package eStoreSearch;

import java.util.Scanner;

import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class theSearch {

    private Books myBookOb = new Books();
    private Electronics myElectronicOb = new Electronics();
    private ArrayList<Products> productList = new ArrayList<>();

    /**
     * This method uses the users search input to search through all books and electronics within the store.
     * This method takes all conditions into consideration and prints out any matches found accordingly.
     * 
     * @return ArrayList<String>
     * @param proID
     * @param keywordDes
     * @param years1
     * @param years2
     */
    //     public void yourSearch ( String ID, String[] keywords, String years ) {
    public ArrayList<String> yourSearch ( String proID, String keywordDes, String years1, String years2 ) {

        ArrayList<String> returnPrint = new ArrayList<>();

        String ID = proID;
        String years = "";

        if ( ID.equalsIgnoreCase ( "" ) ) {
            ID = "";
        }

        if ( !(ID.equalsIgnoreCase ( "" )) ) {
            // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
            if ( !( ID.length() == 6 || ID.equals("")) ) {
                    returnPrint.add ( "That is not a valid entry. Please enter a productID for the search request, which must be 6 digits long. NOTE: this field may be left blank.\n");
                    return returnPrint;
            }
        }

        String keywords[] = keywordDes.split ( "[ ]+");

        if ( !(years1.equals("-") && years1.equals("")) && years2.equals("")) {
            try {
                Integer.parseInt(years1);
            } catch ( Exception e ) {
                returnPrint.add ( "That is not a valid entry. The start year must be an integer.\n");
                return returnPrint;
            }
            years = years1;
        }
        else {
            if ( !(years1.equals("-") || years2.equals("-") ) ) {
                if ( !(years1.equals("") && years2.equals("")) ){
                    try {
                        int y1 = Integer.parseInt(years1);
                        int y2 = Integer.parseInt(years2);
                        if ( y1 > y2 ) {
                            returnPrint.add ( "That is not a valid entry. The start year can not be greater then the end year.\n");
                            return returnPrint;
                        }
                    } catch ( Exception e ) {
                        returnPrint.add ( "That is not a valid entry. The start year and end year must be integers and an end year cannot be entered without a start year.\n");
                        return returnPrint;
                    }
                }
            }

            int tester = 0;

            if (years1.equals("-")) {
                try {
                    Integer.parseInt(years2);
                    years = years1 + years2;
                    tester = 1;
                } catch ( Exception e ) {
                    returnPrint.add ( "That is not a valid entry. The end year must be an integer.\n");
                    return returnPrint;
                }
            }

            if (years2.equals("-")) {
                try {
                    Integer.parseInt(years1);
                    years = years1 + years2;
                    tester = 1;
                } catch ( Exception e ) {
                    returnPrint.add ( "That is not a valid entry. The start year must be an integer.\n");
                    return returnPrint;
                }
            }

            if ( !(years1.equals("") && years2.equals("")) && tester == 0 ){
                years = years1 + "-" + years2;
            }

            if ( (years1.equals("")) ) {
                years = years2;
            }
            if ( (years1.equals("")) && !(years2.equals("")) ) {
                returnPrint.add ( "That is not a valid entry. An end year cannot be entered without a start year.\n");
                return returnPrint;
            }

            if ( years.equalsIgnoreCase ( "" ) ) {
                years = "";
            }
            // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
            while ( !(years.equalsIgnoreCase ("") || years.length() == 4 || (years.length() == 5 && years.indexOf ('-') == 0) || (years.length() == 5 && years.indexOf ('-') == 4) || (years.length() == 9 && years.indexOf ('-') == 4) ) ) {
                returnPrint.add ( "That is not a valid entry. Please enter the time period for the search request. If a start year and end year are both included do not use the '-' symbol.  NOTE: either year fields may be left blank.\n");
                return returnPrint;
            }
        }

        int i = 0;

        // This if condition is used if the user leaves all search fields blank
        if ( ID.equals("") && (keywords.length == 1 && keywords[0].equalsIgnoreCase("")) && years.equalsIgnoreCase("") ) {
            for ( Products searchTheProducts : productList ) {
                returnPrint.add ( "----------The following infomation is present in the store----------");
                returnPrint.add (searchTheProducts.toString());
            }
            return returnPrint;
        }

        // This if condition is used if the user enters a productID to be searched
        else if ( !(ID.equals("")) && (keywords.length == 1 && keywords[0].equalsIgnoreCase("")) && years.equalsIgnoreCase("") ) {
            for ( Products searchTheProducts : productList ) {
                if ( ID.equals(searchTheProducts.getProductID()) ) {
                    returnPrint.add ( "----------The following infomation matches the entered productID----------");
                    returnPrint.add(searchTheProducts.toString());
                }
            }
            return returnPrint;
        }

        // This if condition is used if the user enters a keyword(s) to be searched            
        else if ( ID.equals("") && (keywords.length != 1 || !(keywords[0].equalsIgnoreCase(""))) && years.equalsIgnoreCase("") ) {
            HashMap<String, ArrayList<Integer>> index = new HashMap<String, ArrayList<Integer>>();
            i = 0;
            int counter = 0;
            int myChecker = 0;
            int m = 0;
            ArrayList<Integer> list = new ArrayList<Integer>();
            ArrayList<Integer> temp = new ArrayList<Integer>();
            ArrayList<Integer> finalList = new ArrayList<Integer>();

            // Add all unique description words to a HashMap and add their indicies
            for ( Products searchingMyList : productList ) {
                // Split the description into seperate words and convert them to all upper case
                String other[] = (searchingMyList.getDescription().toUpperCase().split("[ ]+"));
                for ( int k = 0; k < other.length; k++ ) {
                    // If a word is already a key, add the index of that word to the existing key
                    if ( (index.containsKey(other[k])) ) {
                        list = index.get(other[k]);
                        list.add(counter);
                        index.put(other[k], list );
                        list = new ArrayList<Integer>();
                    }
                    // Create a new key for the new word and add its index
                    else {
                        list.add(counter);
                        index.put(other[k], list );
                        list = new ArrayList<Integer>();
                    }
                }
                counter = counter + 1;
            }

            int theTester = 0;
            int otherTester = 0;
            int lastTester = 0;
            // If the user enters more than one keyword to be searched
            if ( keywords.length != 1 ) {
                while ( theTester < keywords.length ) {
                    // Compare the users input with the HashMap
                    if ( lastTester == 0 ) {
                        if ( index.containsKey(keywords[lastTester].toUpperCase()) ) {
                            list = index.get(keywords[lastTester].toUpperCase());
                            myChecker = 1;
                        }
                        if ( index.containsKey(keywords[lastTester+1].toUpperCase()) ) {
                            temp = index.get(keywords[lastTester+1].toUpperCase());
                            myChecker = 1;
                        }
                        lastTester = lastTester + 1;
                    }

                    // Compare the users input with the HashMap
                    for ( int myIndex1 = 0; myIndex1 < list.size(); myIndex1++ ) {
                        for ( int myIndex2 = 0; myIndex2 < temp.size(); myIndex2++ ) {
                            if ( list.get(myIndex1) == temp.get(myIndex2)) {
                                myChecker = 1;
                                finalList.add(list.get(myIndex1));
                            }
                        }
                        otherTester = otherTester + 1;
                    }

                    if ( keywords.length == 2 ) {
                        list = finalList;
                        temp = new ArrayList<Integer>();
                        theTester = theTester + 1;
                    }

                    else {
                        list = finalList;
                        temp = index.get(keywords[lastTester+1].toUpperCase());
                        finalList = new ArrayList<>();
                        theTester = theTester + 1;
                    }

                }

                // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                if ( myChecker == 1) {
                    while ( m < finalList.size() ) {
                        myChecker = 0;
                        Products printer = productList.get(finalList.get(m));
                        myChecker = 2;
                        if ( myChecker == 2 ) {
                            returnPrint.add ( "----------The following infomation matches the entered keyword(s)----------");
                            returnPrint.add(printer.toString());
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
                    return returnPrint;
                }
                i = i + 1;

            }

            else {
                if ( index.containsKey(keywords[i].toUpperCase()) ) {
                    list = index.get(keywords[i].toUpperCase());
                    myChecker = 1;
                }

                // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                if ( myChecker == 1) {
                    while ( m < list.size() ) {
                        myChecker = 0;
                        Products printer = productList.get(list.get(m));
                        myChecker = 2;
                        if ( myChecker == 2 ) {
                            returnPrint.add ( "----------The following infomation matches the entered keyword(s)----------");
                            returnPrint.add(printer.toString());
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
                    return returnPrint;
                }
                i = i + 1;
            }
            
        }

        // This if condition is used if the user enters a time period to be searched
        else if ( ID.equals("") && (keywords.length == 1 && keywords[0].equalsIgnoreCase("")) && !(years.equalsIgnoreCase("") ) ) {
            for ( Products searchTheProducts : productList ) {
                // This if condition is used when the user enters one year
                if ( years.length() == 4 ) {

                    // Parse the variable years to integer data type
                    int otherYear = Integer.parseInt ( years );
                    if ( otherYear == searchTheProducts.getYear() ) {
                        returnPrint.add ( "----------The following infomation matches the entered year----------");
                        returnPrint.add(searchTheProducts.toString());
                    }
                }
                // This if condition is used when the user enters a year with a '-' at the beginning
                else if ( years.length() == 5 && years.indexOf ('-') == 0 ) {
                    String yearsTwo = years;
                    int editYears = searchTheProducts.getYear();
                    yearsTwo = yearsTwo.replaceAll ( "\\D", "");

                    // Parse the variable yearsTwo to integer data type
                    int otherYear = Integer.parseInt ( yearsTwo );
                    while ( otherYear != 999 ) {
                        if ( otherYear == editYears ) {
                            returnPrint.add ( "----------The following infomation matches the entered year----------");
                            returnPrint.add(searchTheProducts.toString());
                        }
                        otherYear = otherYear - 1;;
                    }

                }
                // This if condition checks if the user enters a year with a '-' at the end
                else if ( years.length() == 5 && years.indexOf ('-') == 4 ) {
                    String yearsTwo = years;
                    int editYears = searchTheProducts.getYear();
                    yearsTwo = yearsTwo.replaceAll ( "\\D", "");

                    // Parse the variable yearsTwo to integer data type
                    int otherYear = Integer.parseInt ( yearsTwo );
                    while ( otherYear != 10000 ) {
                        if ( otherYear == editYears ) {
                            returnPrint.add ( "----------The following infomation matches the entered year----------");
                            returnPrint.add(searchTheProducts.toString());
                        }
                        otherYear = otherYear + 1;;
                    }
                }
                // This if condition checks is the user has entered two years seperatde by a '-'
                else if ( years.length() == 9 && years.indexOf ('-') == 4 ) {
                    String yearsTwo = years;
                    int editYears = searchTheProducts.getYear();
                    String other[] = yearsTwo.split ("-");

                    // Parse the variable other[0] to integer data type
                    int year1 = Integer.parseInt ( other[0] );
                    // Parse the variable other[1] to integer data type
                    int year2 = Integer.parseInt ( other[1] );
                    while ( year1 != year2 + 1 ) {
                        if ( year1 == editYears ) {
                            returnPrint.add ( "----------The following infomation matches the entered year----------");
                            returnPrint.add(searchTheProducts.toString());
                        }
                        year1 = year1 + 1;;
                    }
                }
            }
            return returnPrint;
        }

        // This if condition is used if the user enters a productID and a time period to be searched            
        else if ( !(ID.equals("")) && (keywords.length == 1 && keywords[0].equalsIgnoreCase("")) && !(years.equalsIgnoreCase("")) ) {
            for ( Products searchTheProducts : productList ) {
                if ( ID.equals(searchTheProducts.getProductID()) ) {
                    // This if condition is used when the user enters one year
                    if ( years.length() == 4 ) {

                        // Parse the variable years to integer data type
                        int otherYear = Integer.parseInt ( years );
                        if ( otherYear == searchTheProducts.getYear() ) {
                            returnPrint.add ( "----------The following infomation matches the entered productID and year----------");
                            returnPrint.add(searchTheProducts.toString());
                        }
                    }
                    // This if condition is used when the user enters a year with a '-' at the beginning
                    else if ( years.length() == 5 && years.indexOf ('-') == 0 ) {
                        String yearsTwo = years;
                        int editYears = searchTheProducts.getYear();
                        yearsTwo = yearsTwo.replaceAll ( "\\D", "");

                        // Parse the variable yearsTwo to integer data type
                        int otherYear = Integer.parseInt ( yearsTwo );
                        while ( otherYear != 999 ) {
                            if ( otherYear == editYears ) {
                                returnPrint.add ( "----------The following infomation matches the entered productID and year----------");
                                returnPrint.add(searchTheProducts.toString());
                            }
                            otherYear = otherYear - 1;;
                        }
    
                    }
                    // This if condition checks if the user enters a year with a '-' at the end
                    else if ( years.length() == 5 && years.indexOf ('-') == 4 ) {
                        String yearsTwo = years;
                        int editYears = searchTheProducts.getYear();
                        yearsTwo = yearsTwo.replaceAll ( "\\D", "");
                        // Parse the variable yearsTwo to integer data type
                        int otherYear = Integer.parseInt ( yearsTwo );
                        while ( otherYear != 10000 ) {
                            if ( otherYear == editYears ) {
                                returnPrint.add ( "----------The following infomation matches the entered productID and year----------");
                                returnPrint.add(searchTheProducts.toString());
                            }
                            otherYear = otherYear + 1;;
                        }
                    }
                    // This if condition checks is the user has entered two years seperatde by a '-'
                    else if ( years.length() == 9 && years.indexOf ('-') == 4 ) {
                        String yearsTwo = years;
                        int editYears = searchTheProducts.getYear();
                        String other[] = yearsTwo.split ("-");
                        // Parse the variable other[0] to integer data type
                        int year1 = Integer.parseInt ( other[0] );
                        // Parse the variable other[1] to integer data type
                        int year2 = Integer.parseInt ( other[1] );
                        while ( year1 != year2 + 1 ) {
                            if ( year1 == editYears ) {
                                returnPrint.add ( "----------The following infomation matches the entered productID and year----------");
                                returnPrint.add(searchTheProducts.toString());
                            }
                            year1 = year1 + 1;;
                        }
                    }
                }
            }
            return returnPrint;
        }

        // This if condition is used if the user enters a productID and keyword(s) to be searched            
        else if ( !(ID.equals("")) && (keywords.length != 1 || !(keywords[0].equalsIgnoreCase(""))) && years.equalsIgnoreCase("") ) {
            HashMap<String, ArrayList<Integer>> index = new HashMap<String, ArrayList<Integer>>();
            i = 0;
            int counter = 0;
            int myChecker = 0;
            int printManager = -1;
            int m = 0;
            ArrayList<Integer> list = new ArrayList<Integer>();
            ArrayList<Integer> temp = new ArrayList<Integer>();
            ArrayList<Integer> finalList = new ArrayList<Integer>();

            // Add all unique description words to a HashMap and add their indicies
            for ( Products searchingMyList : productList ) {
                // Split the description into seperate words and convert them to all upper case
                String other[] = (searchingMyList.getDescription().toUpperCase().split("[ ]+"));
                for ( int k = 0; k < other.length; k++ ) {
                    // If a word is already a key, add the index of that word to the existing key
                    if ( (index.containsKey(other[k])) ) {
                        list = index.get(other[k]);
                        list.add(counter);
                        index.put(other[k], list );
                        list = new ArrayList<Integer>();
                    }
                    // Create a new key for the new word and add its index
                    else {
                        list.add(counter);
                        index.put(other[k], list );
                        list = new ArrayList<Integer>();
                    }
                }
                if ( ID.equals(searchingMyList.getProductID()) ) {
                    printManager = counter;
                }
                counter = counter + 1;
            }

            int theTester = 0;
            int otherTester = 0;
            int lastTester = 0;
            // If the user enters more than one keyword to be searched
            if ( keywords.length != 1 ) {
                while ( theTester < keywords.length ) {
                    // Compare the users input with the HashMap
                    if ( lastTester == 0 ) {
                        if ( index.containsKey(keywords[lastTester].toUpperCase()) ) {
                            list = index.get(keywords[lastTester].toUpperCase());
                            myChecker = 1;
                        }
                        if ( index.containsKey(keywords[lastTester+1].toUpperCase()) ) {
                            temp = index.get(keywords[lastTester+1].toUpperCase());
                            myChecker = 1;
                        }
                        lastTester = lastTester + 1;
                    }

                    // Compare the users input with the HashMap
                    for ( int myIndex1 = 0; myIndex1 < list.size(); myIndex1++ ) {
                        for ( int myIndex2 = 0; myIndex2 < temp.size(); myIndex2++ ) {
                            if ( list.get(myIndex1) == temp.get(myIndex2)) {
                                myChecker = 1;
                                finalList.add(list.get(myIndex1));
                            }
                        }
                        otherTester = otherTester + 1;
                    }

                    if ( !(finalList.contains(printManager)) ) {
                        printManager = -1;
                    }

                    if ( keywords.length == 2 ) {
                        list = finalList;
                        temp = new ArrayList<Integer>();
                        theTester = theTester + 1;
                    }

                    else {
                        list = finalList;
                        temp = index.get(keywords[lastTester+1].toUpperCase());
                        finalList = new ArrayList<>();
                        theTester = theTester + 1;
                    }

                }

                // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                if ( myChecker == 1) {
                    while ( m < finalList.size() ) {
                        myChecker = 0;
                        Products printer = productList.get(finalList.get(m));
                        myChecker = 2;
                        if ( myChecker == 2 ) {
                            if ( printManager != -1 ) {
                                returnPrint.add ( "----------The following infomation matches the entered productID and keyword(s)----------");
                                returnPrint.add(printer.toString());
                                printManager = -1;
                            }
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
                    return returnPrint;
                }
                i = i + 1;

            }

            else {
                i = 0;
                if ( index.containsKey(keywords[i].toUpperCase()) ) {
                    list = index.get(keywords[i].toUpperCase());
                    myChecker = 1;
                }

                if ( !(list.contains(printManager)) ) {
                    printManager = -1;
                }

                // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                if ( myChecker == 1) {
                    while ( m < list.size() ) {
                        myChecker = 0;
                        Products printer = productList.get(list.get(m));
                        myChecker = 2;
                        if ( myChecker == 2 ) {
                            if ( printManager != -1 ) {
                                returnPrint.add ( "----------The following infomation matches the entered productID and keyword(s)----------");
                                returnPrint.add(printer.toString());
                                printManager = -1;
                            }
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
                    return returnPrint;
                }
                i = i + 1;
            }
        }

        // This if condition is used if the user enters a keyword(s) and a time period to be searched            
        else if ( ID.equals("") && (keywords.length != 1 || !(keywords[0].equalsIgnoreCase(""))) && !(years.equalsIgnoreCase("")) ) {
            HashMap<String, ArrayList<Integer>> index = new HashMap<String, ArrayList<Integer>>();
            i = 0;
            int counter = 0;
            int myChecker = 0;
            int m = 0;
            ArrayList<Integer> printManager = new ArrayList<Integer>();
            ArrayList<Integer> list = new ArrayList<Integer>();
            ArrayList<Integer> temp = new ArrayList<Integer>();
            ArrayList<Integer> finalList = new ArrayList<Integer>();
            ArrayList<Integer> newYearList = new ArrayList<Integer>();

            // Add all unique description words to a HashMap and add their indicies
            for ( Products searchingMyList : productList ) {
                // Split the description into seperate words and convert them to all upper case
                String other[] = (searchingMyList.getDescription().toUpperCase().split("[ ]+"));
                for ( int k = 0; k < other.length; k++ ) {
                    // If a word is already a key, add the index of that word to the existing key
                    if ( (index.containsKey(other[k])) ) {
                        list = index.get(other[k]);
                        list.add(counter);
                        index.put(other[k], list );
                        list = new ArrayList<Integer>();
                    }
                    // Create a new key for the new word and add its index
                    else {
                        list.add(counter);
                        index.put(other[k], list );
                        list = new ArrayList<Integer>();
                    }
                }

                // This if condition is used when the user enters one year
                if ( years.length() == 4 ) {

                    // Parse the variable years to integer data type
                    int otherYear = Integer.parseInt ( years );
                    if ( otherYear == searchingMyList.getYear() ) {
                        printManager.add(counter);
                    }
                }
                // This if condition is used when the user enters a year with a '-' at the beginning
                else if ( years.length() == 5 && years.indexOf ('-') == 0 ) {
                    String yearsTwo = years;
                    int editYears = searchingMyList.getYear();
                    yearsTwo = yearsTwo.replaceAll ( "\\D", "");

                    // Parse the variable yearsTwo to integer data type
                    int otherYear = Integer.parseInt ( yearsTwo );
                    while ( otherYear != 999 ) {
                        if ( otherYear == editYears ) {
                            printManager.add(counter);
                        }
                        otherYear = otherYear - 1;;
                    }

                }
                // This if condition checks if the user enters a year with a '-' at the end
                else if ( years.length() == 5 && years.indexOf ('-') == 4 ) {
                    String yearsTwo = years;
                    int editYears = searchingMyList.getYear();
                    yearsTwo = yearsTwo.replaceAll ( "\\D", "");

                    // Parse the variable yearsTwo to integer data type
                    int otherYear = Integer.parseInt ( yearsTwo );
                    while ( otherYear != 10000 ) {
                        if ( otherYear == editYears ) {
                            printManager.add(counter);
                        }
                        otherYear = otherYear + 1;;
                    }
                }
                // This if condition checks is the user has entered two years seperatde by a '-'
                else if ( years.length() == 9 && years.indexOf ('-') == 4 ) {
                    String yearsTwo = years;
                    int editYears = searchingMyList.getYear();
                    String twoSeperateDates[] = yearsTwo.split ("-");

                    // Parse the variable twoSeperateDates[0] to integer data type
                    int year1 = Integer.parseInt ( twoSeperateDates[0] );
                    // Parse the variable twoSeperateDates[1] to integer data type
                    int year2 = Integer.parseInt ( twoSeperateDates[1] );
                    while ( year1 != year2 + 1 ) {
                        if ( year1 == editYears ) {
                            printManager.add(counter);
                        }
                        year1 = year1 + 1;;
                    }
                }

                counter = counter + 1;
            }

            int theTester = 0;
            int otherTester = 0;
            int lastTester = 0;
            // If the user enters more than one keyword to be searched
            if ( keywords.length != 1 ) {
                while ( theTester < keywords.length ) {
                    // Compare the users input with the HashMap
                    if ( lastTester == 0 ) {
                        if ( index.containsKey(keywords[lastTester].toUpperCase()) ) {
                            list = index.get(keywords[lastTester].toUpperCase());
                            myChecker = 1;
                        }
                        if ( index.containsKey(keywords[lastTester+1].toUpperCase()) ) {
                            temp = index.get(keywords[lastTester+1].toUpperCase());
                            myChecker = 1;
                        }
                        lastTester = lastTester + 1;
                    }

                    // Compare the users input with the HashMap
                    for ( int myIndex1 = 0; myIndex1 < list.size(); myIndex1++ ) {
                        for ( int myIndex2 = 0; myIndex2 < temp.size(); myIndex2++ ) {
                            if ( list.get(myIndex1) == temp.get(myIndex2)) {
                                myChecker = 1;
                                finalList.add(list.get(myIndex1));
                            }
                        }
                        otherTester = otherTester + 1;
                    }

                    if ( keywords.length == 2 ) {
                        list = finalList;
                        temp = new ArrayList<Integer>();
                        theTester = theTester + 1;
                    }

                    else {
                        list = finalList;
                        temp = index.get(keywords[lastTester+1].toUpperCase());
                        finalList = new ArrayList<>();
                        theTester = theTester + 1;
                    }

                }

                for ( int myIndex1 = 0; myIndex1 < finalList.size(); myIndex1++ ) {
                    for ( int myIndex2 = 0; myIndex2 < printManager.size(); myIndex2++ ) {
                        if ( finalList.get(myIndex1) == printManager.get(myIndex2)) {
                            newYearList.add(finalList.get(myIndex1));
                        }
                    }
                    otherTester = otherTester + 1;
                }

                // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                if ( myChecker == 1) {
                    while ( m < newYearList.size() ) {
                        myChecker = 0;
                        Products printer = productList.get(newYearList.get(m));
                        myChecker = 2;
                        if ( myChecker == 2 ) {
                            returnPrint.add ( "----------The following infomation matches the entered year and keyword(s)----------");
                            returnPrint.add(printer.toString());
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
                    return returnPrint;
                }
                i = i + 1;

            }

            else {
                otherTester = 0;
                if ( index.containsKey(keywords[i].toUpperCase()) ) {
                    list = index.get(keywords[i].toUpperCase());
                    myChecker = 1;
                }

                for ( int myIndex1 = 0; myIndex1 < list.size(); myIndex1++ ) {
                    for ( int myIndex2 = 0; myIndex2 < printManager.size(); myIndex2++ ) {
                        if ( list.get(myIndex1) == printManager.get(myIndex2)) {
                            newYearList.add(list.get(myIndex1));
                        }
                    }
                    otherTester = otherTester + 1;
                }

                // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                if ( myChecker == 1) {
                    while ( m < newYearList.size() ) {
                        myChecker = 0;
                        Products printer = productList.get(newYearList.get(m));
                        myChecker = 2;
                        if ( myChecker == 2 ) {
                            returnPrint.add ( "----------The following infomation matches the entered year and keyword(s)----------");
                            returnPrint.add(printer.toString());
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
                    return returnPrint;
                }
                i = i + 1;
            }
        }

        // This if condition is used if the user enters a productID, keyword(s) and a time period to be searched
        else if ( !(ID.equals("")) && (keywords.length != 1 || !(keywords[0].equalsIgnoreCase(""))) && !(years.equalsIgnoreCase(""))) {
            HashMap<String, ArrayList<Integer>> index = new HashMap<String, ArrayList<Integer>>();
            i = 0;
            int counter = 0;
            int myChecker = 0;
            int m = 0;
            int printCounter = -1;
            ArrayList<Integer> printManager = new ArrayList<Integer>();
            ArrayList<Integer> list = new ArrayList<Integer>();
            ArrayList<Integer> temp = new ArrayList<Integer>();
            ArrayList<Integer> finalList = new ArrayList<Integer>();
            ArrayList<Integer> newYearList = new ArrayList<Integer>();

            // Add all unique description words to a HashMap and add their indicies
            for ( Products searchingMyList : productList ) {
                // Split the description into seperate words and convert them to all upper case
                String other[] = (searchingMyList.getDescription().toUpperCase().split("[ ]+"));
                for ( int k = 0; k < other.length; k++ ) {
                    // If a word is already a key, add the index of that word to the existing key
                    if ( (index.containsKey(other[k])) ) {
                        list = index.get(other[k]);
                        list.add(counter);
                        index.put(other[k], list );
                        list = new ArrayList<Integer>();
                    }
                    // Create a new key for the new word and add its index
                    else {
                        list.add(counter);
                        index.put(other[k], list );
                        list = new ArrayList<Integer>();
                    }
                }

                if ( ID.equals(searchingMyList.getProductID()) ) {
                    printCounter = counter;
                }

                // This if condition is used when the user enters one year
                if ( years.length() == 4 ) {

                    // Parse the variable years to integer data type
                    int otherYear = Integer.parseInt ( years );
                    if ( otherYear == searchingMyList.getYear() ) {
                        printManager.add(counter);
                    }
                }
                // This if condition is used when the user enters a year with a '-' at the beginning
                else if ( years.length() == 5 && years.indexOf ('-') == 0 ) {
                    String yearsTwo = years;
                    int editYears = searchingMyList.getYear();
                    yearsTwo = yearsTwo.replaceAll ( "\\D", "");

                    // Parse the variable yearsTwo to integer data type
                    int otherYear = Integer.parseInt ( yearsTwo );
                    while ( otherYear != 999 ) {
                        if ( otherYear == editYears ) {
                            printManager.add(counter);
                        }
                        otherYear = otherYear - 1;;
                    }

                }
                // This if condition checks if the user enters a year with a '-' at the end
                else if ( years.length() == 5 && years.indexOf ('-') == 4 ) {
                    String yearsTwo = years;
                    int editYears = searchingMyList.getYear();
                    yearsTwo = yearsTwo.replaceAll ( "\\D", "");

                    // Parse the variable yearsTwo to integer data type
                    int otherYear = Integer.parseInt ( yearsTwo );
                    while ( otherYear != 10000 ) {
                        if ( otherYear == editYears ) {
                            printManager.add(counter);
                        }
                        otherYear = otherYear + 1;;
                    }
                }
                // This if condition checks is the user has entered two years seperatde by a '-'
                else if ( years.length() == 9 && years.indexOf ('-') == 4 ) {
                    String yearsTwo = years;
                    int editYears = searchingMyList.getYear();
                    String twoSeperateDates[] = yearsTwo.split ("-");

                    // Parse the variable twoSeperateDates[0] to integer data type
                    int year1 = Integer.parseInt ( twoSeperateDates[0] );
                    // Parse the variable twoSeperateDates[1] to integer data type
                    int year2 = Integer.parseInt ( twoSeperateDates[1] );
                    while ( year1 != year2 + 1 ) {
                        if ( year1 == editYears ) {
                            printManager.add(counter);
                        }
                        year1 = year1 + 1;;
                    }
                }

                counter = counter + 1;
            }

            int theTester = 0;
            int otherTester = 0;
            int lastTester = 0;
            // If the user enters more than one keyword to be searched
            if ( keywords.length != 1 ) {
                while ( theTester < keywords.length ) {
                    // Compare the users input with the HashMap
                    if ( lastTester == 0 ) {
                        if ( index.containsKey(keywords[lastTester].toUpperCase()) ) {
                            list = index.get(keywords[lastTester].toUpperCase());
                            myChecker = 1;
                        }
                        if ( index.containsKey(keywords[lastTester+1].toUpperCase()) ) {
                            temp = index.get(keywords[lastTester+1].toUpperCase());
                            myChecker = 1;
                        }
                        lastTester = lastTester + 1;
                    }

                    // Compare the users input with the HashMap
                    for ( int myIndex1 = 0; myIndex1 < list.size(); myIndex1++ ) {
                        for ( int myIndex2 = 0; myIndex2 < temp.size(); myIndex2++ ) {
                            if ( list.get(myIndex1) == temp.get(myIndex2)) {
                                myChecker = 1;
                                finalList.add(list.get(myIndex1));
                            }
                        }
                        otherTester = otherTester + 1;
                    }

                    if ( !(finalList.contains(printCounter)) ) {
                        printCounter = -1;
                    }

                    if ( keywords.length == 2 ) {
                        list = finalList;
                        temp = new ArrayList<Integer>();
                        theTester = theTester + 1;
                    }

                    else {
                        list = finalList;
                        temp = index.get(keywords[lastTester+1].toUpperCase());
                        finalList = new ArrayList<>();
                        theTester = theTester + 1;
                    }

                }

                for ( int myIndex1 = 0; myIndex1 < finalList.size(); myIndex1++ ) {
                    for ( int myIndex2 = 0; myIndex2 < printManager.size(); myIndex2++ ) {
                        if ( finalList.get(myIndex1) == printManager.get(myIndex2)) {
                            newYearList.add(finalList.get(myIndex1));
                        }
                    }
                    otherTester = otherTester + 1;
                }

                if ( !(newYearList.contains(printCounter)) ) {
                    printCounter = -1;
                }

                // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                if ( myChecker == 1) {
                    while ( m < newYearList.size() ) {
                        myChecker = 0;
                        Products printer = productList.get(newYearList.get(m));
                        myChecker = 2;
                        if ( myChecker == 2 ) {
                            if ( printCounter != -1 ) {
                                if ( printCounter != -1 ) {
                                    returnPrint.add ( "----------The following infomation matches the entered productID, year and keyword(s)----------");
                                    returnPrint.add(printer.toString());
                                    printCounter = -1;
                                }
                                myChecker = 1;
                            }
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
                    return returnPrint;
                }
                i = i + 1;

            }

            else {
                otherTester = 0;
                if ( index.containsKey(keywords[i].toUpperCase()) ) {
                    list = index.get(keywords[i].toUpperCase());
                    myChecker = 1;
                }

                for ( int myIndex1 = 0; myIndex1 < list.size(); myIndex1++ ) {
                    for ( int myIndex2 = 0; myIndex2 < printManager.size(); myIndex2++ ) {
                        if ( list.get(myIndex1) == printManager.get(myIndex2)) {
                            newYearList.add(list.get(myIndex1));
                        }
                    }
                    otherTester = otherTester + 1;
                }

                if ( !(newYearList.contains(printCounter)) ) {
                    printCounter = -1;
                }

                // This if condition checks if the variable myChecker equals 1, if so the matches are displayed to the user.
                if ( myChecker == 1) {
                    while ( m < newYearList.size() ) {
                        myChecker = 0;
                        Products printer = productList.get(newYearList.get(m));
                        myChecker = 2;
                        if ( myChecker == 2 ) {
                            if ( printCounter != -1 ) {
                                returnPrint.add ( "----------The following infomation matches the entered productID, year and keyword(s)----------");
                                returnPrint.add(printer.toString());
                                printCounter = -1;
                            }
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
                    return returnPrint;
                }
                i = i + 1;
            }
        }

        returnPrint.add("The search failed.");
        return returnPrint;

    }

    /**
     * This method is used to add a new book to the store.
     * The method prompts the user to enter all the required fields for a new book.
     * The method does not allow duplicate productIDs to be entered
     * The method does not allow the productID, description or year fields to be left blank.
     * Once all feilds have been correctly entered by the user, a new book will be added and saved to the store
     * 
     * @return String of message
     * @param myBook
     * @param yourFile
     * @param i1
     * @param i2
     * @param i3
     * @param iChange4
     * @param i5
     * @param i6
     * @throws Exception
     */

    Scanner keyboardElectronic = new Scanner(System.in);
    Scanner keyboardBook = new Scanner(System.in);
    
    public String yourBook ( Books myBook, String yourFile, String i1, String i2, String i3, String iChange4, String i5, String i6 ) throws Exception {

        int test = 0;
        try {
            test = Integer.parseInt(i1);
            test = test - 1;
        } catch (Exception e) {
            return ( "That is not a valid entry. Please enter the required field productID, which must be 6 digits long.");
        }
        String theBookID = i1;
        String theDescription = i2;
        String thePrice = i3;
        int theYear;
        try {
            int i4 = Integer.parseInt(iChange4);
            theYear = i4;
        } catch (Exception e) {
            return ( "That is not a valid entry. Please enter the required field year, which must be between 1000 and 9999.");
        }
        String theAuthors = i5;
        String thePublisher = i6;

        // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
        while ( !(theBookID.length() == 6) ) {
            return ( "That is not a valid entry. Please enter the required field productID, which must be 6 digits long.");
        }

        for ( Products searchingIDS : productList ) {

            // This while loop checks if the user has entered a duplicate ID. If so they will be prompted to enter a valid option.
            while ( theBookID.equals(searchingIDS.getProductID()) ) {
                return ( "You have entered a productID which has already been entered" );
            }
        }

        while ( theDescription.equalsIgnoreCase ( "" ) ) {
            return ( "You cannot leave this field blank. Please enter the required field description.");
        }
        
        while ( theYear < 1000 || theYear > 9999 ) {
            return ( "That is not a valid entry. Please enter the required field year, which must be between 1000 and 9999.");
        }

        double thePriceDouble = 0;

        try {
            if ( thePrice.equalsIgnoreCase ( "" ) ) {
                thePrice = "0";
            }
            // Parse the variable thePrice to double data type
            thePriceDouble = Double.parseDouble (thePrice);
        } catch (Exception e) {
            return ( "That is not a valid entry. Please enter the required field price, which must be of integer or double data type.");
        }

        if ( theAuthors.equalsIgnoreCase ( "" ) ) {
            theAuthors = "N/A";
        }

        if ( thePublisher.equalsIgnoreCase ( "" ) ) {
            thePublisher = "N/A";
        }

        myBookOb.setProductID(theBookID);
        myBookOb.setDescription(theDescription);
        myBookOb.setYear(theYear);
        myBookOb.setPrice(thePriceDouble);
        myBookOb.setAuthors(theAuthors);
        myBookOb.setPublisher(thePublisher);
        myBookOb.setType("book");

        productList.add ( myBookOb );

        try {
            // Write the newly added book to the given file
            PrintWriter fileWriter = new PrintWriter ( new FileOutputStream ( yourFile, true ) );
            fileWriter.println ( myBookOb.toString() );
            fileWriter.close();

        } catch ( Exception e ) {
            
            return ( "Failed to write." );

        }

        myBookOb = new Books();

        return ( "You have entered a new book.");

    }

    /**
     * This method accepts a string as the parameter which contains
     * the name of the file which the product will be written to
     * 
     * @param yourFile
     */
    public void yourWrite (String yourFile) {

        try {

            int i = 0;

            for ( Products printer: productList ) {
                // Write the products to the file
                PrintWriter fileWriter = new PrintWriter ( new FileOutputStream ( yourFile, true ) );
                fileWriter.println ( printer.toString() );
                fileWriter.close();

                i = i + 1;

            }

        // Exception if the write fails
        } catch ( Exception e ) {
            
            System.out.println ( "Failed to write.\n\n" );

        }

    }

    /**
     * This method accepts a string as the parameter which contains the name of the
     * file which the product(s) will be read from
     * 
     * @param theFile
     * @throws Exception
     */
    public void yourFile(String theFile) throws Exception {

        String my_line;

        try {

            File f = new File(theFile);

            Scanner scanner = new Scanner( f );

            if ( !(scanner.hasNextLine()) ) {
                System.out.println ( "The file is empty" );
            }

            while ( scanner.hasNextLine()) {

                my_line = scanner.nextLine();
                String finalDescription = null;
                String finalAuthor = null;
                String finalPublisher = null;
                String finalMaker = null;

                String other[] = my_line.split("[ ]+");

                // If the type of product is a book
                if ( other[2].equals("\"book\"") ) {

                    String productIdRead = scanner.nextLine();
                    String other1[] = productIdRead.split("[ ]+");
                    other1[2] = other1[2].substring(1, (other1[2].length() - 1));

                    String descriptionRead = scanner.nextLine();
                    String other2[] = descriptionRead.split("[ ]+");
                    for ( int i = 2; i < other2.length; i++ ) {
                        finalDescription = finalDescription + " " + other2[i];
                    }
                    finalDescription = finalDescription.substring(6, (finalDescription.length() - 1));

                    String priceRead = scanner.nextLine();
                    String other3[] = priceRead.split("[ ]+");
                    other3[2] = other3[2].substring(1, (other3[2].length() - 1));
                    double realPrice = Double.parseDouble(other3[2]);

                    String yearRead = scanner.nextLine();
                    String other4[] = yearRead.split("[ ]+");
                    other4[2] = other4[2].substring(1, (other4[2].length() - 1));
                    int realYear = Integer.parseInt(other4[2]);

                    String authorRead = scanner.nextLine();
                    String other5[] = authorRead.split("[ ]+");
                    for ( int i = 2; i < other5.length; i++ ) {
                        finalAuthor = finalAuthor + " " + other5[i];
                    }
                    finalAuthor = finalAuthor.substring(6, (finalAuthor.length() - 1));

                    String publisherRead = scanner.nextLine();
                    String other6[] = publisherRead.split("[ ]+");
                    for ( int i = 2; i < other6.length; i++ ) {
                        finalPublisher = finalPublisher + " " + other6[i];
                    }
                    finalPublisher = finalPublisher.substring(6, (finalPublisher.length() - 1));

                    // Add all read attributes to an object of type book
                    myBookOb.setProductID(other1[2]);
                    myBookOb.setDescription(finalDescription);
                    myBookOb.setYear(realYear);
                    myBookOb.setPrice(realPrice);
                    myBookOb.setAuthors(finalAuthor);
                    myBookOb.setPublisher(finalPublisher);
                    myBookOb.setType("book");
            
                    productList.add ( myBookOb );

                    myBookOb = new Books();

                }
                // If the type of product is an electronic
                else if ( other[2].equals("\"electronics\"") ) {

                    String productIdRead = scanner.nextLine();
                    String other1[] = productIdRead.split("[ ]+");
                    other1[2] = other1[2].substring(1, (other1[2].length() - 1));

                    String descriptionRead = scanner.nextLine();
                    String other2[] = descriptionRead.split("[ ]+");
                    for ( int i = 2; i < other2.length; i++ ) {
                        finalDescription = finalDescription + " " + other2[i];
                    }
                    finalDescription = finalDescription.substring(6, (finalDescription.length() - 1));

                    String priceRead = scanner.nextLine();
                    String other3[] = priceRead.split("[ ]+");
                    other3[2] = other3[2].substring(1, (other3[2].length() - 1));
                    double realPrice = Double.parseDouble(other3[2]);

                    String yearRead = scanner.nextLine();
                    String other4[] = yearRead.split("[ ]+");
                    other4[2] = other4[2].substring(1, (other4[2].length() - 1));
                    int realYear = Integer.parseInt(other4[2]);

                    String publisherRead = scanner.nextLine();
                    String other6[] = publisherRead.split("[ ]+");
                    for ( int i = 2; i < other6.length; i++ ) {
                        finalMaker = finalMaker + " " + other6[i];
                    }
                    finalMaker = finalMaker.substring(6, (finalMaker.length() - 1));

                    // Add all read attributes to an object of type electronic
                    myElectronicOb.setProductID(other1[2]);
                    myElectronicOb.setDescription(finalDescription);
                    myElectronicOb.setYear(realYear);
                    myElectronicOb.setPrice(realPrice);
                    myElectronicOb.setMaker(finalMaker);
                    myElectronicOb.setType("electronics");
            
                    productList.add ( myElectronicOb );

                    myElectronicOb = new Electronics();

                }

                }

            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.println ( "Error opening the file\n" );

        }
    }

    /**
     * This method is used to add a new electronic to the store.
     * The method prompts the user to enter all the required fields for a new electronic.
     * The method does not allow duplicate productIDs to be entered
     * The method does not allow the productID, description or year fields to be left blank.
     * Once all feilds have been correctly entered by the user, a new electronic will be added and saved to the store
     * 
     * @return String of message
     * @param myBook
     * @param yourFile
     * @param i1
     * @param i2
     * @param i3
     * @param iChange4
     * @param i5
     * @throws Exception
     */
    public String yourElectronic ( Electronics myBook, String yourFile, String i1, String i2, String i3, String iChange4, String i5 ) throws Exception{

        int test = 0;
        try {
            test = Integer.parseInt(i1);
            test = test - 1;
        } catch (Exception e) {
            return ( "That is not a valid entry. Please enter the required field productID, which must be 6 digits long.");
        }
        String theElectronicID = i1;
        String theDescription = i2;
        String thePrice = i3;
        int theYear;
        try {
            int i4 = Integer.parseInt(iChange4);
            theYear = i4;
        } catch (Exception e) {
            return ( "That is not a valid entry. Please enter the required field year, which must be between 1000 and 9999.");
        }
        String theMaker = i5;

        // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
        while ( !(theElectronicID.length() == 6) ) {
            return ( "That is not a valid entry. Please enter the required field productID, which must be 6 digits long.");
        }

        for ( Products searchingIDS : productList ) {

            // This while loop checks if the user has entered a duplicate ID. If so they will be prompted to enter a valid option.
            while ( theElectronicID.equals(searchingIDS.getProductID()) ) {
                return ( "You have entered a productID which has already been entered." );
            }
        }


        // This while loop checks if the user has left this field empty. If so they will be prompted to enter a valid option.
        while ( theDescription.equalsIgnoreCase ( "" ) ) {
            return ( "You cannot leave this field blank. Please enter the required field description.");
        }

        // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.        
        while ( theYear < 1000 || theYear > 9999 ) {
            return ( "That is not a valid entry. Please enter the required field year, which must be between 1000 and 9999.");
        }

        if ( thePrice.equalsIgnoreCase ( "" ) ) {
            thePrice = "0";
        }

        double thePriceDouble = 0;

        try {
            if ( thePrice.equalsIgnoreCase ( "" ) ) {
                thePrice = "0";
            }
            // Parse the variable thePrice to double data type
            thePriceDouble = Double.parseDouble (thePrice);
        } catch (Exception e) {
            return ( "That is not a valid entry. Please enter the required field price, which must be of integer or double data type.");
        }

        if ( theMaker.equalsIgnoreCase ( "" ) ) {
            theMaker = "N/A";
     
        }

        myElectronicOb.setProductID(theElectronicID);
        myElectronicOb.setDescription(theDescription);
        myElectronicOb.setYear(theYear);
        myElectronicOb.setPrice(thePriceDouble);
        myElectronicOb.setMaker(theMaker);
        myElectronicOb.setType("electronic");

        productList.add ( myElectronicOb );

        try {
            // Write the newly added book to the given file
            PrintWriter fileWriter = new PrintWriter ( new FileOutputStream ( yourFile, true ) );
            fileWriter.println ( myElectronicOb.toString() );
            fileWriter.close();

        } catch ( Exception e ) {
            
            System.out.println ( "Failed to write.\n\n" );

        }

        myElectronicOb = new Electronics();

        return ( "You have entered a new electronic.");

    }
}
