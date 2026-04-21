class MobileDevays extends Electronic {
    public MobileDevays(String title, Double price, String description) {
        super(title, price, description);
        Catalog.incrementSubCategory();
    }
}