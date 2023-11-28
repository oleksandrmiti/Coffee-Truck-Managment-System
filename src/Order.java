//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------

//--------------------------------------------------
//
//	CLASS Order
//
//--------------------------------------------------

/**
 * This class is make and contain an information about the order
 */
public class Order {


//---------------------------------------
//	Fields
//---------------------------------------
    private final int orderId;
    private final int customerId;
    private final int productId;

//---------------------------------------
//	Constructor
//---------------------------------------
    public Order(int _orderId, int _customerId, int _productId){

        // 1.0. Initialise the fields
        this.orderId = _orderId;
        this.customerId = _customerId;
        this.productId = _productId;
    }

//---------------------------------------
//	GET METHODS
//---------------------------------------

    //---------------------------------------
    //	getOrderId
    //---------------------------------------

    /**
     * This method is returning an order ID
     * @return orderId
     */
    public int getOrderId(){
        return this.orderId;
    }

    //---------------------------------------
    //	getCustomerId
    //---------------------------------------

    /**
     *  This method is returning a customer ID
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    //---------------------------------------
    //	getProductId
    //---------------------------------------

    /**
     * This method is returning a product ID
     * @return productId
     */
    public int getProductId() {
        return productId;
    }



//---------------------------------------
//	EXTRA METHODS
//---------------------------------------

    //---------------------------------------
    //	toString
    //---------------------------------------

    /**
     * This method is override of toString method
     * @return an information about order
     */
    @Override
    public String toString() {
        return "Order Id: "+ this.orderId +"{" +
                "Customer Id:" + this.customerId +
                "Product Id:" + this.productId +
                '}';
    }
}