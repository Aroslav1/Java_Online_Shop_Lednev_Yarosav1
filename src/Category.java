public class Category {
    private static int idCounter = 1;
    private static int categoryCount = 0;
    private static int subCategoryCount = 0;

    private int id;
    private String title;
    private String description;
    private double price;

    public Category(String title, String description, double price) {
        this.id = idCounter++;
        this.title = title;
        this.description = description;
        this.price = price;

        if (this.getClass() == Category.class) {
            categoryCount++;
        } else {
            subCategoryCount++;
        }
    }

    public void ADD_Category() {
        System.out.println("Категория " + title + " успешно создана.");
    }

    public void SHOP_Category() {
        System.out.println("Вы просматриваете товары в категории: " + title);
    }

    public static int getCategoryCount() { return categoryCount; }
    public static int getSubCategoryCount() { return subCategoryCount; }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
}