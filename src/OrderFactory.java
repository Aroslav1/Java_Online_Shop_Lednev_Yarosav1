import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

class OrderFactory {
    private static final List<Order> orders = new ArrayList<>();

    public Order createOrder(Product product, ClientStatus clientStatus, DiscountCalculator discountCalculator) {
        Order order = new Order(product, clientStatus, discountCalculator);
        orders.add(order);
        System.out.println(" Создан заказ для товара: " + product.get_Title());
        return order;
    }

    public Receipt purchaseOrder(int orderIndex, ClientStatus clientStatus, DiscountCalculator discountCalculator) {
        if (orderIndex < 0 || orderIndex >= orders.size()) {
            System.out.println(" Заказ не найден!");
            return null;
        }

        Order order = orders.get(orderIndex);

        if (order.isPaid()) {
            System.out.println(" Заказ уже оплачен!");
            return null;
        }

        order.purchase();
        System.out.println(" Товар \"" + order.getProduct().get_Title() + "\" успешно куплен!");

        List<Order> purchasedOrders = new ArrayList<>();
        purchasedOrders.add(order);

        double totalDiscount = discountCalculator.calculateDiscount(clientStatus, order.getOriginalPrice());

        return new Receipt(
                UUID.randomUUID().toString(),
                new Date(),
                purchasedOrders,
                totalDiscount,
                clientStatus
        );
    }

    public Receipt purchaseMultipleOrders(List<Integer> orderIndices, ClientStatus clientStatus, DiscountCalculator discountCalculator) {
        List<Order> purchasedOrders = new ArrayList<>();
        double totalDiscount = 0;

        for (int index : orderIndices) {
            if (index >= 0 && index < orders.size()) {
                Order order = orders.get(index);
                if (!order.isPaid()) {
                    order.purchase();
                    purchasedOrders.add(order);
                    totalDiscount += discountCalculator.calculateDiscount(clientStatus, order.getOriginalPrice());
                    System.out.println(" Товар \"" + order.getProduct().get_Title() + "\" успешно куплен!");
                }
            }
        }

        if (purchasedOrders.isEmpty()) {
            System.out.println(" Не выбрано ни одного товара для покупки!");
            return null;
        }

        return new Receipt(
                UUID.randomUUID().toString(),
                new Date(),
                purchasedOrders,
                totalDiscount,
                clientStatus
        );
    }

    public static void displayAllOrders() {
        if (orders.isEmpty()) {
            System.out.println(" Заказы не созданы");
            return;
        }
        System.out.println("\n СПИСОК ЗАКАЗОВ:");
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("\n[" + i + "]");
            orders.get(i).displayOrderInfo();
            System.out.println("  Оплачен: " + (orders.get(i).isPaid() ? " Да" : " Нет"));
        }
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static Order getOrder(int index) {
        if (index >= 0 && index < orders.size()) {
            return orders.get(index);
        }
        return null;
    }

    public static void clearOrders() {
        orders.clear();
    }
}