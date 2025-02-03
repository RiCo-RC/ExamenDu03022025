package notify;

import order.Order;
import payment.IPaymentMethod;

import java.util.List;

public class Customer implements INotify {

    private static int idAutoIncrement = 1;
    private int id;
    private String name;
    private double fund;
    private List<IPaymentMethod> paymentMethods;
    private List<Order> orders;

    public Customer(String name, double fund) {
        this.id = idAutoIncrement++;
        this.name = name;
        this.fund = fund;
    }

    public String getName() {
        return this.name;
    }

    public double getFund() {
        return this.fund;
    }

//    public Customer addPaymentMethod(IPaymentMethod paymentMethod) {
//        this.paymentMethods.add(paymentMethod);
//        return this;
//    }

    public Customer display() {
        System.out.println(this);
        return this;
    }

    @Override
    public void update(String message) {
        System.out.println("--> " + this.name +
                " a reçu une nouvelle notification :\n" +
                ">>> " + message);
    }

    @Override
    public String toString() {
        return "--> Client : " +
                "n°" + this.id +
                ", nom = '" + this.name + "'" +
                ", argent = " + this.fund + "€.";
    }
}
