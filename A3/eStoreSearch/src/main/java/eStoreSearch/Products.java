package eStoreSearch;

abstract class Products {

    protected String productID;
    protected String description;
    protected int year;
    protected String type;
    protected double price;

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
    public Products(String productID, String description, int year, double price) throws Exception {
        if ( !(productID.equals("") || productID.equals(null))) {
            this.productID = productID;
        }
        else{
            throw new Exception ("Fatal error: productID cannot be left blank");
        }
        if ( !(description.equals("") || description.equals(null))) {
            this.description = description;
        }
        else{
            throw new Exception ("Fatal error: description cannot be left blank");
        }
        if ( !(year == 0) ) {
            this.year = year;
        }
        else{
            throw new Exception ("Fatal error: year cannot be left blank");
        }
        this.price = price;
    }

    public Products() {
        productID = "";
        description = "";
        year = 0;
        price = 0.0;
    }

    /**
     * This method header returns the productID
     * 
     * @return productID
     */
    public abstract String getProductID();

    /**
     * This method header returns the description
     * 
     * @return description
     */
    public abstract String getDescription();

    /**
     * This method header returns the year
     * 
     * @return year
     */
    public abstract int getYear();

    /**
     * This method header returns the price
     * 
     * @return price
     */
    public abstract double getPrice();

    /**
     * This method header returns the type
     * 
     * @return type
     */
    public abstract String getType();

    
    /**
     * This method header initializes the productID using the 'this' keyword
     * 
     * @param productID
     * @throws Exception
     */
    public abstract void setProductID(String productID) throws Exception;

    /**
     * This method header initializes the description using the 'this' keyword
     * 
     * @param description
     * @throws Exception
     */
    public abstract void setDescription(String description) throws Exception;

    /**
     * This method header initializes the year using the 'this' keyword
     * 
     * @param year
     * @throws Exception
     */
    public abstract void setYear(int year) throws Exception;

    /**
     * This method header initializes the price using the 'this' keyword
     * 
     * @param price
     */
    public abstract void setPrice(double price);

    /**
     * This method header initializes the type using the 'this' keyword
     * 
     * @param type
     */
    public abstract void setType(String type);

}
