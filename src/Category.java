public class Category {
    private static int idCounter = 1;
    private int id;
    private String title;
    private String description;

    public Category(String title, String description) {
        this.id = idCounter++;
        this.title = title;
        this.description = description;
    }
    public int getId() { return id; }
    public String getTitle() { return title; }

    public String getDescription() {
        return description;
    }
}
