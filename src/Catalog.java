public class Catalog {
    private final int id;
    private final String title;
    private final double price;

    private static int categoryCount = 0;
    private static int subCategoryCount = 0;

    public Catalog(int id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public static void incrementCategory() {
        categoryCount++;
    }

    public static void incrementSubCategory() {
        subCategoryCount++;
    }

    public static void printStats() {
        System.out.println("\nСтатистика каталога ");
        System.out.println("Всего категорий: " + categoryCount);
        System.out.println("Всего подкатегорий (типов товаров): " + subCategoryCount);
    }

    public int getID() { return id; }
    public String getTitle() { return title; }
    public double getPrice() { return price; }
}