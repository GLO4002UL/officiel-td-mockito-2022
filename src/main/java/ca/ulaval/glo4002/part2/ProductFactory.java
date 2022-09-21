package ca.ulaval.glo4002.part2;

public class ProductFactory {
    public Product create(String name, int quantity) {
        return new Product(name, quantity);
    }
}
