import java.util.ArrayList;

public class Product {
    private ArrayList<Category> items = new ArrayList<>();

    public void addProduct(Category item) {
        items.add(item);
    }

    public void showAllProducts() {
        System.out.println("СПИСОК ТОВАРОВ В МАГАЗИНЕ");
        for (Category item : items) {
            item.showInfo();
        }
    }
}