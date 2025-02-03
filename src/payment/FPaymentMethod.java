package payment;

public class FPaymentMethod {

    private static IPaymentMethod getPaymentMethod(EPaymentMethod type) {
        switch (type) {
            case EPaymentMethod.PAYPAL:
                return new Paypal();
            case EPaymentMethod.CRYPTOCURRENCY:
                return new Cryptocurrency();
            case EPaymentMethod.CREDIT_CARD:
            default:
                return new CreditCard();
        }
    }

    public static IPaymentMethod createMeansOfPayment(EPaymentMethod type) {
        return getPaymentMethod(type);
    }
}
