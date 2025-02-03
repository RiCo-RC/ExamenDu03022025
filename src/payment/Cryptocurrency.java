package payment;

public class Cryptocurrency implements IPaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("--> Paiement de " + amount + " € effectué(e) par Cryptomonnaie.");
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "--> Méthode de payement : Cryptocurrency";
    }
}
