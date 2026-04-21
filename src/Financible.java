public interface Financible {
    double checkBalance();
    boolean hasEnoughMoney(double amount);
    String getFinancesStatus();
}