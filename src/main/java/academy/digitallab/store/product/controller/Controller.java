package academy.digitallab.store.product.controller;

import academy.digitallab.store.product.entity.Product;
import academy.digitallab.store.product.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping (value = "/products")
public class Controller {

    @Autowired
    private ProductService productService;

    public ResponseEntity<List<Product>> listProduct(){
        List<Product> products = productService.listAllProduct();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    
}
