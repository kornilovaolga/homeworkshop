import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ShopRepositoryTest {

    Product product1 = new Product(11, "хлеб", 40);
    Product product2 = new Product(222, "булка", 30);
    Product product3 = new Product(3, "картошка", 7);
    ShopRepository shopRepo = new ShopRepository();

    @BeforeEach
    public void Setup() {
        shopRepo.add(product1);
        shopRepo.add(product2);
        shopRepo.add(product3);
    }

    @Test
    public void shouldFindExceptionWhenPositiveID() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            shopRepo.removeByID(100);
        });
    }

    @Test
    public void shouldFindExceptionWhenNegativeID() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            shopRepo.removeByID(-11);
        });
    }

    @Test
    public void shouldFindExceptionAfterRemoveByID() {
        shopRepo.removeByID(222);
        Assertions.assertThrows(NotFoundException.class, () -> {
            shopRepo.removeByID(222);
        });
    }

    @Test
    public void shouldRemoveByIDTheFirst() {
        Product[] expected = {product2, product3};
        shopRepo.removeByID(11);
        Product[] actual = shopRepo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIDTheSecond() {
        Product[] expected = {product1, product3};
        shopRepo.removeByID(222);
        Product[] actual = shopRepo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }
}