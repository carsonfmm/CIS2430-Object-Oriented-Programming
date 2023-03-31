package eStoreSearch;

public class Electronics extends Products {
        

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
    public Electronics ( String productID, String description, int year, double price, String maker ) {
        super (productID, description, year, price);
        this.maker = maker;
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
}