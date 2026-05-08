import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Product extends Category implements Payble {
    public static ArrayList<Product> productList = new ArrayList<>();
    private boolean isPaid = false;
    private OrderStatus orderStatus = OrderStatus.PENDING;

    public Product(String title, Double price, String description) {
        super(title, price, description);
        productList.add(this);
    }

    public static Comparator<Product> PriceComparator = (p1, p2) ->
            Double.compare(p1.get_Price(), p2.get_Price());

    public static void checkClientStatus(ClientStatus status, ClientStatusChecker checker, String clientName) {
        if (checker.checkStatus(status)) {
            System.out.println(clientName + " является клиентом со статусом: " + status.getTitle()
                    + " (скидка: " + status.getDiscount() + "%)");
        } else {
            System.out.println(clientName + " не подходит под указанный критерий");
        }
    }

    public static void safeCompare(Product p1, Product p2) {
        System.out.println("\n   Результат сравнения   ");

        if (!p1.getClass().equals(p2.getClass())) {
            System.out.println("ОШИБКА: Нельзя сравнивать [" + p1.getClass().getSimpleName() +
                    "] и [" + p2.getClass().getSimpleName() + "]!");
            return;
        }

        System.out.println("Сравниваем товары категории: " + p1.getClass().getSimpleName());

        if (p1.get_Price() > p2.get_Price()) {
            System.out.println("ЛУЧШИЙ ВЫБОР (Премиум): " + p1.get_Title() + " (" + p1.get_Price() + " руб.)");
        } else if (p2.get_Price() > p1.get_Price()) {
            System.out.println("ЛУЧШИЙ ВЫБОР (Премиум): " + p2.get_Title() + " (" + p2.get_Price() + " руб.)");
        } else {
            System.out.println("Цена одинаковая. Сравниваем информативность...");
            if (p1.get_Description().length() >= p2.get_Description().length()) {
                System.out.println("ЛУЧШИЙ ВЫБОР (Больше инфо): " + p1.get_Title());
            } else {
                System.out.println("ЛУЧШИЙ ВЫБОР (Больше инфо): " + p2.get_Title());
            }
        }
    }

    public static Product getProductByIndex(int index) {
        if (index >= 0 && index < productList.size()) {
            return productList.get(index);
        }
        return null;
    }

    public static void showProductsByPriceRange(double min, double max) {
        System.out.println("\nТовары в ценовом диапазоне " + min + " - " + max + " руб.:");
        productList.stream()
                .filter(p -> p.get_Price() >= min && p.get_Price() <= max)
                .forEach(p -> System.out.println("  - " + p.get_Title() + " (" + p.get_Price() + " руб.)"));
    }

    public static void showProductsByType(Class<?> type) {
        System.out.println("\nТовары типа " + type.getSimpleName() + ":");
        productList.stream()
                .filter(p -> p.getClass().equals(type))
                .forEach(p -> System.out.println("  - " + p.get_Title() + " (" + p.get_Price() + " руб.)"));
    }

    public static void showStatistics() {
        System.out.println("\nСтатистика по товарам:");
        System.out.println("Всего товаров: " + productList.size());
        System.out.println("Средняя цена: " + productList.stream()
                .mapToDouble(Product::get_Price)
                .average()
                .orElse(0));
        System.out.println("Максимальная цена: " + productList.stream()
                .mapToDouble(Product::get_Price)
                .max()
                .orElse(0));
        System.out.println("Минимальная цена: " + productList.stream()
                .mapToDouble(Product::get_Price)
                .min()
                .orElse(0));
        System.out.println("Общая стоимость: " + productList.stream()
                .mapToDouble(Product::get_Price)
                .sum());

        System.out.println("\nГруппировка по типам:");
        productList.stream()
                .collect(Collectors.groupingBy(p -> p.getClass().getSimpleName(), Collectors.counting()))
                .forEach((type, count) -> System.out.println("  " + type + ": " + count + " шт."));
    }

    public void updateOrderStatus(OrderStatus newStatus) {
        this.orderStatus = newStatus;
        System.out.println("Статус заказа для " + get_Title() + " обновлен на: " + newStatus.getDescription());
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override
    public double getFinalPrice() {
        return get_Price();
    }

    @Override
    public void pay(double amount) {
        if (amount >= getFinalPrice()) {
            this.isPaid = true;
            this.orderStatus = OrderStatus.PAID;
        }
    }

    @Override
    public boolean isPaid() {
        return isPaid;
    }

    @Override
    public void showInfo() {
        System.out.println("ID: " + get_id() +
                " | Название: " + String.format("%-12s", get_Title()) +
                " | Цена: " + String.format("%-8.2f", get_Price()) +
                " | Оплачено: " + (isPaid ? "Да" : "Нет") +
                " | Статус: " + orderStatus.getDescription());
    }

    public static void showAllProducts() {
        System.out.println("\nТекущий список товаров:");
        for (int i = 0; i < productList.size(); i++) {
            System.out.print("[" + i + "] ");
            productList.get(i).showInfo();
        }
    }
}