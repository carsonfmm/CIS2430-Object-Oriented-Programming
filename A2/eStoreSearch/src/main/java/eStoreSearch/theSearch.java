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
     * @param ID
     * @param keywords
     * @param years
     */
    public void yourSearch ( String ID, String[] keywords, String years ) {

        int i = 0;

        // This if condition is used if the user leaves all search fields blank
        if ( ID.equals("") && (keywords.length == 1 && keywords[0].equalsIgnoreCase("")) && years.equalsIgnoreCase("") ) {
            for ( Products searchTheProducts : productList ) {
                System.out.println ( "----------The following infomation is present in the store----------");
                System.out.println(searchTheProducts.toString());
            }
        }

        // This if condition is used if the user enters a productID to be searched
        else if ( !(ID.equals("")) && (keywords.length == 1 && keywords[0].equalsIgnoreCase("")) && years.equalsIgnoreCase("") ) {
            for ( Products searchTheProducts : productList ) {
                if ( ID.equals(searchTheProducts.getProductID()) ) {
                    System.out.println ( "----------The following infomation matches the entered productID----------");
                    System.out.println(searchTheProducts.toString());
                }
            }
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
                            System.out.println ( "----------The following infomation matches the entered keyword(s)----------");
                            System.out.println(printer.toString());
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
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
                            System.out.println ( "----------The following infomation matches the entered keyword(s)----------");
                            System.out.println(printer.toString());
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
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
                        System.out.println ( "----------The following infomation matches the entered year----------");
                        System.out.println(searchTheProducts.toString());
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
                            System.out.println ( "----------The following infomation matches the entered year----------");
                            System.out.println(searchTheProducts.toString());
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
                            System.out.println ( "----------The following infomation matches the entered year----------");
                            System.out.println(searchTheProducts.toString());
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
                            System.out.println ( "----------The following infomation matches the entered year----------");
                            System.out.println(searchTheProducts.toString());
                        }
                        year1 = year1 + 1;;
                    }
                }
            }
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
                            System.out.println ( "----------The following infomation matches the entered productID and year----------");
                            System.out.println(searchTheProducts.toString());
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
                                System.out.println ( "----------The following infomation matches the entered productID and year----------");
                                System.out.println(searchTheProducts.toString());
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
                                System.out.println ( "----------The following infomation matches the entered productID and year----------");
                                System.out.println(searchTheProducts.toString());
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
                                System.out.println ( "----------The following infomation matches the entered productID and year----------");
                                System.out.println(searchTheProducts.toString());
                            }
                            year1 = year1 + 1;;
                        }
                    }
                }
            }
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
                                System.out.println ( "----------The following infomation matches the entered productID and keyword(s)----------");
                                System.out.println(printer.toString());
                                printManager = -1;
                            }
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
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
                                System.out.println ( "----------The following infomation matches the entered productID and keyword(s)----------");
                                System.out.println(printer.toString());
                                printManager = -1;
                            }
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
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
                            System.out.println ( "----------The following infomation matches the entered year and keyword(s)----------");
                            System.out.println(printer.toString());
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
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
                            System.out.println ( "----------The following infomation matches the entered year and keyword(s)----------");
                            System.out.println(printer.toString());
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
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
                                    System.out.println ( "----------The following infomation matches the entered productID, year and keyword(s)----------");
                                    System.out.println(printer.toString());
                                    printCounter = -1;
                                }
                                myChecker = 1;
                            }
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
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
                                System.out.println ( "----------The following infomation matches the entered productID, year and keyword(s)----------");
                                System.out.println(printer.toString());
                                printCounter = -1;
                            }
                            myChecker = 1;
                        }
                        m = m + 1;
                    }
                }
                i = i + 1;
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
     * @param myProducts
     * @param yourFile
     */

    Scanner keyboardElectronic = new Scanner(System.in);
    Scanner keyboardBook = new Scanner(System.in);
    
    public void yourBook ( Books myBook, Products myProducts, String yourFile ) {

        String theBookID = "";
        String theDescription;
        int theYear;
        String thePrice;
        String theAuthors;
        String thePublisher;

        System.out.print ( "Please enter the required field productID, which must be 6 digits long: ");
        theBookID = keyboardBook.nextLine();

        System.out.print ( "\n" );

        // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
        while ( !(theBookID.length() == 6) ) {
            System.out.println ( "That is not a valid entry. Please enter the required field productID, which must be 6 digits long: ");
            theBookID = keyboardBook.nextLine();
        }

        for ( Products searchingIDS : productList ) {

            // This while loop checks if the user has entered a duplicate ID. If so they will be prompted to enter a valid option.
            while ( theBookID.equals(searchingIDS.getProductID()) ) {
                System.out.println ( "You have entered a productID which has already been entered" );
                return;
            }
        }

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
            theAuthors = "N/A";
        }

        System.out.println ( "Enter the publisher, or leave blank: ");
        thePublisher = keyboardBook.nextLine();
        if ( thePublisher.equalsIgnoreCase ( "" ) ) {
            thePublisher = "N/A";
        }

        System.out.println ( "You have entered a new book.");

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
            
            System.out.println ( "Failed to write.\n\n" );

        }

        myBookOb = new Books();

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
     * This method accepts a string as the parameter which contains
     * the name of the file which the product(s) will be read from
     * 
     * @param theFile
     */
    public void yourFile (String theFile) {

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
                else {
                    System.out.println ( "The file is not in a valid format.\n\n" );
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
     * @param myBook
     * @param myProducts
     * @param yourFile
     */
    public void yourElectronic ( Electronics myBook, Products myProducts, String yourFile ){

        String theElectronicID = "";
        String theDescription;
        int theYear;
        String thePrice;
        String theMaker;

        System.out.print ( "Please enter the required field productID, which must be 6 digits long: ");
        theElectronicID = keyboardElectronic.nextLine();

        System.out.print ( "\n" );

        // This while loop checks if the user has entered a valid input. If not they will be prompted to enter a valid option.
        while ( !(theElectronicID.length() == 6) ) {
            System.out.println ( "That is not a valid entry. Please enter the required field productID, which must be 6 digits long: ");
            theElectronicID = keyboardElectronic.nextLine();
        }

        for ( Products searchingIDS : productList ) {

            // This while loop checks if the user has entered a duplicate ID. If so they will be prompted to enter a valid option.
            while ( theElectronicID.equals(searchingIDS.getProductID()) ) {
                System.out.println ( "You have entered a productID which has already been entered" );
                return;
            }
        }

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

        System.out.println ( "You have entered a new electronic.");

    }
}
