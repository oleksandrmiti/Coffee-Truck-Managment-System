/**
 * Author: Oleksandr Mitichkin
 * Date: 16.11.2023
 * Description: My final project for OOP Module in Semester 1 Year 2 of Software Development Honours course at MTU
 *              This project is a program to manage a Coffee Truck.
 */
//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.util.List;
import java.util.Scanner;

//--------------------------------------------------
//
//	CLASS MyMain
//
//--------------------------------------------------

/**
 *  This is a Main class for Assignment 3.
 */
public class MyMain {

    //--------------------------------------------------
    //	outputMenu
    //--------------------------------------------------

    /**
     *  This function print's menu for user
     */

    public static void outputMenu() {
        // 1.0. "update" menu
        for (int i = 0; i < 20; i++){
            System.out.println("\n");
        }

        // 2.0. Show menu options for customer
        System.out.println("============================");
        System.out.println("\t\t\tMenu");
        System.out.println("============================");
        System.out.println("Please choose from the follow menu: ");
        System.out.println("1. Add Customer.");
        System.out.println("2. Add Drink.");
        System.out.println("3. Add Dessert.");
        System.out.println("4. Remove Customer.");
        System.out.println("5. Remove Product.");
        System.out.println("6. Display All Customers.");
        System.out.println("7. Display Customer Information.");
        System.out.println("8. Make New Order.");
        System.out.println("9. Display Orders.");
        System.out.println("10. Load Database.");
        System.out.println("11. Save Database.");
        System.out.println("0. Exit.");
    }

    //--------------------------------------------------
    //	getOption
    //--------------------------------------------------

    /**
     * This user asks customer to enter an Int option. It will not return value until user enters an integer.
     * @param sc -: The scanner that taking input from the user
     * @return an integer that user has entered
     */
    public static int getOption(Scanner sc) {
        // 1.0. Create a local variable for looping
        boolean correctOption = false;

        // 2.0. Loop asking user until get a correct option
        do{
            try {
                // I. Create a local variable for result
                int res;

                // II. Ask user to enter an option
                System.out.println("Please enter your choice:");
                res = sc.nextInt();

                // III. Stop loop if user entered an int
                correctOption = true;

                // IV. Return the result
                return res;
            } catch (java.util.InputMismatchException e){
                System.out.println("Please use numbers. ");
                sc.next(); // Call scanner again
            }
        } while (!correctOption);
        return 0; // Dummy return
    }

    //--------------------------------------------------
    //	getBooleanOption
    //--------------------------------------------------

    /**
     * This function is returning a boolean true if user enter 1 and false if user enter 0.
     * We need this method only for handling input for Dessert.
     * @param sc -: The scanner that taking input from the user
     * @return @param isGlutenFree
     */
    public static boolean getBooleanOption(Scanner sc){

        // 1.0. Create local variables
        boolean correctOption = false;
        boolean isGlutenFree = false;
        int choice = 0;

        // 2.0. Loop asking user until we get a correct answer
        do {
            try {

                // I. Loop until we get an option between 0 and 1.
                do {
                    System.out.print("\nEnter your answer please: ");
                    choice = sc.nextInt();

                    if (choice < 0 || choice > 1) {
                        System.err.println("You must enter 0 or 1. "); // Output an error
                    }
                } while (choice < 0 || choice >1);

                // II. Stop loop
                correctOption = true;
            }
            catch(java.util.InputMismatchException e){
                System.err.print("Please use numbers.\n"); // Output an error
                sc.next(); // Call scanner again
            }

            // 2.1. Checks is it glutenFree or not
            if (choice == 1) isGlutenFree = true;
        } while (!correctOption);

        // 3.0. Return result
        return isGlutenFree;
    }

    //--------------------------------------------------
    //	runProgram
    //--------------------------------------------------

    /**
     * This is a main function that run all program. Customer parse 3 parameters to get a connection to the database and then
     * create an instance object of the CoffeeTruck.
     *
     * @param url -: a URL to the database
     * @param user -: a user who can manage database
     * @param password -: a password for this user
     */
    public static void runProgram(String url, String user, String password){
        // 1.0. Create a scanner sc
        Scanner sc = new Scanner(System.in);

        // 2.0. Create a sql connector and parse details of the DB
        MySQLCon sql = new MySQLCon(url, user, password);

        // 3.0. Create an object of our Coffee Truck
        CoffeeTruckImpl coffeeTruck = new CoffeeTruckImpl();

        // 4.0. Create a local variable
        boolean exit = false;

        // 5.0. Run a loop until
        while (!exit){

            // 5.1. Output the menu
            outputMenu();

            // 5.2. Create a local variable and asking user to enter
            int option = getOption(sc);

            // 5.3. "refresh" the menu
            for (int i = 0; i < 50; i++){
                System.out.println("\n");
            }

            // 5.4. Depends on the input show a needed menu
            switch (option) {
                case 0 -> exit = true;
                case 1 ->{

                    // I. Show the menu
                    System.out.println("---------------\n1. Add Customer\n---------------");
                    System.out.println("Enter customer details:\n");
                    sc.nextLine(); // Consume newline

                    // II. Ask user to enter fist name
                    System.out.print("First name: ");
                    String firstName = sc.nextLine();

                    // III. Ask user to enter last name
                    System.out.print("Last name: ");
                    String lastName = sc.nextLine();

                    // IV. Add customer
                    int customerId = coffeeTruck.addCustomer(firstName, lastName);

                    // V. Output the result
                    System.out.println("Customer added locally with ID: " + customerId);

                    // VI. Ask to enter anything to show the menu again
                    System.out.println("Enter anything to continue.");
                    sc.next();
                }
                case 2 -> {

                    // I. Show the menu
                    System.out.println("---------------\n2. Add Drink\n---------------");
                    System.out.println("Enter drink details:\n");

                    // II. Ask user to enter the name of the Drink
                    System.out.print("Name: ");
                    String name = sc.next();

                    // III. Ask user to enter the size of the drink
                    System.out.print("\nSize in ml (100 = 100ml, 500 = 500ml etc.): \n");
                    int size = getOption(sc);

                    // IV. Add drink
                    int drinkId = coffeeTruck.addDrink(name, size);

                    // V. Output the result
                    System.out.println("Drink added with ID: " + drinkId);

                    // VI. Ask to enter anything to show the menu again
                    System.out.println("Enter anything to continue.");
                    sc.next();
                }
                case 3 -> {

                    // I. Show the menu
                    System.out.println("---------------\n3. Add Dessert\n---------------");
                    System.out.println("Enter dessert details:\n");

                    // II. Ask user to enter a name of the dessert
                    System.out.print("Name: ");
                    String name = sc.next();

                    // III. Ask user is this dessert gluten-free or not
                    System.out.print("Is Gluten Free? (true = 1 /false = 0): ");
                    boolean isGlutenFree = getBooleanOption(sc);

                    // IV. Add dessert
                    int dessertId = coffeeTruck.addDessert(name, isGlutenFree);
                    System.out.println("Dessert added with ID: " + dessertId);

                    // V. Ask to enter anything to show the menu again
                    System.out.println("Enter anything to continue.");
                    sc.next();
                }
                case 4 -> {

                    // I. Show the menu
                    System.out.println("---------------\n4. Remove Customer\n---------------");

                    // II. Ask user to enter a customer ID
                    System.out.println("Enter customer ID to remove:");
                    int customerId = getOption(sc);

                    // III. Try to remove a customer
                    boolean removed = coffeeTruck.removeCustomer(customerId);

                    // IV. Output the result
                    if (removed) {
                        System.out.println("Customer removed successfully.");
                    } else {
                        System.out.println("Customer not found.");
                    }

                    // V. Ask to enter anything to show the menu again
                    System.out.println("Enter anything to continue.");
                    sc.next();
                }
                case 5 -> {

                    // I. Show the menu
                    System.out.println("---------------\n5. Remove Product\n---------------");

                    // II. Ask user to enter a product ID
                    System.out.println("Enter product ID to remove:");
                    int productId = getOption(sc);

                    // III. Try to remove a product
                    boolean removed = coffeeTruck.removeProduct(productId);

                    // IV. Output the result
                    if (removed) {
                        System.out.println("Product removed successfully.");
                    } else {
                        System.out.println("Product not found.");
                    }

                    // V. Ask to enter anything to show the menu again
                    System.out.println("Enter anything to continue.");
                    sc.next();
                }
                case 6 -> {

                    // I. Show the menu
                    System.out.println("---------------\n6. Display All Customers\n---------------");
                    System.out.println("List of all customers: ");

                    // II. Output all customers
                    coffeeTruck.displayCustomers();

                    // III. Ask to enter anything to show the menu again
                    System.out.println("Enter anything to continue.");
                    sc.next();
                }
                case 7 -> {

                    // I. Show the menu
                    System.out.println("---------------\n7. Display Customer Information\n---------------");

                    // II. Ask user to enter an ID of the customer
                    System.out.println("Enter customer ID to show details:");
                    int customerId = getOption(sc);

                    // III. Output information about customer
                    System.out.println("Information about customer: ");
                    boolean exist = coffeeTruck.displayCustomerInfo(customerId);

                    // IV. If customer doesn't exist in the system
                    if (!exist) System.err.println("Customer with this ID doesn't exist.");

                    // V. Ask to enter anything to show the menu again
                    System.out.println("Enter anything to continue.");
                    sc.next();
                }
                case 8 -> {

                    // I. Show the menu
                    System.out.println("---------------\n8. Make New Order\n---------------");

                    // II. Show available products to order
                    int res = coffeeTruck.displayProducts();

                    // III. If product list is not empty
                    if (res == 1) {
                        System.out.println("Enter customer ID to make an order:");
                        int customerId = getOption(sc);
                        System.out.println("Enter the ID of the product to add to the cart:");
                        int productId = getOption(sc);
                        int orderId = coffeeTruck.makeOrder(customerId, productId);
                        if (orderId != -1) {
                            System.out.println("Product added to the cart with order ID: " + orderId);
                        } else {
                            System.out.println("Failed to add the product to the cart.");
                        }
                    }

                    // IV. Ask to enter anything to show the menu again
                    System.out.println("Enter anything to continue.");
                    sc.next();
                }
                case 9 -> {
                    // I. Show the menu
                    System.out.println("---------------\n9. Display All Orders\n---------------");
                    System.out.println("Display all orders: ");

                    // II. Display all orders
                    coffeeTruck.displayOrders();

                    // III. Ask to enter anything to show the menu again
                    System.out.println("Enter anything to continue.");
                    sc.next();
                }
                case 10 -> {

                    // I. Load a customer list from the database
                        // i. Create a customer list and load it from the database
                    List<Customer> customerList = sql.loadCustomers();

                        // ii. Add a customer list to our program
                    coffeeTruck.addCustomers(customerList);

                    // II. Load a product list from the database
                        // i. Create a product list and load it from the database
                    List<Product> productList = sql.loadProducts();
                        // ii. Add a product list to our program
                    coffeeTruck.addProducts(productList);

                    // III. Load a activeCart from the database
                    coffeeTruck.loadActiveCart(sql, customerList);

                    // IV. Output the result
                    System.out.println("Database was successfully loaded.");

                    // V. Ask to enter anything to show the menu again
                    System.out.println("Enter anything to continue.");
                    sc.next();
                }
                case 11 -> {
                    // I. Show the menu
                    System.out.println("---------------\n8. Make New Order\n---------------");
                    coffeeTruck.saveCustomersToDB(sql);
                    coffeeTruck.saveProductsToDB(sql);
                    coffeeTruck.saveActiveCartToDB(sql);
                    System.out.println("DB saved");

                    // VI. Ask to enter anything to show the menu again
                    System.out.println("Enter anything to continue.");
                    sc.next();
                }
                default -> System.err.println("Sorry, enter between 0 and 10");
            }
        }
        // 6.0. Close the Scanner
        sc.close();
    }

//---------------------------------------
//	MAIN Program
//---------------------------------------

    public static void main(String[] args) {
        // 1. Create variables to connect to the MySQL Database
            //1.1. URL to the database, please change it before you run the program
        String DB_URL = "jdbc:mysql://localhost:3306/sys";
            // 1.2. Name of the User
        String USER = "root";
            // 1.3. Password
        String PASSWORD = "ghMLEUod23";

        // 2. Run the program and parse parameters of the Database
        runProgram(DB_URL, USER, PASSWORD);

    }


}