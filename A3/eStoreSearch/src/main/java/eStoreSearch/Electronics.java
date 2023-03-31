package eStoreSearch;

public class Electronics extends Products {
        

    protected String maker;

    /**
     * This constructor method initializes all instance variables for the
     * Electronics class
     * 
     * @param productID
     * @param description
     * @param year
     * @param price
     * @param maker
     * @throws Exception
     */
    public Electronics(String productID, String description, int year, double price, String maker) throws Exception {
        super (productID, description, year, price);
        this.maker = maker;
    }

    public Electronics ( Electronics theCopy ) {
        this.productID = theCopy.productID;
        this.description = theCopy.description;
        this.year = theCopy.year;
        this.price = theCopy.price;
        this.maker = theCopy.maker;
    }

    @Override
    public boolean equals ( Object theObject ) {
        if ( theObject == null || !(getClass() == theObject.getClass())) {
            return false;
        }
        else {
            Electronics theElectronic = (Electronics)theObject;
            return ( productID.equals(theElectronic.productID) && description.equals(theElectronic.description) &&
            year == (theElectronic.year) && price == (theElectronic.price) && maker.equals(theElectronic.maker) );
        }
    }

    public Electronics() {
        maker = "";
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
     * This method initializes the maker using the 'this' keyword
     * 
     * @param maker
     */
    public void setMaker(String maker) {
        this.maker = maker;
    }

    /**
     * This method prints the attributes for a electronic
     * 
     * return "type = \"" + getType() + "\"\nproductID = \"" + getProductID() + "\"\ndescription = \"" + getDescription() + "\"\nprice = \"" + getPrice() + "\"\nyear = \"" + getYear() + "\"\nmaker = \"" + getMaker() + "\""
     */
    @Override
    public String toString() {
        return ( "type = \"" + getType() + "\"\nproductID = \"" + getProductID() + "\"\ndescription = \"" + getDescription() + "\"\nprice = \"" + getPrice() + "\"\nyear = \"" + getYear() + "\"\nmaker = \"" + getMaker() + "\"" );
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