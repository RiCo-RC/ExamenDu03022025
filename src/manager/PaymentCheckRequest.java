package manager;

public class PaymentCheckRequest implements IOrderValidation {
    public IOrderValidation next = null;

    @Override
    public void setNext(IOrderValidation next) {
        this.next = next;
    }

    @Override
    public void handleOrder(ServiceOrder order) {
        boolean canContinue = true;
        if (order.getType() == ETypeRequest.PAYMENT_CHECK) {
            if (order.getFundOfCustomer() < order.getTotalPrice()) {
                System.out.println("La commande n°" + order.getOrderId() + " n'est pas valide. Motif : Manque de fonds !");
                canContinue = false;
            }
        }
        if (canContinue && this.next != null)  {
            order.setType(ETypeRequest.ORDER_DISPATCH);
            this.next.handleOrder(order);
        }
    }
}