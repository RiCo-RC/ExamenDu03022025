import notify.Customer;
import notify.OrderObserver;
import order.EOrderStatus;
import order.Order;
import payment.EPaymentMethod;
import payment.FPaymentMethod;
import payment.IPaymentMethod;

public class Main {
    public static void main(String[] args) {

        // -- ORDER -- \\

        Order order_1 = new Order.OrderBuilder()
                .addProduct("un site internet", 20000)
                .setStatus(EOrderStatus.IN_PROGRESS)
                .build();

        Order order_2 = new Order.OrderBuilder()
                .addProduct("un serveur", 10000)
                .addProduct("un ordinateur", 2500)
                .addProduct("une télé", 1200)
                .setStatus(EOrderStatus.DELIVERED)
                .build();

        order_1.display();
        order_2.display();

        // -- PAYMENT -- \\

        IPaymentMethod payment_1 = FPaymentMethod.createMeansOfPayment(EPaymentMethod.PAYPAL);
        IPaymentMethod payment_2 = FPaymentMethod.createMeansOfPayment(EPaymentMethod.CREDIT_CARD);

        payment_1.pay(200);
        payment_2.pay(200);

        // -- NOTIFY -- \\

        OrderObserver order_observer = new OrderObserver();
        Customer client_1 = new Customer("Client 1");
        Customer client_2 = new Customer("Client 2");

        order_observer.addObserver(client_1);
        order_observer.addObserver(client_2);

        order_observer.notify("Votre commande a bien été reçu !");
        order_observer.notify("Votre commande est en cours de préparation !");
    }
}