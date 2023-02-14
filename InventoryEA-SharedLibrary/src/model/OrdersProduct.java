package model;

public class OrdersProduct {

    private Integer orderprodId;

    private Integer orderQty;

    private int orderId;

    private int prodId;

    public OrdersProduct() {
    }

    public OrdersProduct(Integer orderprodId, Integer orderQty, int orderId, int prodId) {
        this.orderprodId = orderprodId;
        this.orderQty = orderQty;
        this.orderId = orderId;
        this.prodId = prodId;
    }
    
    
    public OrdersProduct(Integer orderprodId) {
        this.orderprodId = orderprodId;
    }

    public Integer getOrderprodId() {
        return orderprodId;
    }

    public void setOrderprodId(Integer orderprodId) {
        this.orderprodId = orderprodId;
    }

    public Integer getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    @Override
    public String toString() {
        return "model.OrdersProduct[ orderprodId=" + orderprodId + " ]";
    }
    
}
