import java.util.Objects;

public abstract class Category implements Comparable<Category> {
    private static int idCon = 0;
    private final int id;
    private final String title;
    private final Double price;
    private final String description;

    public Category(String title, Double price, String description) {
        idCon++;
        this.id = idCon;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    @Override
    public int compareTo(Category other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    public abstract void showInfo();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(title, category.title) &&
                Objects.equals(price, category.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price);
    }

    public final int get_id() { return id; }
    public final String get_Title() { return title; }
    public final Double get_Price() { return price; }
    public final String get_Description() { return description; }
}

class CategoryManager {
    private static CategoryManager instance;
    private int totalCategories;
    private int totalSubCategories;

    private CategoryManager() {
        this.totalCategories = 0;
        this.totalSubCategories = 0;
    }

    public static CategoryManager getInstance() {
        if (instance == null) {
            instance = new CategoryManager();
        }
        return instance;
    }

    public void incrementCategory() {
        totalCategories++;
    }

    public void incrementSubCategory() {
        totalSubCategories++;
    }

    public void displayStats() {
        System.out.println("\nСтатистика категорий (Singleton):");
        System.out.println("Всего категорий: " + totalCategories);
        System.out.println("Всего подкатегорий: " + totalSubCategories);
    }
}