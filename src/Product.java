import java.util.ArrayList;

public class Product extends Category implements Payble {
    public static ArrayList<Product> productList = new ArrayList<>();
    private boolean isPaid = false;

    public Product(String title, Double price, String description) {
        super(title, price, description);
        productList.add(this);
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
                " | Название: " + get_Title() +
                " | Цена: " + get_Price() +
                " | Оплачено: " + (isPaid ? "Да" : "Нет"));
    }

    public static void showAllProducts() {
        System.out.println("\nСписок товаров:");
        for (Product p : productList) p.showInfo();
    }
}