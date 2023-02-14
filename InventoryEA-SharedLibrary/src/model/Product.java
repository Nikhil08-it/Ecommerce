package model;


public class Product {
    
    String prodId;
    String prodName;
    String prodQty;
    String prodPrice;
    String prodUnit;
    String prodDateAdded;
    String prodAdded;
    String prodCategory;

    public Product() {
    }

    public Product(String prodId, String prodName, String prodQty, String prodPrice, String prodUnit, String prodDateAdded, String prodAdded, String prodCategory) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodQty = prodQty;
        this.prodPrice = prodPrice;
        this.prodUnit = prodUnit;
        this.prodDateAdded = prodDateAdded;
        this.prodAdded = prodAdded;
        this.prodCategory = prodCategory;
    }

    public Product(String prodName, String prodQty, String prodPrice, String prodUnit, String prodDateAdded, String prodAdded, String prodCategory) {
        this.prodName = prodName;
        this.prodQty = prodQty;
        this.prodPrice = prodPrice;
        this.prodUnit = prodUnit;
        this.prodDateAdded = prodDateAdded;
        this.prodAdded = prodAdded;
        this.prodCategory = prodCategory;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdQty() {
        return prodQty;
    }

    public void setProdQty(String prodQty) {
        this.prodQty = prodQty;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getProdUnit() {
        return prodUnit;
    }

    public void setProdUnit(String prodUnit) {
        this.prodUnit = prodUnit;
    }

    public String getProdDateAdded() {
        return prodDateAdded;
    }

    public void setProdDateAdded(String prodDateAdded) {
        this.prodDateAdded = prodDateAdded;
    }

    public String getProdAdded() {
        return prodAdded;
    }

    public void setProdAdded(String prodAdded) {
        this.prodAdded = prodAdded;
    }

    public String getProdCategory() {
        return prodCategory;
    }

    public void setProdCategory(String prodCategory) {
        this.prodCategory = prodCategory;
    }

    @Override
    public String toString() {
        return "Product{" + "prodId=" + prodId + ", prodName=" + prodName + ", prodQty=" + prodQty + ", prodPrice=" + prodPrice + ", prodUnit=" + prodUnit + ", prodDateAdded=" + prodDateAdded + ", prodAdded=" + prodAdded + ", prodCategory=" + prodCategory + '}';
    }

}
