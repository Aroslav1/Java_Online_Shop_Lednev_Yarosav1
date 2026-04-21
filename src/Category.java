import java.util.Objects;

public abstract class Category {
    private static int idCon = 0;
    private int id;
    private String title;
    private Double price;
    private String description;

    public Category(String title, Double price, String description) {
        idCon++;
        this.id = idCon;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public abstract void showInfo();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(title, category.title) &&
                Objects.equals(price, category.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price);
    }

    public int get_id() { return id; }
    public String get_Title() { return title; }
    public Double get_Price() { return price; }
    public String get_Description() { return description; }
}