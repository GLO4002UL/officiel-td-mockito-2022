package ca.ulaval.glo4002.part2;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

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
        givenAListOfItemsExists();

        service.searchProducts(A_PRODUCT_NAME);

        verify(repository).findByName(A_PRODUCT_NAME);
    }

    @Test
    void givenManyProductsWithTheName_whenSearchingForProducts_thenOnlyReturnsTheOnesInStock() {
        Product productInStock = givenProduct(2);
        Product productNotInStock = givenProduct(0);
        givenAListOfItemsExists(List.of(productInStock, productNotInStock));

        List<Product> products = service.searchProducts(A_PRODUCT_NAME);

        assertEquals(1, products.size());
        assertEquals(productInStock, products.get(0));
    }

    @Test
    void whenAddingProduct_thenCreatesItWithNameAndQuantity() {
        Product newProduct = givenProduct();
        willReturn(newProduct).given(factory).create(anyString(), anyInt());

        service.addProduct(A_PRODUCT_NAME, A_QUANTITY);

        verify(factory).create(A_PRODUCT_NAME, A_QUANTITY);
    }

    @Test
    void givenItemCanBeSold_whenAddingProduct_thenSavesIt() {
        Product newProduct = givenProduct();
        willReturn(true).given(newProduct).canBeSold();
        willReturn(newProduct).given(factory).create(anyString(), anyInt());

        service.addProduct(A_PRODUCT_NAME, A_QUANTITY);

        verify(repository).insert(newProduct);
    }

    @Test
    void givenAnItemThatCannotBeSold_whenAddingProduct_thenDoesNotSaveIt() {
        Product newProduct = givenProduct();
        willReturn(false).given(newProduct).canBeSold();
        willReturn(newProduct).given(factory).create(anyString(), anyInt());

        service.addProduct(A_PRODUCT_NAME, A_QUANTITY);

        verify(repository, never()).insert(any());
    }

    @Test
    void whenUpdatingAQuantity_thenSendsTheUpdateRequestToTheStorage() {
        int newQuantity = 3;

        service.updateQuantity(A_PRODUCT_NAME, newQuantity);

        ArgumentCaptor<UpdateQuantityRequest> captor = ArgumentCaptor.forClass(UpdateQuantityRequest.class);
        verify(repository).updateQuantity(captor.capture());
        assertEquals(A_PRODUCT_NAME, captor.getValue().name());
        assertEquals(newQuantity, captor.getValue().quantity());
    }

    private void givenAListOfItemsExists() {
        givenAListOfItemsExists(new LinkedList<>());
    }

    private void givenAListOfItemsExists(List<Product> products) {
        willReturn(products).given(repository).findByName(anyString());
    }

    private Product givenProduct() {
        return givenProduct(A_QUANTITY);
    }

    private Product givenProduct(int quantity) {
        Product product = mock(Product.class);
        willReturn(quantity).given(product).quantity();

        return product;
    }
}
