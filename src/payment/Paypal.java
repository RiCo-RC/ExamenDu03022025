package payment;

public class Paypal implements IPaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("--> Paiement de " + amount + " € effectué(e) par Paypal.");
    }
}
