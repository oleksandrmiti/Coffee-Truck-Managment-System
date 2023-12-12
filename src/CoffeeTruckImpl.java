//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//--------------------------------------------------
//
//	CLASS CoffeeTruckImpl
//
//--------------------------------------------------

/**
 * This class that implements an interface
 */
public class CoffeeTruckImpl implements CoffeeTruck {


//---------------------------------------
//	Fields
//---------------------------------------
    private static ArrayList<Customer> customerList;
    private ArrayList<Product> productList;
    private static int nextCustomerId = 100;
    private static int nextProductId = 200;
    protected static int nextOrderId = 300;

//---------------------------------------
//	Constructor
//---------------------------------------
    public CoffeeTruckImpl() {
        // 1.0 We assume that no customers and products created
        customerList = new ArrayList<Customer>();
        this.productList = new ArrayList<Product>();
    }
//---------------------------------------
//	EXTRA METHODS
//---------------------------------------

    //---------------------------------------
    //	addCustomer
    //---------------------------------------

    /**
     * This method is adding a local customer
     * @param _firstName -: the first name of the customer
     * @param _lastName -: the last name of the customer
     * @return an ID of the new customer
     */
    @Override
    public int addCustomer(String _firstName, String _lastName) {
        // 1.0. We create a local variable for customer Id
        int _id = CoffeeTruckImpl.nextCustomerId;

        // 2.0. We create a new obj of Customer
        Customer newCustomer = new Customer(_id, _firstName, _lastName);

        // 3.0. Add the new customer to the customerList
        this.customerList.add(newCustomer);

        // 4.0. We increase an ID for the next customer by 1
        CoffeeTruckImpl.nextCustomerId++;

        // 5.0. We return an ID of the new customer
        return newCustomer.getID();
    }

    //---------------------------------------
    //	addCustomer
    //---------------------------------------

    /**
     * This method is overloading of the addCustomer method to load customers from the database
     * @param customerList a list of the customers imported from the database
     * @return a number of customers were added
     */
    public int addCustomer(List<Customer> customerList) {
        // 1.0. Check if customerList is not empty
        if (customerList != null && !customerList.isEmpty()) {
            // 1.1. Create a local variable
            int customersAdded = 0;

            // 1.2. For each new customer in the customer list
            for (Customer newCustomer : customerList) {
                // 1.2.1. Check if the customer with the same ID already exists in the list
                boolean alreadyExists = this.customerList.stream()
                        .anyMatch(existingCustomer -> existingCustomer.getID() == newCustomer.getID());

                // 1.2.2. If the customer doesn't already exist, add it to the list
                if (!alreadyExists) {
                    this.customerList.add(newCustomer);
                    customersAdded++;

                    // 1.2.3. Set next customerId to the ID of the last created customer
                    CoffeeTruckImpl.nextCustomerId = newCustomer.getID() + 1;
                }
            }
            // 1.3. Return a number of customers were added
            return customersAdded;
        } else {
            // 1.4. If the provided list is null or empty, return 0 to indicate no customers were added
            return 0;
        }
    }

    //---------------------------------------
    //	addDrink
    //---------------------------------------

    /**
     * This method is adding a new drink to product list
     * @param _name -: the name of the drink
     * @param _size -: the size of the drink
     * @return an ID of the new drink
     */
    public int addDrink(String _name, int _size) {
        // 1.0. We create a new local variable for product Id
        int _id = CoffeeTruckImpl.nextProductId;

        // 2.0. We create a new obj of Drink
        Drinks newDrink = new Drinks(_id, _name, _size);

        // 3.0. Add the new drink to the productList
        productList.add(newDrink);

        // 4.0. We increase an ID by one for the next variable
        CoffeeTruckImpl.nextProductId++;

        // 5.0. We return an ID of the new product
        return newDrink.getID();
    }

    //---------------------------------------
    //	addDessert
    //---------------------------------------

    /**
     * This method is adding a new dessert to the product list
     * @param _name -: the name of the dessert
     * @param _isGlutenFree -: is this dessert gluten-free or not
     * @return an ID of the new dessert
     */
    @Override
    public int addDessert(String _name, boolean _isGlutenFree) {
        // 1.0. We create a new local variable for product Id
        int _id = CoffeeTruckImpl.nextProductId;

        // 2.0. We create a new obj of Dessert
        Desserts newDessert = new Desserts(_id, _name, _isGlutenFree);

        // 3.0. Add the new dessert to the productList
        productList.add(newDessert);

        // 4.0. We increase an ID by one for the next variable
        CoffeeTruckImpl.nextProductId++;

        // 5.0. We return an ID of the new product
        return newDessert.getID();
    }

    //---------------------------------------
    //	addProducts
    //---------------------------------------

    /**
     * This method is adding a products from the database to program
     * @param productList -: a list of the products imported from the database
     */
    @Override
    public void addProducts(List<Product> productList) {
        // 1.0. For each product in the product list
        for (Product product : productList) {

            // 1.1. If the product not in the list
            if (!productListContainsId(product.getID())) {

                // 1.2. Add the product to the list
                this.productList.add(product);

                // 1.3. Update a nextProductId
                CoffeeTruckImpl.nextProductId++;
            }
        }
    }

    //---------------------------------------
    //	removeCustomer
    //---------------------------------------

    /**
     * This method is removing a customer from the list of customers
     * @param customerId -: an ID of the customer that needs to be removed
     * @return boolean that indicated was the customer removed or not
     */
    @Override
    public boolean removeCustomer(int customerId) {
        // 1.0. Create an iterator
        Iterator<Customer> iterator = customerList.iterator();

        while (iterator.hasNext()) {
            // 2.0. Take a customer
            Customer customer = iterator.next();

            // 3.0 Remove a customer if the provided ID match
            if (customer.getID() == customerId) {
                iterator.remove();
                return true;
            }
        }

        // 4.0. Return false if the customer is not removed
        return false;
    }

    //---------------------------------------
    //	removeProduct
    //---------------------------------------

    /**
     * This method is removing a product from the product list
     * @param productId -: an ID of the product that needs to be removed
     * @return boolean that indicated was the product removed or not
     */
    @Override
    public boolean removeProduct(int productId) {
        // 1.0. Create an iterator
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {

            // 2.0. Take a product
            Product product = iterator.next();

            // 3.0 Remove a product if the provided ID match
            if (product.getID() == productId) {
                iterator.remove();
                return true;
            }
        }
        // 4.0. Return false if the product is not removed
        return false;
    }

    //---------------------------------------
    //	displayCustomers
    //---------------------------------------

    /**
     * This method is displaying a list of customers
     */
    @Override
    public void displayCustomers() {
        // 1.0. We display every customer from the list
        for (Customer customer : customerList) {
            int id = customer.getID();
            String firstName = customer.getFirstName();
            String lastName = customer.getLastName();
            System.out.println("\n\n===========================");
            System.out.println("ID = "+ id+"\n\tFirst Name: "+firstName+"\n\tLast Name: "+lastName);
        }
    }

    //---------------------------------------
    //	displayProducts
    //---------------------------------------

    /**
     * This method is displaying a list of products
     * @return 1 or 0. 0 means that no products in the list, 1 means that list is not empty
     */
    @Override
    public int displayProducts() {
        // 1.0. If the product list in not empty
        if (productList.isEmpty()) {
            System.out.println("Sorry, but we are empty now.");
        } else{
            // 2.0. Output available product
            System.out.println("Available Products:");

            // 3.0. For every product in the product list
            for (Product product : productList) {
                // 3.1. Output information about product
                System.out.println("ID: " + product.getID() + ", Name: " + product.getName());

                // 3.2. Check if this an instance of Drink or Dessert
                if (product instanceof Drinks) {
                    Drinks drink = (Drinks) product;
                    System.out.println("  Type: Drink, Size: " + drink.getSize());
                } else if (product instanceof Desserts) {
                    Desserts dessert = (Desserts) product;
                    System.out.println("  Type: Dessert, Gluten Free: " + dessert.isGlutenFree());
                }
            }
            return 1;
        }
        return 0;
    }

    //---------------------------------------
    //	displayCustomerInfo
    //---------------------------------------

    /**
     * This method is displaying an information about the customer
     * @param customerId -: an ID of the customer
     * @return a boolean that shows is this customer exist in the system
     */
    @Override
    public boolean displayCustomerInfo(int customerId) {
        // 1.0. For every customer in the customer list
        for (Customer customer : customerList) {
            // 2.0. If provided ID match customer
            if (customer.getID() == customerId) {
                // 3.0. Output information about customer
                System.out.println(customer);
                return true;
            }
        }
        return false;
    }

    //---------------------------------------
    //	makeOrder
    //---------------------------------------

    /**
     * This method is making an order
     * @param customerId -: an ID of the customer who is making an order
     * @param productId -: an ID of the product that customer want to order
     * @return an OrderId
     */
    @Override
    public int makeOrder(int customerId, int productId) {
        // 1.0. Find the customer with provided ID
        Customer customer = findCustomer(customerId);

        // 2.0. Find the product with provided ID
        Product product = findProduct(productId);

        // 3.0 Get an order ID
        int _id = CoffeeTruckImpl.nextOrderId;

        // 4.0 If the customer and product is null we make an order
        if (customer != null && product != null) {
            Order order = new Order(_id, customerId, productId);
            customer.addToCart(order);
            CoffeeTruckImpl.nextOrderId++;
            return order.getOrderId();
        }
        return -1; // Indicate failure
    }

    //---------------------------------------
    //	displayOrders
    //---------------------------------------

    /**
     * This method is displaying all orders
     */
    @Override
    public void displayOrders() {
        // 1.0. For each customer in the customer list
        for (Customer customer : customerList) {
            // 1.1. Output the order information for this customer
            System.out.println("Customer ID: " + customer.getID());
            System.out.println("Active Cart:");
            for (Order order : customer.getActiveCart()) {
                System.out.println(order);
            }
            System.out.println("------------");
        }
    }

    //---------------------------------------
    //	saveCustomersToDB
    //---------------------------------------

    /**
     * This method is saving a customers to the database
     * @param sql -: an object that makes a connection to the database
     */
    public void saveCustomersToDB(MySQLCon sql) {
        // 1.0. We save customerList to the DB
        sql.saveCustomers(customerList);
    }

    //---------------------------------------
    //	saveProductsToDB
    //---------------------------------------

    /**
     * This method is saving a products to the database
     * @param sql -: an object that makes a connection to the database
     */
    public void saveProductsToDB(MySQLCon sql) {
        // 1.0, We save productList to the DB
        sql.saveProducts(productList);
    }

    //---------------------------------------
    //	loadActiveCart
    //---------------------------------------

    /**
     * This method is loading an active cart from the database
     * @param sql -: an object that makes a connection to the database
     * @param customerList -: a list of customers
     */
    public void loadActiveCart(MySQLCon sql, List<Customer> customerList) {
        for (Customer customer : customerList) {
            ArrayList<Order> activeCart = sql.loadActiveCart(customer.getID());
            customer.setActiveCart(activeCart);
        }
    }

    //---------------------------------------
    //	saveActiveCartToDB
    //---------------------------------------

    /**
     * This method is saving an active cart to the database
     * @param sql -: an object that makes a connection to the database
     */
    public void saveActiveCartToDB(MySQLCon sql) {
        for (Customer customer : customerList) {
            for (Order order : customer.getActiveCart()) {
                sql.saveActiveCart(order);
            }
        }
    }


    //---------------------------------------
    //	findCustomer
    //---------------------------------------

    /**
     * Help method to find a Customer by customerId
     *
     * @param customerId -- customer ID
     * @return customer if find it in the customer list
     */
    public Customer findCustomer(int customerId) {
        for (Customer customer : customerList) {
            if (customer.getID() == customerId) {
                return customer;
            }
        }
        return null;
    }

    //---------------------------------------
    //	findProduct
    //---------------------------------------

    /**
     * This is a helper method that find product in the list
     * @param productId -: a product ID of the product that we are looking for
     * @return a product
     */
    private Product findProduct(int productId) {
        // 1.0. For each product in the product list
        for (Product product : productList) {

            // 2.0. If the provided ID match we return a product
            if (product.getID() == productId) {
                return product;
            }
        }
        return null;
    }

    //---------------------------------------
    //	productListContainsId
    //---------------------------------------

    /**
     * This is a help method that check is the product in the list or not
     * @param productId -: an ID of the product
     * @return true if product in the list or false if it's not
     */
    private boolean productListContainsId(int productId) {
        // 1.0. For each product in the product list
        for (Product product : this.productList) {

            // 1.1. If the product in the list return true
            if (product.getID() == productId) {
                return true;
            }
        }

        // 2.0. Return false if the product not in the list
        return false;
    }

}