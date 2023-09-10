package academy.digitallab.store.product;

import academy.digitallab.store.product.entity.Category;
import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.repository.ProductRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {
    @Autowired
    private ProductRepository productRepository;
    //Cuando use H2 recordar que primero se carga el insert del test y despues el de la data.sql
    @Test
    public void whenFindByCategory_thenReturnListProduct(){
        //Category category01 = new Category(1L , "Dimas");
        
        Product product01 = Product.builder()
                .name("computer")
                .category(Category.builder().id(2L).build())  //2L categoria 2
                .description("")
                .stock(Double.parseDouble("10"))      //Convierte de String a primitivo
                .price(Double.valueOf("1240.99"))     //Convierte de String a un OBJETO numero
                .status("Created")
                .createAt(new Date()).build();
        productRepository.save(product01);

        List<Product> founds= productRepository.findByCategory(product01.getCategory());

        Assertions.assertThat(founds.size()).isEqualTo(2);  //Encontro 2 productos categoria 2
    }
}
