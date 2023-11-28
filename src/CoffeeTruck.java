//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------A
import java.util.List;

//--------------------------------------------------
//
//	INTERFACE CoffeeTruck
//
//--------------------------------------------------
/**
 * This is an interface class to give an access to manage a coffee truck
 */
public interface CoffeeTruck {


    //---------------------------------------
    //	addCustomer
    //---------------------------------------

    /**
     * This method is adding a local customer
     * @param _firstName -: the first name of the customer
     * @param _lastName -: the last name of the customer
     * @return an ID of the new customer
     */
    int addCustomer(String _firstName, String _lastName);

    //---------------------------------------
    //	addCustomer
    //---------------------------------------

    /**
     * This method is overloading of the addCustomer method to load customers from the database
     * @param customerList a list of the customers imported from the database
     * @return a number of customers were added
     */
    int addCustomer(List<Customer> customerList);

    //---------------------------------------
    //	addDrink
    //---------------------------------------

    /**
     * This method is adding a new drink to product list
     * @param _name -: the name of the drink
     * @param _size -: the size of the drink
     * @return an ID of the new drink
     */
    int addDrink(String _name, int _size);

    //---------------------------------------
    //	addDessert
    //---------------------------------------

    /**
     * This method is adding a new dessert to the product list
     * @param _name -: the name of the dessert
     * @param _isGlutenFree -: is this dessert gluten-free or not
     * @return an ID of the new dessert
     */
    int addDessert(String _name, boolean _isGlutenFree);

    //---------------------------------------
    //	addProducts
    //---------------------------------------

    /**
     * This method is adding a products from the database to program
     * @param productList -: a list of the products imported from the database
     */
    void addProducts(List<Product> productList);

    //---------------------------------------
    //	removeCustomer
    //---------------------------------------

    /**
     * This method is removing a customer from the list of customers
     * @param customerId -: an ID of the customer that needs to be removed
     * @return boolean that indicated was the customer removed or not
     */
    boolean removeCustomer(int customerId);

    //---------------------------------------
    //	removeProduct
    //---------------------------------------

    /**
     * This method is removing a product from the product list
     * @param productId -: an ID of the product that needs to be removed
     * @return boolean that indicated was the product removed or not
     */
    boolean removeProduct(int productId);

    //---------------------------------------
    //	displayCustomers
    //---------------------------------------

    /**
     * This method is displaying a list of customers
     */
    void displayCustomers();

    //---------------------------------------
    //	displayProducts
    //---------------------------------------

    /**
     * This method is displaying a list of products
     * @return 1 or 0. 0 means that no products in the list, 1 means that list is not empty
     */
    int displayProducts();

    //---------------------------------------
    //	displayCustomerInfo
    //---------------------------------------

    /**
     * This method is displaying an information about the customer
     * @param customerId -: an ID of the customer
     * @return a boolean that shows is this customer exist in the system
     */
    boolean displayCustomerInfo(int customerId);

    //---------------------------------------
    //	makeOrder
    //---------------------------------------

    /**
     * This method is making an order
     * @param customerId -: an ID of the customer who is making an order
     * @param productId -: an ID of the product that customer want to order
     * @return an order ID
     */
    int makeOrder(int customerId, int productId);

    //---------------------------------------
    //	displayOrders
    //---------------------------------------

    /**
     * This method is displaying all orders
     */
    void displayOrders();

    //---------------------------------------
    //	saveCustomersToDB
    //---------------------------------------

    /**
     * This method is saving a customers to the database
     * @param sql -: an object that makes a connection to the database
     */
    void saveCustomersToDB(MySQLCon sql);

    //---------------------------------------
    //	saveProductsToDB
    //---------------------------------------

    /**
     * This method is saving a products to the database
     * @param sql -: an object that makes a connection to the database
     */
    void saveProductsToDB(MySQLCon sql);

    //---------------------------------------
    //	loadActiveCart
    //---------------------------------------

    /**
     * This method is loading an active cart from the database
     * @param sql -: an object that makes a connection to the database
     * @param customerList -: a list of customers
     */
    void loadActiveCart(MySQLCon sql, List<Customer> customerList);

    //---------------------------------------
    //	saveActiveCartToDB
    //---------------------------------------

    /**
     * This method is saving an active cart to the database
     * @param sql -: an object that makes a connection to the database
     */
    void saveActiveCartToDB(MySQLCon sql);

}