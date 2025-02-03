package manager;

import notify.Customer;
import order.Order;
import product.Product;

import java.util.Map;

public class ServiceOrder {
    private ETypeRequest type;
    private Customer customer;
    private Order order;
    private String conclusion = "";

    public ServiceOrder(ETypeRequest type, Customer customer, Order order) {
        this.type = type;
        this.customer = customer;
        this.order = order;
    }

    public ETypeRequest getType() {
        return this.type;
    }

    public void setType(ETypeRequest type) {
        this.type = type;
    }

    public double getFundOfCustomer() {
        return this.customer.getFund();
    }

    public Map<Product, Integer> getProducts() {
        return this.order.getProducts();
    }

    public double getTotalPrice() {
        return this.order.getTotalPrice();
    }

    public int getOrderId() {
        return this.order.getId();
    }

    public String getConclusion() {
        return this.conclusion;
    }

    public void display() {
        System.out.println(this.conclusion);
    }
}