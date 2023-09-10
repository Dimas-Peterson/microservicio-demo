package academy.digitallab.store.product;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.repository.ProductRepository;
import academy.digitallab.store.product.service.ProductService;
import academy.digitallab.store.product.service.ProductServiceImpl;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ProductServiceMockTest {
    
    @Mock   //Indica que van a ser datos mockeados
    private ProductRepository productRepository; //Interface extiende JpaRepository
    
    private ProductService productService; //Contiene declaracion metodos abstractos
    
    @BeforeEach //Indica que antes del test haga esto
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);
        Product computer = Product.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1L).build())
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .build();
        
        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(computer));
        Mockito.when(productRepository.save(computer)).thenReturn(computer);
        
         
    }
    
    @Test
    public void whenValidGetID_ThenReturnProduct(){
        Product found = productService.getProduct(1L);
        Assertions.assertThat(found.getName()).isEqualTo("computer");
    }
    
    @Test
    public void whenValidUpdateStrock_ThenReturnNewStock(){
        Product newStock = productService.updateStock(1L, Double.valueOf("8")); //valueOf es mas rapido que parseDouble al instanciar
        Assertions.assertThat(newStock.getStock()).isEqualTo(13);
    }
}
