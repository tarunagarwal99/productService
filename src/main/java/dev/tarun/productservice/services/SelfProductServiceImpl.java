package dev.tarun.productservice.services;

import dev.tarun.productservice.dtos.GenericProductDto;
import dev.tarun.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService {
    @Override
    public GenericProductDto getProductById(Long id){
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProduct() {
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(Long id,GenericProductDto product) {
        return null;
    }
    public List<String> getAllCategories(){
        return null;
    }

    @Override
    public List<GenericProductDto> getByCategory(String category) {
        return null;
    }
}
