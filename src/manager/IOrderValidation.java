package manager;

public interface IOrderValidation {
    public void setNext(IOrderValidation next);
    public void handleOrder(ServiceOrder order);
}
