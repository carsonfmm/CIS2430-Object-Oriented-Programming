package eStoreSearch;

public class Books extends Products {
        
    protected String authors;
    protected String publisher;

    /**
     * This constructor method initializes all instance variables for the Books
     * class
     * 
     * @param productID
     * @param description
     * @param year
     * @param price
     * @param authors
     * @param publisher
     * @throws Exception
     */
    public Books(String productID, String description, int year, double price, String authors, String publisher) throws Exception {
        super (productID, description, year, price);
        this.authors = authors;
        this.publisher = publisher;
    }

    public Books ( Books theCopy ) {
        this.productID = theCopy.productID;
        this.description = theCopy.description;
        this.year = theCopy.year;
        this.price = theCopy.price;
        this.authors = theCopy.authors;
        this.publisher = theCopy.publisher;
    }

        /**
     * This constructor method initializes all instance variables for the Product
     * class
     * 
     * @param productID
     * @param description
     * @param year
     * @param price
     * @throws Exception
     */

    @Override
    public boolean equals ( Object theObject ) {
        if ( theObject == null || !(getClass() == theObject.getClass())) {
            return false;
        }
        else {
            Books theBook = (Books)theObject;
            return ( productID.equals(theBook.productID) && description.equals(theBook.description) &&
            year == (theBook.year) && price == (theBook.price) && authors.equals(theBook.authors) && 
            publisher.equals(theBook.publisher));
        }
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

    /**
     * This method returns the productID
     * 
     * @return productID
     */
    public String getProductID() {
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
     * This method returns the type
     * 
     * @return type
     */
    public String getType() {
        return type;
    }

    
    /**
     * This method initializes the productID using the 'this' keyword
     * 
     * @param productID
     * @throws Exception
     */
    public void setProductID(String productID) throws Exception {
        if ( !(productID.equals("") || productID.equals(null))) {
            this.productID = productID;
        }
        else{
            throw new Exception ("Fatal error: productID cannot be left blank");
        }
    }

    /**
     * This method initializes the description using the 'this' keyword
     * 
     * @param description
     * @throws Exception
     */
    public void setDescription(String description) throws Exception {
        if ( !(description.equals("") || description.equals(null))) {
            this.description = description;
        }
        else{
            throw new Exception ("Fatal error: description cannot be left blank");
        }
    }

    /**
     * This method initializes the year using the 'this' keyword
     * 
     * @param year
     * @throws Exception
     */
    public void setYear(int year) throws Exception {
        if ( !(year == 0) ) {
            this.year = year;
        }
        else{
            throw new Exception ("Fatal error: year cannot be left blank");
        }
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
     * This method initializes the type using the 'this' keyword
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

}
