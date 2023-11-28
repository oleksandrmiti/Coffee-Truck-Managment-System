
//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//--------------------------------------------------
//
//	CLASS Customer
//
//--------------------------------------------------
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
    public MySQLCon(String url, String user, String password) {
        this.DB_URL = url;
        this.USER = user;
        this.PASSWORD = password;
    }

//---------------------------------------
//	GET METHODS
//---------------------------------------
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }


//---------------------------------------
//	EXTRA METHODS
//---------------------------------------
    public List<Customer> loadCustomers() {
        List<Customer> userList = new ArrayList<>();
        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Customer")) {

            while (rs.next()) {
                int id = rs.getInt("customerId");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Customer user = new Customer(id, firstName, lastName);
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }


    public void saveCustomers(List<Customer> customerList) {
        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
            // Cleaning all tables before saving a new data
            stmt.executeUpdate("DELETE FROM Customer");
            stmt.executeUpdate("DELETE FROM Drinks");
            stmt.executeUpdate("DELETE FROM Desserts");
            stmt.executeUpdate("DELETE FROM ActiveCart");


            System.out.println("Database cleared.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement("INSERT INTO Customer (customerId, firstName, lastName) VALUES (?, ?, ?)")) {
            for (Customer customer : customerList) {
                pstmt.setInt(1, customer.getID());
                pstmt.setString(2, customer.getFirstName());
                pstmt.setString(3, customer.getLastName());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            System.out.println("Customers saved to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> loadProducts() {
        List<Product> productList = new ArrayList<>();

        // Load Drinks from the Drinks table
        try (Connection con = getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Drinks")) {
            while (rs.next()) {
                int productId = rs.getInt("productId");
                String name = rs.getString("name");
                int size = rs.getInt("size");
                Drinks drink = new Drinks(productId, name, size); // You can set a default name or load it from a table if available
                productList.add(drink);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Load Desserts from the Desserts table
        try (Connection con = getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM Desserts")) {
            while (rs.next()) {
                int productId = rs.getInt("productId");
                String name = rs.getString("name");
                boolean isGlutenFree = rs.getBoolean("isGlutenFree");
                Desserts dessert = new Desserts(productId, name, isGlutenFree); // You can set a default name or load it from a table if available
                productList.add(dessert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    public void saveProducts(List<Product> productList) {
        // Save Drinks to the Drinks table
        try (
                Connection con = getConnection();
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO Drinks (productId, name, size) VALUES (?, ?, ?)")) {
            for (Product product : productList) {
                if (product instanceof Drinks) {
                    Drinks drink = (Drinks) product;
                    pstmt.setInt(1, drink.getID());
                    pstmt.setString(2, drink.getName());
                    pstmt.setInt(3, drink.getSize());
                    pstmt.addBatch();
                }
            }
            pstmt.executeBatch();
            System.out.println("Drinks saved to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Save Desserts to the Desserts table
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO Desserts (productId, name, isGlutenFree) VALUES (?, ?, ?)")) {
            for (Product product : productList) {
                if (product instanceof Desserts) {
                    Desserts dessert = (Desserts) product;
                    pstmt.setInt(1, dessert.getID());
                    pstmt.setString(2, dessert.getName());
                    pstmt.setBoolean(3, dessert.isGlutenFree());
                    pstmt.addBatch();
                }
            }
            pstmt.executeBatch();
            System.out.println("Desserts saved to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Order> loadActiveCart(int customerId) {
        ArrayList<Order> activeCart = new ArrayList<>();

        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();

            // Assuming "ActiveCart" table has columns: orderId, customerId, productId
            String query = "SELECT orderId, customerId, productId FROM ActiveCart WHERE customerId = " + customerId;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int orderId = resultSet.getInt("orderId");
                int productId = resultSet.getInt("productId");

                // Create the order and add it to the active cart
                Order order = new Order(orderId, customerId, productId);
                activeCart.add(order);
            }

            // Close resources (result set, statement, etc.)
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return activeCart;
    }

    public void saveActiveCart(Order order) {

        try {
            Connection con = getConnection();
            String query = "INSERT INTO ActiveCart (orderId, customerId, productId) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);


            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getCustomerId());
            preparedStatement.setInt(3, order.getProductId());

            // Execute the insert query
            preparedStatement.executeUpdate();

            System.out.println("Desserts saved to the database.");

            // Close resources (statement, etc.)
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}