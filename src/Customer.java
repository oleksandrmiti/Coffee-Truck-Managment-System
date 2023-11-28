//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------
import java.util.ArrayList;

//--------------------------------------------------
//
//	CLASS Customer
//
//--------------------------------------------------

/**
 * This is a child class of the Agent for Customer.
 */
public class Customer extends Agent {


//---------------------------------------
//	Fields
//---------------------------------------
    private ArrayList<Order> activeCart;
    private String firstName;
    private String lastName;

//---------------------------------------
//	Constructor
//---------------------------------------
public Customer(int _id, String _firstName, String _lastName) {
    // 1.0. Parse ID to the parent class
    super(_id);

    // 2.0. Initialise fields
    this.firstName = _firstName;
    this.lastName = _lastName;

    // 3.0 Assume that customer has no orders yet
    this.activeCart = new ArrayList<Order>();
}


//---------------------------------------
//	GET METHODS
//---------------------------------------

    //---------------------------------------
    //	getFirstName
    //---------------------------------------

    /**
     * This method is return a first name of the customer
     * @return firstName
     */
    public String getFirstName() {
        return this.firstName;
    }

    //---------------------------------------
    //	getLastName
    //---------------------------------------

    /**
     * This method is return a last name of the customer
     * @return lastName
     */
    public String getLastName() {
        return this.lastName;
    }

    //---------------------------------------
    //	getActiveCart
    //---------------------------------------

    /**
     * This method is returning an active cart
     * @return activeCart
     */
    public ArrayList<Order> getActiveCart(){
        return this.activeCart;
    }

//---------------------------------------
//	SET METHODS
//---------------------------------------

    //---------------------------------------
    //	setActiveCart
    //---------------------------------------

    /**
     * Setting active cart for the customer
     * @param _activeCart -: active cart
     */
    public void setActiveCart(ArrayList<Order> _activeCart){
        this.activeCart = new ArrayList<>(_activeCart);
    }

//---------------------------------------
//	EXTRA METHODS
//---------------------------------------

    //---------------------------------------
    //	toString
    //---------------------------------------

    /**
     * This method is override of the method toString
     * @return -: an information about customer
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + this.getID() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", active cart='" + getActiveCart() + '\'' +
                '}';
    }

    //---------------------------------------
    //	addToCart
    //---------------------------------------

    /**
     * This method is adding an order to the cart
     * @param order -: an order with products inside
     */
    public void addToCart(Order order){
        activeCart.add(order);
    }
}