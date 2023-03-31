package eStoreSearch;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class eStoreSearchTest {

    private Books myBooks = new Books();
    private Electronics myElectronics = new Electronics();
    private ArrayList<Products> productList = new ArrayList<>();
    private theSearch store = new theSearch();
    Products myProducts = new Products();

    @Test public void testSetProductIDBook() {
        System.out.println ( "Test setProductID and getProductID methods with a productID of integer data type" );
        myBooks.setProductID ( "123456" );
        assertTrue ( myBooks.getProductID() == "123456" );
    }

    @Test public void testSetDescriptionBook() {
        System.out.println ( "Test setDescription and getDescription methods with a description of String data type" );
        myBooks.setDescription ( "Java Programming is fun" );
        assertTrue ( myBooks.getDescription() == "Java Programming is fun" );
    }

    @Test public void testSetYearBook() {
        System.out.println ( "Test setYear and getYear methods with a year of integer data type" );
        myBooks.setYear ( 2020 );
        assertTrue ( myBooks.getYear() == 2020 );
    }

    @Test public void testSetPriceBook() {
        System.out.println ( "Test setPrice and getPrice methods with a price of double data type" );
        myBooks.setPrice ( 65.99 );
        assertTrue ( myBooks.getPrice() == 65.99 );
    }

    @Test public void testSetAuthorsBook() {
        System.out.println ( "Test setAuthors and getAuthors methods with authors of tring data type" );
        myBooks.setAuthors ( "Joe, Will" );
        assertTrue ( myBooks.getAuthors() == "Joe, Will" );
    }
    @Test public void testSetPublisherBook() {
        System.out.println ( "Test setPublisher and getPublisher methods with a publisher of String data type" );
        myBooks.setPublisher ( "Smith" );
        assertTrue ( myBooks.getPublisher() == "Smith" );
    }

    @Test public void testSetType() {
        System.out.println ( "Test setType and getType methods with a type of String data type" );
        myBooks.setPublisher ( "book" );
        assertTrue ( myBooks.getPublisher() == "book" );
    }

    @Test public void testSetProductIDElectronic() {
        System.out.println ( "Test setProductID and getProductID methods with a productID of integer data type" );
        myElectronics.setProductID ( "123456" );
        assertTrue ( myElectronics.getProductID() == "123456" );
    }

    @Test public void testSetDescriptionElectronic() {
        System.out.println ( "Test setDescription and getDescription methods with a description of String data type" );
        myElectronics.setDescription ( "Java Programming is fun" );
        assertTrue ( myElectronics.getDescription() == "Java Programming is fun" );
    }

    @Test public void testSetYearElectronic() {
        System.out.println ( "Test setYear and getYear methods with a year of integer data type" );
        myElectronics.setYear ( 2020 );
        assertTrue ( myElectronics.getYear() == 2020 );
    }

    @Test public void testSetPriceElectronic() {
        System.out.println ( "Test setPrice and getPrice methods with a price of double data type" );
        myElectronics.setPrice ( 65.99 );
        assertTrue ( myElectronics.getPrice() == 65.99 );
    }

    @Test public void testSetAuthorsElectronic() {
        System.out.println ( "Test setAuthors and getAuthors methods with authors of string data type" );
        myElectronics.setMaker ( "Joe, Will" );
        assertTrue ( myElectronics.getMaker() == "Joe, Will" );
    }

    @Test public void testYourBook() {
        System.out.println("Test yourBook method with all attributes");
        myBooks.setProductID("123456");
        myBooks.setDescription("This is a test");
        myBooks.setYear(2016);
        myBooks.setPrice(45.99);
        myBooks.setAuthors("Will");
        myBooks.setPublisher("Smith");
        productList.add(myBooks);
        myBooks = new Books();
        assertTrue(productList.add(myBooks));
    }

    @Test public void testYourElectronic() {
        System.out.println("Test yourElectronic method with all attributes");
        myElectronics.setProductID("654321");
        myElectronics.setDescription("This is another test");
        myElectronics.setYear(1990);
        myElectronics.setPrice(26.85);
        myElectronics.setMaker("Bob");
        productList.add(myElectronics);
        myElectronics = new Electronics();
        assertTrue(productList.add(myElectronics));
    }

    @Test public void testYourSearch1() {
        System.out.println("Test yourSearch method with a productID and keyword(s) using a sample electronic");
        myElectronics.setProductID("654321");
        myElectronics.setDescription("This is another test");
        myElectronics.setYear(1990);
        myElectronics.setPrice(26.85);
        myElectronics.setMaker("Bob");
        productList.add(myElectronics);
        String theProductID = "654321";
        String theKeywords = "another THIS";
        String other[] = theKeywords.split("[ ]+");
        store.yourSearch ( theProductID, other, "" );
        assertTrue(productList.contains(myElectronics));
    }

    @Test public void testYourSearch2() {
        System.out.println("Test yourSearch method with a productID and time period using a sample electronic");
        myBooks.setProductID("123456");
        myBooks.setDescription("This is a test");
        myBooks.setYear(2016);
        myBooks.setPrice(45.99);
        myBooks.setAuthors("Will");
        myBooks.setPublisher("Smith");
        productList.add(myBooks);
        String theProductID = "123456";
        String theTimePeriod = "-2020";
        String other[] = theTimePeriod.split("-");
        store.yourSearch ( theProductID, other, "" );
        assertTrue(productList.contains(myBooks));
    }

    @Test public void testDuplicateProductIDs() {
        System.out.println("Test if no duplicate productIDs are allowed to be added");
        String theElectronicID = null;
        myElectronics.setProductID("654321");
        myElectronics.setDescription("This is a test");
        myElectronics.setYear(1990);
        myElectronics.setPrice(26.85);
        myElectronics.setMaker("Bob");
        productList.add(myElectronics);
        theElectronicID = "654321";
        String test = null;
        while ( theElectronicID.equals(myElectronics.getProductID()) ) {
            test = "You have entered a productID which has already been entered";
            return;
        }
        assertTrue(test == "You have entered a productID which has already been entered" );
    }

    @Test public void testYourWrite() {
        System.out.println("Test yourWrite method with a string containing the file name");
        String myFile = "read.txt";
        store.yourWrite(myFile);
        assertTrue( myFile != null );
    }

    @Test public void testYourFile() {
        System.out.println("Test yourFile method with a string containing the file name");
        String myFile = "read.txt";
        store.yourFile(myFile);
        assertTrue( productList != null );
    }

}
