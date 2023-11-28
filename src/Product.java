//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------

//--------------------------------------------------
//
//	CLASS Product
//
//--------------------------------------------------

/**
 * This class is a child class of Agent. This class is an abstract class and has a name of the products.
 */
public abstract class Product extends Agent {

//---------------------------------------
//	Fields
//---------------------------------------
private String name;

//---------------------------------------
//	Constructor
//---------------------------------------
    public Product(int _id, String _name){
        // 1.0. Parse id to the parent class
        super(_id);

        // 2.0. Initialise the field
        this.name = _name;
    }

//---------------------------------------
//	GET METHODS
//---------------------------------------

    //---------------------------------------
    //	getName
    //---------------------------------------

    /**
     * This method is returning a name of the product
     * @return -: name
     */
    public String getName(){
        return this.name;
    }

}