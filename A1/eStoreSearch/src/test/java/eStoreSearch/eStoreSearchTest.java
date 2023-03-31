package eStoreSearch;

import org.junit.Test;
import static org.junit.Assert.*;

public class eStoreSearchTest {

    private Books myBooks = new Books();
    private Electronics myElectronics = new Electronics();

    @Test public void testSetProductIDBook() {
        System.out.println ( "Test setProductID and getProductID methods with a productID of integer data type" );
        myBooks.setProductID ( 123456 );
        assertTrue ( myBooks.getProductID() == 123456 );
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




    @Test public void testSetProductIDElectronic() {
        System.out.println ( "Test setProductID and getProductID methods with a productID of integer data type" );
        myElectronics.setProductID ( 123456 );
        assertTrue ( myElectronics.getProductID() == 123456 );
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
        System.out.println ( "Test setAuthors and getAuthors methods with authors of tring data type" );
        myElectronics.setMaker ( "Joe, Will" );
        assertTrue ( myElectronics.getMaker() == "Joe, Will" );
    }
}
