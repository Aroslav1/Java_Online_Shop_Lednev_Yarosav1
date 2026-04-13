class MobileDevays extends Electronic {
    public MobileDevays(String title, String description, double price) {
        super(title, description, price);
    }
    @Override
    public void showInfo() {
        System.out.println("[Мобильные Девайсы] ID: " + getId() + " | " + getTitle() + " (" + getDescription() + ") | " + price + " руб.");
    }
}