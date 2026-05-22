public class Catalog {
    private static Catalog instance;
    private int categoryCount;
    private int subCategoryCount;

    private Catalog() {
        this.categoryCount = 0;
        this.subCategoryCount = 0;
    }

    public static Catalog getInstance() {
        if (instance == null) {
            instance = new Catalog();
        }
        return instance;
    }

    public void incrementCategory() {
        categoryCount++;
    }

    public void incrementSubCategory() {
        subCategoryCount++;
    }

    public int getCategoryCount() {
        return categoryCount;
    }

    public int getSubCategoryCount() {
        return subCategoryCount;
    }

    public void printStats() {
        System.out.println("\nСтатистика каталога (Singleton):");
        System.out.println("Всего категорий: " + categoryCount);
        System.out.println("Всего подкатегорий (типов товаров): " + subCategoryCount);
    }

    public void reset() {
        categoryCount = 0;
        subCategoryCount = 0;
    }
}