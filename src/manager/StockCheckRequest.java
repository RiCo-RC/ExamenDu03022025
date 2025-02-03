package manager;

import product.Product;

import java.util.Map;

public class StockCheckRequest implements IOrderValidation {
    public IOrderValidation next = null;

    @Override
    public void setNext(IOrderValidation next) {
        this.next = next;
    }

    @Override
    public void handleOrder(ServiceOrder order) {
        boolean canContinue = true;
        if (order.getType() == ETypeRequest.STOCK_CHECK) {
            for (Map.Entry<Product, Integer> cmd : order.getProducts().entrySet()) {
                Product produit = cmd.getKey();
                int orderQuantity = cmd.getValue();
                if (produit.getQuantity() < orderQuantity) {
                    System.out.println("La commande n'est pas valide ! Le produit " + produit + " est en rupture de stock !");
                    canContinue = false;
                }
            }
        }
        if (canContinue && this.next != null)  {
            order.setType(ETypeRequest.PAYMENT_CHECK);
            this.next.handleOrder(order);
        }
    }
}