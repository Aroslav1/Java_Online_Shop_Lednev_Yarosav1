class Electronic extends Category {
    public Electronic(String title, String description, double price) {
        super(title, description, price);
    }
    @Override
    public void showInfo() {
        System.out.println("[Электроника] ID: " + getId() + " | " + getTitle() + " (" + getDescription() + ") | Цена: " + price + " руб.");
    }
}