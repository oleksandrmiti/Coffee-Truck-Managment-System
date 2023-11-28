//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------


import java.util.ArrayList;
import java.util.List;

//--------------------------------------------------
//
//	CLASS Customer
//
//--------------------------------------------------
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

    super(_id);

    this.firstName = _firstName;
    this.lastName = _lastName;

    this.activeCart = new ArrayList<Order>();
}


//---------------------------------------
//	GET METHODS
//---------------------------------------

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void addToCart(Order order){
        activeCart.add(order);
    }


    public ArrayList<Order> getActiveCart(){
        return this.activeCart;
    }

    //---------------------------------------
//	SET METHODS
//---------------------------------------

    public void setActiveCart(ArrayList<Order> _activeCart){
        this.activeCart = new ArrayList<>(_activeCart);
    }

//---------------------------------------
//	EXTRA METHODS
//---------------------------------------
    public String toString() {
        return "User{" +
                "id=" + this.getID() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                '}';
    }

    public void addActiveCart(ArrayList<Order> _activeCart){
    this.activeCart.addAll(_activeCart);
    }
}