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
                .addProduct("Une télé", 1200)
                .setStatus(EOrderStatus.DELIVERED)
                .build();

        order_1.display();
        order_2.display();

        // -- PAYMENT -- \\

        IPaymentMethod payment_1 = FPaymentMethod.createMeansOfPayment(EPaymentMethod.PAYPAL);
        IPaymentMethod payment_2 = FPaymentMethod.createMeansOfPayment(EPaymentMethod.CREDIT_CARD);

        payment_1.pay(200);
        payment_2.pay(200);
    }
}