import java.util.ArrayList;
import java.util.Comparator;

public class Product extends Category implements Payble {

    public static ArrayList<Product> productList = new ArrayList<>();
    private boolean isPaid = false;

    public Product(String title, Double price, String description) {
        super(title, price, description);
        productList.add(this);
    }

    public static Comparator<Product> PriceComparator = (p1, p2) ->
            Double.compare(p1.get_Price(), p2.get_Price());

    public static void safeCompare(Product p1, Product p2) {
        System.out.println("\n   Проверка безопасности сравнения   ");

        if (!p1.getClass().equals(p2.getClass())) {
            System.out.println("ОШИБКА: Несовместимые типы товаров!");
            System.out.println("Нельзя сравнить [" + p1.getClass().getSimpleName() +
                    "] и [" + p2.getClass().getSimpleName() + "]");
        } else {
            int result = p1.compareTo(p2);
            System.out.println("Сравнение товаров одного типа [" + p1.getClass().getSimpleName() + "]:");
            if (result == 0) {
                System.out.println("Результат: Товары идентичны по названию.");
            } else if (result < 0) {
                System.out.println("Результат: " + p1.get_Title() + " идет перед " + p2.get_Title());
            } else {
                System.out.println("Результат: " + p2.get_Title() + " идет перед " + p1.get_Title());
            }
        }
    }

    public static Product getProductByIndex(int index) {
        if (index >= 0 && index < productList.size()) {
            return productList.get(index);
        }
        return null;
    }

    @Override
    public double getFinalPrice() {
        return get_Price();
    }

    @Override
    public void pay(double amount) {
        if (amount >= getFinalPrice()) {
            this.isPaid = true;
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
                " | Оплачено: " + (isPaid ? "Да" : "Нет"));
    }

    public static void showAllProducts() {
        System.out.println("\nТекущий список товаров:");
        for (int i = 0; i < productList.size(); i++) {
            System.out.print("[" + i + "] ");
            productList.get(i).showInfo();
        }
    }
}