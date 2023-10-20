package dev.tarun.productservice.services;

import dev.tarun.productservice.thirdpartyclients.fakestore.FakeStoreProductDto;
import dev.tarun.productservice.dtos.GenericProductDto;
import dev.tarun.productservice.exceptions.NotFoundException;
import dev.tarun.productservice.thirdpartyclients.fakestore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient=fakeStoreProductServiceClient;
    }

    private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product=new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

        return product;
    }
    public GenericProductDto createProduct(GenericProductDto product){

        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.createProduct(product));
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
      return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.getProductById(id));
    }

    @Override
    public List<GenericProductDto> getAllProduct() {
        List<GenericProductDto> genericProductDto=new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductServiceClient.getAllProduct()){
            genericProductDto.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
        }
      return genericProductDto;
    }


    //here fakestore delete api want to return every information after deletion,
    // so we have to wirte the code for delete by self
    @Override
    public GenericProductDto deleteProductById(Long id) {
       return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.deleteProductById(id));

    }

    @Override
    public GenericProductDto updateProductById(Long id,GenericProductDto product) {

        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.updateProductById(id,product));
    }

    @Override
    public List<String> getAllCategories() {
        return fakeStoreProductServiceClient.getAllCategories();
    }
    public List<GenericProductDto> getByCategory(String category){
        List<GenericProductDto> genericProductDtos=new ArrayList<>();
        List<FakeStoreProductDto> fakeStoreProductDtos=fakeStoreProductServiceClient.getByCategory(category);

        for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductDtos){
            genericProductDtos.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto));
        }
        return genericProductDtos;
    }

}
