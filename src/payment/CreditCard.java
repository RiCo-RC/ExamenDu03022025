package payment;

public class CreditCard implements IPaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("--> Paiement de " + amount + " € effectué(e) par Carte Bancaire.");
    }
}
