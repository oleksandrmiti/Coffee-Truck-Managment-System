//--------------------------------------------------
//	IMPORTS
//--------------------------------------------------


//--------------------------------------------------
//
//	CLASS Order
//
//--------------------------------------------------
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
        this.orderId = _orderId;
        this.customerId = _customerId;
        this.productId = _productId;
    }

//---------------------------------------
//	GET METHODS
//---------------------------------------

    public int getOrderId(){
        return this.orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    //---------------------------------------
//	SET METHODS
//---------------------------------------


//---------------------------------------
//	EXTRA METHODS
//---------------------------------------

    @Override
    public String toString() {
        return "Order Id: "+ this.orderId +"{" +
                "Customer Id:" + this.customerId +
                "Product Id:" + this.productId +
                '}';
    }
}