package manager;

public class OrderDispatchRequest implements IOrderValidation {
    public IOrderValidation next = null;

    @Override
    public void setNext(IOrderValidation next) {
        this.next = next;
    }

    @Override
    public void handleOrder(ServiceOrder order) {
        boolean canContinue = true;
        if (order.getType() == ETypeRequest.ORDER_DISPATCH) {
            System.out.println("La commande n°" + order.getOrderId() + " a était validé ! Tout est bon ! Préparation de la commande");
        }
        if (canContinue && this.next != null) this.next.handleOrder(order);
    }
}