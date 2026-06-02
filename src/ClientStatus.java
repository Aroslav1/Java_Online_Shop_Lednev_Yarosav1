public enum ClientStatus {
    NEW("Новый клиент", 0),
    REGULAR("Постоянный клиент", 5),
    VIP("VIP клиент", 10),
    GOLD("Золотой клиент", 15);

    private final String title;
    private final int discount;

    ClientStatus(String title, int discount) {
        this.title = title;
        this.discount = discount;
    }

    public String getTitle() {
        return title;
    }

    public int getDiscount() {
        return discount;
    }

    public static ClientStatus getByDiscount(int discount) {
        for (ClientStatus status : values()) {
            if (status.discount == discount) {
                return status;
            }
        }
        return NEW;
    }
}