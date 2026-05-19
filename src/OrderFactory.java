import java.util.ArrayList;
import java.util.List;

class OrderFactory {
    private static final List<Order> orders = new ArrayList<>();

    public Order createOrder(Product product, ClientStatus clientStatus, DiscountCalculator discountCalculator) {
        Order order = new Order(product, clientStatus, discountCalculator);
        orders.add(order);
        System.out.println(" Создан заказ для товара: " + product.get_Title());
        return order;
    }

    public static void displayAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("Заказы не созданы");
            return;
        }
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("[" + i + "]");
            orders.get(i).displayOrderInfo();
            System.out.println();
        }
    }
}