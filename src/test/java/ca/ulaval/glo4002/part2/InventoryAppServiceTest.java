package ca.ulaval.glo4002.part2;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class InventoryAppServiceTest {
    private static final String A_PRODUCT_NAME = "laptop";
    private static final int A_QUANTITY = 2;

    private ProductFactory factory;
    private ProductRepository repository;
    private InventoryAppService service;

    @BeforeEach
    void before() {
        factory = mock(ProductFactory.class);
        repository = mock(ProductRepository.class);
        service = new InventoryAppService(factory, repository);
    }

    @Test
    void whenSearchingProducts_thenSearchesInTheStorageByName() {
        // given: the repository will have to at least return an empty list to make this work.

        service.searchProducts(A_PRODUCT_NAME);

        // assert that the repository is called properly
    }

    @Test
    void givenManyProductsWithTheName_whenSearchingForProducts_thenOnlyReturnsTheOnesInStock() {
        // given: some products with quantity = 0, and some > 0

        List<Product> products = service.searchProducts(A_PRODUCT_NAME);

        // assert only contains products in stock
    }

    @Test
    void whenAddingProduct_thenCreatesItWithNameAndQuantity() {
        // given: will need to return a product from the factory here in order not to crash. Why?

        service.addProduct(A_PRODUCT_NAME, A_QUANTITY);

        // assert created (factory)
    }

    @Test
    void givenItemCanBeSold_whenAddingProduct_thenSavesIt() {
        // given: created product can be sold

        service.addProduct(A_PRODUCT_NAME, A_QUANTITY);

        // assert saved
    }

    @Test
    void givenAnItemThatCannotBeSold_whenAddingProduct_thenDoesNotSaveIt() {
        // given: a product that cannot be sold

        service.addProduct(A_PRODUCT_NAME, A_QUANTITY);

        // assert NOT saved
    }

    @Test
    void whenUpdatingAQuantity_thenSendsTheUpdateRequestToTheStorage() {
        int newQuantity = 3;

        service.updateQuantity(A_PRODUCT_NAME, newQuantity);

        // assert repo.updateQuantity was called with the right object.
    }

}
