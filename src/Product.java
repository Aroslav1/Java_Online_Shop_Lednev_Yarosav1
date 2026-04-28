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