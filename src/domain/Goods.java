package domain;

public class Goods{
    int id;
    String name;
    String description;
    double price;
    String imgUrl;

    public Goods(int id,String name,String description,String imgUrl,double price){
        this.id=id;
        this.name=name;
        this.price=price;
        this.imgUrl=imgUrl;
        this.description=description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;

    }

    public double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
