package model;

public class Category {

    private Integer catId;

    private String catName;

    public Category() {
    }

    public Category(Integer catId, String catName) {
        this.catId = catId;
        this.catName = catName;
    }
    
    public Category(Integer catId) {
        this.catId = catId;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Override
    public String toString() {
        return "model.Category[ catId=" + catId + " ]";
    }
    
}
