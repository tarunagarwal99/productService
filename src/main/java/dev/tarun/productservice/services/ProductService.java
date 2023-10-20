package dev.tarun.productservice.services;

import dev.tarun.productservice.dtos.GenericProductDto;
import dev.tarun.productservice.exceptions.NotFoundException;
import dev.tarun.productservice.models.Product;

import java.util.List;

public interface ProductService {
    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto getProductById(Long id) throws NotFoundException;
    List<GenericProductDto> getAllProduct();

    GenericProductDto deleteProductById(Long id);
    ;
    GenericProductDto updateProductById(Long id,GenericProductDto product);
    List<String> getAllCategories();
    List<GenericProductDto> getByCategory(String category);
}
