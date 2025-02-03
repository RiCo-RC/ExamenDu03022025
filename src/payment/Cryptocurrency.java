package payment;

public class Cryptocurrency implements IPaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("--> Paiement de " + amount + " € effectué(e) par Cryptomonnaie.");
    }
}
