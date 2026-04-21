public interface Payble {
    double getFinalPrice();
    void pay(double amount);
    boolean isPaid();
}