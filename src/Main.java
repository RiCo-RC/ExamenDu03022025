import log.STransactionLogger;
import log.TransactionLogger;
import manager.*;
import notify.Customer;
import notify.OrderObserver;
import order.EOrderStatus;
import order.Order;
import payment.EPaymentMethod;
import payment.FPaymentMethod;
import payment.IPaymentMethod;
import product.Product;

public class Main {
    public static void main(String[] args) {

        // -- INIT -- \\

        // --> Liste des produits
        Product macbookAirM2 = new Product.ProductBuilder()
                .setName("Apple MackBook Air M2")
                .setPrice(1299)
                .setCategory("Appareil")
                .setQuantity(200)
                .build();
                //.display();
        Product ps5 = new Product.ProductBuilder()
                .setName("PlayStation 5")
                .setPrice(499.99)
                .setCategory("Console")
                .setQuantity(200)
                .build();
        Product xboxSeriesX = new Product.ProductBuilder()
                .setName("Xbox Series X")
                .setPrice(499.99)
                .setCategory("Console")
                .setQuantity(200)
                .build();
        Product samsungGalaxyS23 = new Product.ProductBuilder()
                .setName("Samsung Galaxy S23")
                .setPrice(959.99)
                .setCategory("Appareil")
                .setQuantity(200)
                .build();
        Product airPodsPro2 = new Product.ProductBuilder()
                .setName("AirPods Pro 2")
                .setPrice(299)
                .setCategory("Appareil")
                .setQuantity(200)
                .build();

        Product createSite = new Product.ProductBuilder()
                .setName("Creation de site")
                .setPrice(1000)
                .setCategory("Service")
                .setQuantity(100)
                .build();
        Product hostingCloud = new Product.ProductBuilder()
                .setName("Hébergement cloud")
                .setPrice(10)
                .setCategory("Service")
                .setQuantity(10)
                .build();
        Product upkeepAndupdate = new Product.ProductBuilder()
                .setName("Maintenance et mise à jour de logiciels")
                .setPrice(100)
                .setCategory("Service")
                .setQuantity(10)
                .build();
        Product createApp = new Product.ProductBuilder()
                .setName("Creation d'app mobile")
                .setPrice(5000)
                .setCategory("Service")
                .setQuantity(10)
                .build();
        Product cybersecurity = new Product.ProductBuilder()
                .setName("Consultation en cybersécurité")
                .setPrice(500)
                .setCategory("Service")
                .setQuantity(10)
                .build();

        // --> Liste des payements
        IPaymentMethod paypal = FPaymentMethod.createMeansOfPayment(EPaymentMethod.PAYPAL);
        IPaymentMethod creditCards = FPaymentMethod.createMeansOfPayment(EPaymentMethod.CREDIT_CARD);
        IPaymentMethod cryptocurrency = FPaymentMethod.createMeansOfPayment(EPaymentMethod.CRYPTOCURRENCY);

        // --> Liste des clients
        Customer rico = new Customer("RiCo", 4000);
        Customer lip = new Customer("Lip", 10);

        // --> Liste des commandes
        Order rico_order = new Order.OrderBuilder()
                .setCustomer(rico)
                .addProduct(ps5, 1)
                .addProduct(xboxSeriesX, 1)
                .addProduct(hostingCloud, 1)
                .setStatus(EOrderStatus.WAITING)
                .build();
        Order lip_order = new Order.OrderBuilder()
                .setCustomer(lip)
                .addProduct(samsungGalaxyS23, 1)
                .addProduct(airPodsPro2, 1)
                .setStatus(EOrderStatus.WAITING)
                .build();

        // --> Pour la notification
        OrderObserver general_observer = new OrderObserver();
        OrderObserver rico_observer = new OrderObserver();
        OrderObserver lip_observer = new OrderObserver();

        general_observer.addObserver(rico);
        general_observer.addObserver(lip);

        rico_observer.addObserver(rico);

        lip_observer.addObserver(lip);

        // --> Pour gérer la responsabilité
        IOrderValidation stockCheck = new StockCheckRequest();
        IOrderValidation paymentCheck = new PaymentCheckRequest();
        IOrderValidation orderDispatch = new OrderDispatchRequest();

        stockCheck.setNext(paymentCheck);
        paymentCheck.setNext(orderDispatch);

        // --> Pour le logger
        TransactionLogger logger = STransactionLogger.getInstance();
        TransactionLogger anotherLogger = STransactionLogger.getInstance();

        // -- DISPLAY -- \\

        System.out.print("\n");
        System.out.println("#---------- PRODUITS ----------#");
        System.out.print("\n");

        macbookAirM2.display();
        ps5.display();
        xboxSeriesX.display();
        samsungGalaxyS23.display();
        airPodsPro2.display();

        System.out.print("\n");

        createSite.display();
        hostingCloud.display();
        upkeepAndupdate.display();
        createApp.display();
        cybersecurity.display();

        System.out.print("\n");
        System.out.println("#---------- METHODES DE PAYEMENTS ----------#");
        System.out.print("\n");

        creditCards.display();
        paypal.display();
        cryptocurrency.display();

        System.out.print("\n");

        creditCards.pay(200);

        System.out.print("\n");
        System.out.println("#---------- CLIENTS ----------#");
        System.out.print("\n");

        rico.display();
        lip.display();

        System.out.print("\n");
        System.out.println("#---------- COMMANDES ----------#");
        System.out.print("\n");

        rico_order.display();
        lip_order.display();

        //System.out.println(order_rico.getProducts());

        System.out.print("\n");
        System.out.println("#---------- NOTIFICATIONS ----------#");
        System.out.print("\n");

        general_observer.notify("Votre commande a bien été reçu !");
        rico_observer.notify("Votre commande est en cours de préparation !");
        lip_observer.notify("Votre commande est en attende de produits disponible !");

        System.out.print("\n");
        System.out.println("#---------- RESPONSABILITES ----------#");
        System.out.print("\n");

        ServiceOrder rico_requestStock = new ServiceOrder(ETypeRequest.STOCK_CHECK, rico, rico_order);
        //ServiceOrder rico_requestPayment = new ServiceOrder(ETypeRequest.PAYMENT_CHECK, rico, rico_order);
        //ServiceOrder rico_requestDispatch = new ServiceOrder(ETypeRequest.ORDER_DISPATCH, rico, rico_order);
        stockCheck.handleOrder(rico_requestStock);
        //paymentCheck.handleOrder(rico_requestPayment);
        //orderDispatch.handleOrder(rico_requestDispatch);
        rico_requestStock.display();

        ServiceOrder lip_requestStock = new ServiceOrder(ETypeRequest.STOCK_CHECK, lip, lip_order);
        //ServiceOrder lip_requestPayment = new ServiceOrder(ETypeRequest.PAYMENT_CHECK, lip, lip_order);
        //ServiceOrder lip_requestDispatch = new ServiceOrder(ETypeRequest.ORDER_DISPATCH, lip, lip_order);
        stockCheck.handleOrder(lip_requestStock);
        //paymentCheck.handleOrder(lip_requestPayment);
        //orderDispatch.handleOrder(lip_requestDispatch);
        lip_requestStock.display();

        System.out.print("\n");
        System.out.println("#---------- LOG ----------#");
        System.out.print("\n");

        logger.log("Paiement effectué.");
        anotherLogger.log("Annulation de la commande.");
    }
}