package model;

public class Orders {

    private Integer orderId;

    private String orderDate;
    
    private int orderCreatedby;
    
    private String orderStatus;

    public Orders() {
    }

    public Orders(Integer orderId) {
        this.orderId = orderId;
    }

    public Orders(String orderDate, int orderCreatedby) {
        this.orderDate = orderDate;
        this.orderCreatedby = orderCreatedby;
    }

    public Orders(Integer orderId, String orderDate, int orderCreatedby, String orderStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderCreatedby = orderCreatedby;
        this.orderStatus = orderStatus;
    }
     
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderCreatedby() {
        return orderCreatedby;
    }

    public void setOrderCreatedby(int orderCreatedby) {
        this.orderCreatedby = orderCreatedby;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    

    @Override
    public String toString() {
        return "model.Orders[ orderId=" + orderId + " ]";
    }

}
