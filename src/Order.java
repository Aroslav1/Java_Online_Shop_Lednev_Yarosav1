import java.util.UUID;

class Order implements Payble {
    private final String orderId;
    private final Product product;
    private final ClientStatus clientStatus;
    private final double originalPrice;
    private double finalPrice;
    private boolean isPaid;
    private OrderStatus status;
    private final DiscountCalculator discountCalculator;

    public Order(Product product, ClientStatus clientStatus, DiscountCalculator discountCalculator) {
        this.orderId = UUID.randomUUID().toString().substring(0, 8);
        this.product = product;
        this.clientStatus = clientStatus;
        this.originalPrice = product.get_Price();
        this.discountCalculator = discountCalculator;
        this.isPaid = false;
        this.status = OrderStatus.PENDING;
        calculateFinalPrice();
    }

    private void calculateFinalPrice() {
        double discount = discountCalculator.calculateDiscount(clientStatus, originalPrice);
        this.finalPrice = originalPrice - discount;
    }

    public void displayOrderInfo() {
        System.out.println("Заказ #" + orderId);
        System.out.println("  Товар: " + product.get_Title());
        System.out.println("  Клиент: " + clientStatus.getTitle());
        System.out.println("  Исходная цена: " + originalPrice + " руб.");
        System.out.println("  Скидка: " + clientStatus.getDiscount() + "%");
        System.out.println("  Итоговая цена: " + finalPrice + " руб.");
        System.out.println("  Статус: " + status.getDescription());
    }

    public void purchase() {
        this.isPaid = true;
        this.status = OrderStatus.PAID;
    }

    public String getOrderId() { return orderId; }
    public Product getProduct() { return product; }
    public ClientStatus getClientStatus() { return clientStatus; }
    public double getFinalPrice() { return finalPrice; }
    public double getOriginalPrice() { return originalPrice; }
    public OrderStatus getStatus() { return status; }

    @Override
    public void pay(double amount) {
        if (amount >= finalPrice) {
            this.isPaid = true;
            this.status = OrderStatus.PAID;
        }
    }

    @Override
    public boolean isPaid() { return isPaid; }
}