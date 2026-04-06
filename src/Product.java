public abstract class Product {
    private static int idCounter = 1;
    private int id;
    private String title;
    private String description;
    private double price;

    public Product(String title, String description, double price) {
        this.id = idCounter++;
        this.title = title;
        this.description = description;
        this.price = price;
    }
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
}