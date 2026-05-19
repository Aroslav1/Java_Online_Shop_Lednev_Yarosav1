import java.util.Date;
import java.util.List;
import java.util.Collections;

final class Receipt {
    private final String receiptId;
    private final Date purchaseDate;
    private final List<Order> orders;
    private final double totalDiscount;
    private final double totalAmount;

    public Receipt(String receiptId, Date purchaseDate, List<Order> orders, double totalDiscount) {
        this.receiptId = receiptId;
        this.purchaseDate = new Date(purchaseDate.getTime());
        this.orders = Collections.unmodifiableList(orders);
        this.totalDiscount = totalDiscount;
        this.totalAmount = calculateTotalAmount();
    }

    private double calculateTotalAmount() {
        return orders.stream()
                .mapToDouble(Order::getFinalPrice)
                .sum();
    }

    public String getReceiptId() { return receiptId; }
    public Date getPurchaseDate() { return new Date(purchaseDate.getTime()); }
    public List<Order> getOrders() { return orders; }
    public double getTotalDiscount() { return totalDiscount; }
    public double getTotalAmount() { return totalAmount; }

    public void displayReceipt() {
        System.out.println("\n|────────────────────────────────────────────────────────────|");
        System.out.println("|                     ЧЕК ПОКУПКИ                            |");
        System.out.println("|────────────────────────────────────────────────────────────|");
        System.out.printf("| Номер чека: %-42s     |\n", receiptId);
        System.out.printf("| Дата: %-47s      |\n", purchaseDate);
        System.out.println("|────────────────────────────────────────────────────────────|");

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.printf("| %d. %-46s \n", (i+1), order.getProduct().get_Title());
            System.out.printf("|    Цена: %-41.2f руб. \n", order.getOriginalPrice());
            System.out.printf("|    Скидка: %-39d %% \n", order.getClientStatus().getDiscount());
            System.out.printf("|    Итого: %-40.2f руб. \n", order.getFinalPrice());
            System.out.printf("|    %-44s \n", "(" + order.getClientStatus().getTitle() + ")");
            if (i < orders.size() - 1) {
                System.out.println("|────────────────────────────────────────────────────────────|");
            }
        }

        System.out.println("|────────────────────────────────────────────────────────────|");
        System.out.printf("| Общая скидка: %-37.2f руб.   |\n", totalDiscount);
        System.out.println("|────────────────────────────────────────────────────────────|");
        System.out.printf("|  ИТОГО К ОПЛАТЕ: %-33.2f руб.    |\n", totalAmount);
        System.out.println("|────────────────────────────────────────────────────────────|");
    }
}