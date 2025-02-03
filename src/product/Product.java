package product;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private int id;
    private final String name;
    private final double price;
    private final String category;
    private final int quantity;

    public Product(ProductBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.category = builder.category;
        this.quantity = builder.quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Product display() {
        System.out.println(this);
        return this;
    }

    @Override
    public String toString() {
        return "--> Produit : " +
                "n°" + id +
                ", nom = '" + name + '\'' +
                ", prix = " + price +
                ", catégorie = '" + category + '\'' +
                ", quantité = " + quantity +
                ".";
    }

    public static class ProductBuilder implements IProduct {
        private static int idAutoIncrement = 1;
        private int id;
        private String name;
        private double price;
        private String category;
        private int quantity;

        public ProductBuilder(){
            this.id = idAutoIncrement++;
        }

        public ProductBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public ProductBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public ProductBuilder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public double getPrice() {
            return this.price;
        }

        public Product build() {
            return new Product(this);
        }
    }
}