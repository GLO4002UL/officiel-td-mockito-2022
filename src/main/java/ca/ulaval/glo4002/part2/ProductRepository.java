package ca.ulaval.glo4002.part2;

import java.util.List;

public interface ProductRepository {
    List<Product> findByName(String sku);

    void insert(Product product);

    void updateQuantity(UpdateQuantityRequest updateQuantityRequest);
}
