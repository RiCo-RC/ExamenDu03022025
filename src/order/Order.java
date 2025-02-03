package order;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private List<String> products;
    private double totalPrice;
    private String status;

    public Order(OrderBuilder builder) {
        this.id = builder.id;
        this.products = builder.products;
        this.totalPrice = builder.totalPrice;
        this.status = builder.status;
    }

    @Override
    public String toString() {
        return "order.Order{" +
                "id=" + id +
                ", products=" + products +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                '}';
    }

    public void display() {
        System.out.println(this.toString());
    }

    public static class OrderBuilder {
        private static int idAutoIncrement = 1;
        private int id;
        private List<String> products = new ArrayList<>();
        private double totalPrice;
        private String status = EOrderStatus.WAITING.toString();

        public OrderBuilder() {
            this.id = idAutoIncrement++;
        }

        public OrderBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public OrderBuilder addProduct(String product, double price) {
            this.products.add(product);
            this.totalPrice += price;
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

        public Order build() {
            return new Order(this);
        }
    }
}
