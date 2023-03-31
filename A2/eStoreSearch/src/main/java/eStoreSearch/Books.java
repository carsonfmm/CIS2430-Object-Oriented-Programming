package eStoreSearch;

public class Books extends Products {
        
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
    public Books ( String productID, String description, int year, double price, String authors, String publisher ) {
        super (productID, description, year, price);
        this.authors = authors;
        this.publisher = publisher;
    }

	public Books() {
        authors = "";
        publisher = "";
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

    /**
     * 
     * This method prints the attributes for a book
     * 
     * @return "type = \"" + getType() + "\"\nproductID = \"" + getProductID() + "\"\ndescription = \"" + getDescription() + "\"\nprice = \"" + getPrice() + "\"\nyear = \"" + getYear() + "\"\nauthors = \"" + getAuthors() + "\"\npublisher = \"" + getPublisher() + "\""
     */
    @Override
    public String toString() {
        return ( "type = \"" + getType() + "\"\nproductID = \"" + getProductID() + "\"\ndescription = \"" + getDescription() + "\"\nprice = \"" + getPrice() + "\"\nyear = \"" + getYear() + "\"\nauthors = \"" + getAuthors() + "\"\npublisher = \"" + getPublisher() + "\"" );
    }
}
