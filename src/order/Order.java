package order;

import notify.Customer;
import product.Product;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private List<Product> products;
    private double totalPrice;
    private String status;
    private Customer customer;

    public Order(OrderBuilder builder) {
        this.id = builder.id;
        this.products = builder.products;
        this.totalPrice = builder.totalPrice;
        this.status = builder.status;
        this.customer = builder.customer;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public Order display() {
        System.out.println(this.toString());
        return this;
    }

    @Override
    public String toString() {
        String numberProducts = (this.products.size() == 1) ? "produit" : "produits";
        return "--> Commande : " +
                "n°" + this.id +
                ", par " + this.customer.getName() +                ", " + numberProducts + "= " + this.products +
                ", prix total=" + this.totalPrice +
                ", statut='" + this.status + "'.";
    }

    public static class OrderBuilder implements IOrder {
        private static int idAutoIncrement = 1;
        private int id;
        private final List<Product> products = new ArrayList<>();
        private double totalPrice;
        private String status = EOrderStatus.WAITING.toString();
        private Customer customer;

        public OrderBuilder() {
            this.id = idAutoIncrement++;
        }

        public OrderBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public OrderBuilder addProduct(Product product) {
            this.products.add(product);
            this.totalPrice += product.getPrice();
            return this;
        }

        public String getStatusToString(EOrderStatus status) {
            switch (status) {
                case EOrderStatus.WAITING:
                    return "En attende";
                case EOrderStatus.IN_PROGRESS:
                    return "En cours";
                case EOrderStatus.DELIVERED:
                    return "Livrée";
                case EOrderStatus.CANCELLED:
                    return "Annulée";
                default:
                    return "???";

            }
        }

        public OrderBuilder setStatus(EOrderStatus status) {
            this.status = this.getStatusToString(status);
            return this;
        }

        public OrderBuilder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}