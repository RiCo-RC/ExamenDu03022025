package order;

import notify.Customer;
import product.Product;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private int id;
    private Map<Product, Integer> products;
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

    public Map<Product, Integer> getProducts() {
        return this.products;
    }

    public int getId() {
        return this.id;
    }

    public double getTotalPrice() {
        return this.totalPrice;
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
                ", par " + this.customer.getName() +
                ", " + numberProducts + "= " + this.products +
                ", prix total=" + this.totalPrice +
                ", statut='" + this.status + "'.";
    }

    public static class OrderBuilder implements IOrder {
        private static int idAutoIncrement = 1;
        private int id;
        private final Map<Product, Integer>  products = new HashMap<>();
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

        public OrderBuilder addProduct(Product product, int quantity) {
            if (quantity > 0 && quantity <= product.getQuantity()) {
                this.products.put(product, quantity);
                this.totalPrice += product.getPrice() * quantity;
            }
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