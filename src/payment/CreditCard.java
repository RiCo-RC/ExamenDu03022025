package payment;

public class CreditCard implements IPaymentMethod {

    @Override
    public void pay(double amount) {
        System.out.println("--> Paiement de " + amount + " € effectué(e) par Carte Bancaire.");
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "--> Méthode de payement : Carte bancaire";
    }
}
