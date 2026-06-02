interface ClientOutputStrategy {
    void output(String clientName, ClientStatus status);
}

class VipClientOutputStrategy implements ClientOutputStrategy {
    @Override
    public void output(String clientName, ClientStatus status) {
        System.out.println(clientName + " является VIP клиентом со статусом: " +
                status.getTitle() + " (скидка: " + status.getDiscount() + "%)");
    }
}

class RegularClientOutputStrategy implements ClientOutputStrategy {
    @Override
    public void output(String clientName, ClientStatus status) {
        System.out.println(clientName + " является клиентом со статусом: " +
                status.getTitle() + " (скидка: " + status.getDiscount() + "%)");
    }
}

class NewClientOutputStrategy implements ClientOutputStrategy {
    @Override
    public void output(String clientName, ClientStatus status) {
        System.out.println(clientName + " является новым клиентом со статусом: " +
                status.getTitle() + " (скидка: " + status.getDiscount() + "%)");
    }
}