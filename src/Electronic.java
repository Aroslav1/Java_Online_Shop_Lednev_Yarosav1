class Electronic extends Product {
    public Electronic(String title, Double price, String description) {
        super(title, price, description);
        Catalog.incrementSubCategory();
    }
}