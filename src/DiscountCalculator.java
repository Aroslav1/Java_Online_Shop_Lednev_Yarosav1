interface DiscountCalculator {
    double calculateDiscount(ClientStatus clientStatus, double price);
}

class ClientDiscountCalculator implements DiscountCalculator {
    @Override
    public double calculateDiscount(ClientStatus clientStatus, double price) {
        return price * clientStatus.getDiscount() / 100;
    }
}