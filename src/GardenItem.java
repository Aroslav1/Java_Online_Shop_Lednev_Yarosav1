class GardenItem extends Category {
    public GardenItem(String title, String description, double price) {
        super(title, description, price);
    }
    @Override
    public void showInfo() {
        System.out.println("[Сад] ID: " + getId() + " | " + getTitle() + " (" + getDescription() + ") | " + price + " руб.");
    }
}