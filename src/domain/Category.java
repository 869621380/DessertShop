package domain;

import java.io.Serializable;

public class Category  implements Serializable {
    private static final long serialVersionUID = 3992469837058393712L;

    public String categoryId;
    public String name;
    public String desription;
    public int price;
    public String imgURL;

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String description) {
        this.desription = description;
    }

    public String toString() {
        return getCategoryId();
    }
}
