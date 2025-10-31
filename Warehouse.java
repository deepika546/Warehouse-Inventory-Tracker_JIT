package src;

import java.util.*;

public class Warehouse {
    private Map<String, Product> inventory = new HashMap<>();
    private List<StockObserver> observers = new ArrayList<>();

    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }

    public void addProduct(Product product) {
        inventory.put(product.getId(), product);
        System.out.println("Product added: " + product.getName());
    }

    public void receiveShipment(String productId, int qty) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("Invalid Product ID");
            return;
        }
        product.increaseStock(qty);
        System.out.println("Received " + qty + " units of " + product.getName());
    }

    public void fulfillOrder(String productId, int qty) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("Invalid Product ID");
            return;
        }

        try {
            product.decreaseStock(qty);
            System.out.println("Fulfilled " + qty + " orders for " + product.getName());
            if (product.getQuantity() < product.getThreshold()) {
                notifyObservers(product);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void notifyObservers(Product product) {
        for (StockObserver observer : observers) {
            observer.onLowStock(product);
        }
    }
}
