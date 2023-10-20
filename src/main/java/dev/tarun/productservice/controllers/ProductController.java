package dev.tarun.productservice.controllers;

import dev.tarun.productservice.dtos.ExceptionDto;
import dev.tarun.productservice.dtos.GenericProductDto;
import dev.tarun.productservice.exceptions.NotFoundException;
import dev.tarun.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    //spring has three method for dependency injection
//    1: field injection
//    2: using constructor(best practise to use)
//    3: using setter method
//    but the best recommended way for dependency injection is using constructor



    //@Autowired
    //1:field injection

    private ProductService productService;
    //2:constructor injection
    //when we have more than one service linked with one interface then spring is confused which we have to use
    //So we use qualifier keyword to directed to the same
    //we can use primary anotation to that class in the place of qualifier to use it as default
    public ProductController( ProductService productService){
        this.productService=productService;
    }
    //3:setter injection
    //@Autowired
    //    public void setProductService(ProductService productService) {
    //        this.productService = productService;
    //    }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
         return productService.getAllProduct();
    }
    //localhost:8080/products/123
    //(123 id the is here)
    @GetMapping("{id}")
    public GenericProductDto getProductById (@PathVariable("id") Long id ) throws NotFoundException{

        return productService.getProductById(id);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                productService.deleteProductById(id),
                HttpStatus.NOT_FOUND
        );
    }

    //when we have to request something then we require post request
    //until we see on response have body but now we see response will also have body
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto){
        return productService.createProduct(genericProductDto);

    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id,@RequestBody GenericProductDto genericProductDto) throws  NotFoundException{
       //return productService.updateProductById(UUID.fromString(id),genericProductDto);
        return productService.updateProductById(id,genericProductDto);
    }

    @GetMapping("/categories")
    public  List<String> getAllCategories(){
         return productService.getAllCategories();
    }
     @GetMapping("/category/{category}")
    public List<GenericProductDto> getByCategory(@PathVariable("category") String category){
        return productService.getByCategory(category);
    }

}
