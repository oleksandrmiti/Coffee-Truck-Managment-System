//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------

//--------------------------------------------------
//
//	CLASS Desserts
//
//--------------------------------------------------

/**
 * This class is a child of Product class. This class creates desserts.
 */
public class Desserts extends Product {
//---------------------------------------
//	Fields
//---------------------------------------
private boolean isGlutenFree;

//---------------------------------------
//	Constructor
//---------------------------------------
    public Desserts(int _id, String _name, boolean _isGlutenFree) {
        // 1.0. Parse id and name to the parents
        super(_id, _name);

        // 2.0. Initialise the field
        this.isGlutenFree = _isGlutenFree;
    }



//---------------------------------------
//	EXTRA METHODS
//---------------------------------------

    //---------------------------------------
    //	isGlutenFree
    //---------------------------------------

    /**
     * This method is returning is this dessert gluten-free or not
     * @return isGlutenFree
     */
    public boolean isGlutenFree() {
        return this.isGlutenFree;
    }
}