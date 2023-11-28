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
public class CoffeeTruckImpl implements CoffeeTruck {


    //---------------------------------------
//	Fields
//---------------------------------------
    private static ArrayList<Customer> customerList;
    private ArrayList<Product> productList;
    private static int nextCustomerId = 100;
    private static int nextProductId = 200;
    private static int nextOrderId = 300;


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

    public int addCustomers(List<Customer> customerList) {

        if (customerList != null && !customerList.isEmpty()) {
            int customersAdded = 0;

            for (Customer newCustomer : customerList) {
                // Check if the customer with the same ID already exists in the list
                boolean alreadyExists = this.customerList.stream()
                        .anyMatch(existingCustomer -> existingCustomer.getID() == newCustomer.getID());

                // If the customer doesn't already exist, add it to the list
                if (!alreadyExists) {
                    this.customerList.add(newCustomer);
                    customersAdded++;
                    // Set next customerId to the ID of the last created customer
                    CoffeeTruckImpl.nextCustomerId = newCustomer.getID() + 1;
                }
            }

            return customersAdded;
        } else {
            // If the provided list is null or empty, return 0 to indicate no customers were added
            return 0;
        }
    }


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

    @Override
    public void addProducts(List<Product> productList) {
        for (Product product : productList) {
            if (!productListContainsId(product.getID())) {
                this.productList.add(product);
            }
        }
    }

    private boolean productListContainsId(int productId) {
        for (Product product : this.productList) {
            if (product.getID() == productId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeCustomer(int customerId) {
//        return customerList.removeIf(customer -> customer.getID() == customerId);
        // Find and remove the customer with the specified ID
        Iterator<Customer> iterator = customerList.iterator();
        while (iterator.hasNext()) {
            Customer customer = iterator.next();
            if (customer.getID() == customerId) {
                iterator.remove();
                return true; // Customer removed successfully
            }
        }
        return false; // Customer not found
    }

    @Override
    public boolean removeProduct(int productId) {

        // Find and remove the product with the specified ID
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getID() == productId) {
                iterator.remove();
                return true; // Product removed successfully
            }
        }
        return false; // Product not found
    }

    @Override
    public void displayCustomers() {
        // 1.0. We display every customer from the list
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
    }

    @Override
    public int displayProducts() {
        if (productList.isEmpty()) {
            System.out.println("Sorry, but we are empty now.");
        } else{
            System.out.println("Available Products:");
            for (Product product : productList) {
                System.out.println("ID: " + product.getID() + ", Name: " + product.getName());

                if (product instanceof Drinks) {
                    Drinks drink = (Drinks) product;
                    System.out.println("  Type: Drink, Size: " + drink.getSize());
                } else if (product instanceof Desserts) {
                    Desserts dessert = (Desserts) product;
                    System.out.println("  Type: Dessert, Gluten Free: " + dessert.isGlutenFree());
                }
            }
        }
        return 0;
    }

    @Override
    public boolean displayCustomerInfo(int customerId) {
        for (Customer customer : customerList) {
            if (customer.getID() == customerId) {
                System.out.println(customer);
                return true;
            }
        }
        return false;
    }

    @Override
    public int makeOrder(int customerId, int productId) {
        Customer customer = findCustomer(customerId);
        Product product = findProduct(productId);
        int _id = CoffeeTruckImpl.nextOrderId;

        if (customer != null && product != null) {
            Order order = new Order(_id, customerId, productId);
            customer.addToCart(order);
            CoffeeTruckImpl.nextOrderId++;
            return order.getOrderId();
        }
        return -1; // Indicate failure
    }

    private Product findProduct(int productId) {
        for (Product product : productList) {
            if (product.getID() == productId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void displayOrders() {
        for (Customer customer : customerList) {
            System.out.println("Customer ID: " + customer.getID());
            System.out.println("Active Cart:");
            for (Order order : customer.getActiveCart()) {
                System.out.println(order);
            }
            System.out.println("------------");
        }
    }


    public void saveCustomersToDB(MySQLCon sql) {
        // 1.0. We save customerList to the DB
        sql.saveCustomers(customerList);
    }

    public void saveProductsToDB(MySQLCon sql) {
        sql.saveProducts(productList);
    }

    public void saveActiveCartToDB(MySQLCon sql) {
        for (Customer customer : customerList) {
            for (Order order : customer.getActiveCart()) {
                sql.saveActiveCart(order);
            }
        }
    }

    public void loadActiveCart(MySQLCon sql, List<Customer> customerList) {
        for (Customer customer : customerList) {
            ArrayList<Order> activeCart = sql.loadActiveCart(customer.getID());
            customer.setActiveCart(activeCart);
        }
    }


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
}