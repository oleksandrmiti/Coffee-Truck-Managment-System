//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//--------------------------------------------------
//
//	CLASS MySQLCon
//
//--------------------------------------------------

/**
 * This is MySQLCon class where implemented methods to deal with the DB
 */
public class MySQLCon {

//---------------------------------------
//	Fields
//---------------------------------------
    private final String DB_URL;
    private final String USER;
    private final String PASSWORD;

//---------------------------------------
//	Constructor
//---------------------------------------

    /**
     *  SQL Con that connects DB with program and gives access to load/save the data to the database.
     * @param url -: URL to the database
     * @param user -: The name of the user that has the access to the database
     * @param password -: The password of the user
     */
    public MySQLCon(String url, String user, String password) {
        // 1.0. We initialise fields
        this.DB_URL = url;
        this.USER = user;
        this.PASSWORD = password;
    }

//---------------------------------------
//	GET METHODS
//---------------------------------------

    //---------------------------------------
    //	getConnection
    //---------------------------------------

    /**
     * This method checks connection with the database
     * @return -: returns a connection object to interact with the database
     * @throws SQLException -: if there is a problem with connection
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }


//---------------------------------------
//	EXTRA METHODS
//---------------------------------------

    //---------------------------------------
    //	loadCustomers
    //---------------------------------------

    /**
     * This method is loading customers to the program
     * @return -: userList with the customers from the database
     */
    public List<Customer> loadCustomers() {
        // 1.0. Create a new user list
        List<Customer> userList = new ArrayList<>();

        // 2.0. Check if there is a connection with the database
        try (Connection con = getConnection();
             // 2.1. Create a statement that will be entered to the Query
             Statement stmt = con.createStatement();

             // 2.2. Implement the statement and get a result
             ResultSet rs = stmt.executeQuery("SELECT * FROM Customer")) {

            // 2.3. For every field of the database
            while (rs.next()) {
                // 2.3.1. Get the ID of the customer
                int id = rs.getInt("customerId");

                // 2.3.2. Get the first name of the customer
                String firstName = rs.getString("firstName");

                // 2.3.3. Get the last name of the customer
                String lastName = rs.getString("lastName");

                // 2.3.4. Create a new user
                Customer user = new Customer(id, firstName, lastName);

                // 2.3.4. Add a new user to the userList
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 3.0. Return a list with new users
        return userList;
    }

    //---------------------------------------
    //	saveCustomers
    //---------------------------------------

    /**
     *  This method is saving customers to the database
     * @param customerList :- a list with existing customers
     */
    public void saveCustomers(List<Customer> customerList) {
        // 1.0. Check connection and create a new statement
        try (
                Connection con = getConnection();
                Statement stmt = con.createStatement()
        ) {
            // 1.1. Clear all tables before saving a new database
            stmt.executeUpdate("DELETE FROM Customer");
            stmt.executeUpdate("DELETE FROM Drinks");
            stmt.executeUpdate("DELETE FROM Desserts");
            stmt.executeUpdate("DELETE FROM ActiveCart");

            // 1.2. Output that database has been cleared
            System.out.println("Database cleared.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 2.0. Check connection and create a new prepared statement for inserting values
        try (
                Connection con = getConnection();
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO Customer (customerId, firstName, lastName) VALUES (?, ?, ?)")
        ) {

            // 2.1. For each customer in the list
            for (Customer customer : customerList) {
                // 2.1.1. For the first parameter we are getting an ID of the customer
                pstmt.setInt(1, customer.getID());

                // 2.1.2. For the second parameter we are getting a first name of the customer
                pstmt.setString(2, customer.getFirstName());

                // 2.1.3. For the third parameter we are getting a last name of the customer
                pstmt.setString(3, customer.getLastName());

                // 2.1.4. Add the row to the batch
                pstmt.addBatch();
            }
            // 2.2. Insert batch
            pstmt.executeBatch();

            // 2.3. Output that customers are saved to the database
            System.out.println("Customers saved to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------
    //	loadProducts
    //---------------------------------------

    /**
     * This method is loading products from the database
     * @return :- new product list with the products from the database
     */
    public List<Product> loadProducts() {
        // 1.0. Create a new product list
        List<Product> productList = new ArrayList<>();

        // 2.0. Load Drinks from the Drinks table
        try (
                Connection con = getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Drinks")
        ) {
            // 2.1. For each raw in the drinks table
            while (rs.next()) {
                // 2.2.1. Get the ID of the drink
                int productId = rs.getInt("productId");

                // 2.2.2. Get the name of the drink
                String name = rs.getString("name");

                // 2.2.3. Get the size of the drink
                int size = rs.getInt("size");

                // 2.2.4. Create a new drink
                Drinks drink = new Drinks(productId, name, size);

                // 2.2.5. Add a new drink to the product list
                productList.add(drink);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 3.0. Load Desserts from the Desserts table
        try (
                Connection con = getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Desserts")
        ) {
            // 3.1.1 For each raw in the desserts table
            while (rs.next()) {
                // 3.2.1. Get the ID of the dessert
                int productId = rs.getInt("productId");

                // 3.2.2. Get the name of the dessert
                String name = rs.getString("name");

                // 3.2.3. Get the isGlutenFree of the dessert
                boolean isGlutenFree = rs.getBoolean("isGlutenFree");

                // 3.2.4. Create a new dessert
                Desserts dessert = new Desserts(productId, name, isGlutenFree);

                // 3.2.5. Add a new dessert to the product list
                productList.add(dessert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 4.0. Return a new product list
        return productList;
    }

    //---------------------------------------
    //	saveProducts
    //---------------------------------------

    /**
     * This method is saving products to the database
     * @param productList -: an existing list with the products
     */
    public void saveProducts(List<Product> productList) {
        // 1.0. Save Drinks to the Drinks table
        try (
                //1.1.1 Get the connection with the database
                Connection con = getConnection();

                //1.1.2. Create a prepared statement
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO Drinks (productId, name, size) VALUES (?, ?, ?)")
        ) {
            // 1.2.1. For each product in the product list
            for (Product product : productList) {
                // 1.3.1. Check if the product is the instance of Drinks
                if (product instanceof Drinks) {

                    // 1.3.2. Get the drink
                    Drinks drink = (Drinks) product;

                    // 1.3.3. For the first parameter we are getting an ID of the drink
                    pstmt.setInt(1, drink.getID());

                    // 1.3.4. For the second parameter we are getting a name of the drink
                    pstmt.setString(2, drink.getName());

                    // 1.3.5. For the third parameter we are getting a size of the drink
                    pstmt.setInt(3, drink.getSize());

                    // 1.3.6. Add the row to the batch
                    pstmt.addBatch();
                }
            }
            // 2.0. Insert batch
            pstmt.executeBatch();

            // 3.0. Output that drinks were saved to the database
            System.out.println("Drinks saved to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 1.0. Save Desserts to the Desserts table
        try (
                //1.1.1 Get the connection with the database
                Connection con = getConnection();
                //1.1.2. Create a prepared statement
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO Desserts (productId, name, isGlutenFree) VALUES (?, ?, ?)")
        ) {
            // 1.2.1. For each product in the product list
            for (Product product : productList) {
                // 1.3.1. Check if the product is the instance of Desserts
                if (product instanceof Desserts) {
                    // 1.3.2. Get the dessert
                    Desserts dessert = (Desserts) product;

                    // 1.3.3. For the first parameter we are getting an ID of the dessert
                    pstmt.setInt(1, dessert.getID());

                    // 1.3.4. For the second parameter we are getting a name of the dessert
                    pstmt.setString(2, dessert.getName());

                    // 1.3.5. For the third parameter we are getting a isGlutenFree
                    pstmt.setBoolean(3, dessert.isGlutenFree());

                    // 1.3.6. Add the row to the batch
                    pstmt.addBatch();
                }
            }
            // 2.0. Insert batch
            pstmt.executeBatch();

            // 3.0. Output that desserts were saved to the database
            System.out.println("Desserts saved to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //---------------------------------------
    //	loadActiveCart
    //---------------------------------------

    /**
     * This method is loading active cart from the database
     * @param customerId -: the id of the customer for who we are loading an active cart
     * @return -: a new active cart loaded from the database
     */
    public ArrayList<Order> loadActiveCart(int customerId) {
        // 1.0. Create a new active cart
        ArrayList<Order> activeCart = new ArrayList<>();

        try {
            // 2.1. Get the connection with the database
            Connection con = getConnection();

            // 2.2. Create a statement
            Statement statement = con.createStatement();

            // 2.3. We find orders of the customer
            String query = "SELECT orderId, customerId, productId FROM ActiveCart WHERE customerId = " + customerId;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // 3.1. Get an ID of the order
                int orderId = resultSet.getInt("orderId");

                // 3.2. Get the product ID
                int productId = resultSet.getInt("productId");

                // 3.3. Create the order and add it to the active cart
                Order order = new Order(orderId, customerId, productId);

                // 3.4. Add the order to the active cart
                activeCart.add(order);

                // 3.5. We set a n
                CoffeeTruckImpl.nextOrderId++;
            }

            // 4.0. Close resources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 5.0. Return a new activeCart for this customer
        return activeCart;
    }

    //---------------------------------------
    //	saveActiveCart
    //---------------------------------------

    /**
     * This method is saving active cart to the database
     * @param order -: it's an order that customer has created
     */
    public void saveActiveCart(Order order) {
        try {
            // 1.0. Get the connection with the database and create a statement
            Connection con = getConnection();
            String query = "INSERT INTO ActiveCart (orderId, customerId, productId) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);

            // 2.1. For the first parameter we are getting an ID of the order
            preparedStatement.setInt(1, order.getOrderId());

            // 2.2. For the second parameter we are getting a customer ID of the order
            preparedStatement.setInt(2, order.getCustomerId());

            // 2.3. For the third parameter we are getting a product ID in the order
            preparedStatement.setInt(3, order.getProductId());

            // 3.0. Execute the insert query
            preparedStatement.executeUpdate();

            // 4.0. Output that active cart is saved to the database
            System.out.println("Active cart saved to the database.");

            // 5.0. Close resources
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}