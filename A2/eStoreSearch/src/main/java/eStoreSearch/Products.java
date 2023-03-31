package eStoreSearch;

public class Products {

    private String productID;
    private String description;
    private int year;
    private String type;
    private double price;

    /**
     * This constructor method initializes all instance variables for the Product class
     * 
     * @param productID
     * @param description
     * @param year
     * @param price
     */
    public Products ( String productID, String description, int year, double price ) {
        this.productID = productID;
        this.description = description;
        this.year = year;
        this.price = price;
    }

    public Products() {
        productID = "";
        description = "";
        year = 0;
        price = 0.0;
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
     */
    public void setProductID(String productID) {
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
     * This method initializes the type using the 'this' keyword
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

}
