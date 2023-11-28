//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------

//--------------------------------------------------
//
//	CLASS Drinks
//
//--------------------------------------------------

/**
 * This class is a child of the Product. It creates drinks.
 */
public class Drinks extends Product {

//---------------------------------------
//	Fields
//---------------------------------------
private int size;

//---------------------------------------
//	Constructor
//---------------------------------------
    public Drinks(int _id, String _name, int _size) {
        // 1.0. Parse id and name to the parents
        super(_id, _name);

        // 2.0. Initialise the field.
        this.size = _size;
    }

//---------------------------------------
//	GET METHODS
//---------------------------------------

    //---------------------------------------
    //	getSize
    //---------------------------------------

    /**
     * This method is returning a size of the drink in ml.
     * @return size
     */
    public int getSize(){
        return this.size;
    }

}