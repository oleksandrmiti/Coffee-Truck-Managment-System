//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------A


import java.util.List;

//--------------------------------------------------
//
//	INTERFACE CoffeeTruck
//
//--------------------------------------------------
public interface CoffeeTruck {


    //---------------------------------------
    //	addCustomer
    //---------------------------------------

    int addCustomer(String _firstName, String _lastName);

    int addCustomers(List<Customer> customerList);

    int addDrink(String _name, int _size);

    int addDessert(String _name, boolean _isGlutenFree);


    void addProducts(List<Product> productList);

    boolean removeCustomer(int customerId);

    boolean removeProduct(int productId);

    void displayCustomers();

    int displayProducts();

    boolean displayCustomerInfo(int customerId);

    int makeOrder(int customerId, int productId);

    void loadActiveCart(MySQLCon sql, List<Customer> customerList);

    void displayOrders();

    void saveCustomersToDB(MySQLCon sql);

    void saveProductsToDB(MySQLCon sql);

    void saveActiveCartToDB(MySQLCon sql);
}