package ca.ulaval.glo4002.part2;

import java.util.List;

public class InventoryAppService {
    private final ProductFactory productFactory;
    private final ProductRepository productRepository;

    public InventoryAppService(ProductFactory productFactory, ProductRepository productRepository) {
        this.productFactory = productFactory;
        this.productRepository = productRepository;
    }

    public List<Product> searchProducts(String name) {
        List<Product> products = productRepository.findByName(name);

        return products.stream().filter(x -> x.quantity() > 0).toList();
    }

    public void addProduct(String name, int quantity) {
        Product product = productFactory.create(name, quantity);

        if (product.canBeSold()) {
            productRepository.insert(product);
        }
    }

    public void updateQuantity(String name, int quantity) {
        productRepository.updateQuantity(new UpdateQuantityRequest(name, quantity));
    }
}
