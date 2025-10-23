package src;

public class Main {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        AlertService alertService = new AlertService();

        warehouse.addObserver(alertService);

        Product laptop = new Product("P101", "Laptop", 0, 5);
        warehouse.addProduct(laptop);

        warehouse.receiveShipment("P101", 10);
        warehouse.fulfillOrder("P101", 6);
        warehouse.fulfillOrder("P101", 2);
    }
}
