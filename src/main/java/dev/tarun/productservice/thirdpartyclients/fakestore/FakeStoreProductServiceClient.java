package dev.tarun.productservice.thirdpartyclients.fakestore;

import dev.tarun.productservice.dtos.GenericProductDto;
import dev.tarun.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
// this class is the wrapper over fakestore api(FakeStoreProductsServiceClient is a wrapper over fakestore api means
//if fakestore has 10 different endpoint then fakestoreproductserviceclient has 10 diff methoda ,,nothing more than that
//in simple language fakestoreproductserviceclient is a way to represent fakestore api
@Service
public class FakeStoreProductServiceClient  {

    private RestTemplateBuilder restTemplateBuilder;

    //private  String getProductRequestUrl="https://fakestoreapi.com/products/{id}";

    //private String createProductRequestUrl="https://fakestoreapi.com/products";

    //private String getAllProductRequestUrl="https://fakestoreapi.com/products";


   // private  String deleteProductRequestUrl="https://fakestoreapi.com/products/{id}";

   // private  String specificProductRequestUrl="https://fakestoreapi.com/products/{id}";

    private  String allCategoriesUrl ="https://fakestoreapi.com/products/categories";

    private  String specificCategoryUrl ="https://fakestoreapi.com/products/category/jewelerry";

    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;
    @Value("${fakestore.api.path.product}")
    private String fakeStoreProductApiPath;

    private String productRequestBaseUrl;
    private  String specificProductRequestUrl;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder
            ,@Value("${fakestore.api.url}") String fakeStoreApiUrl
            ,@Value("${fakestore.api.path.product}") String fakeStoreProductApiPath){
        this.restTemplateBuilder=restTemplateBuilder;
        this.productRequestBaseUrl=fakeStoreApiUrl+fakeStoreProductApiPath;
        this.specificProductRequestUrl=fakeStoreApiUrl+fakeStoreProductApiPath+"/{id}";
    }

//    private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto){
//        GenericProductDto product=new GenericProductDto();
//        product.setId(fakeStoreProductDto.getId());
//        product.setImage(fakeStoreProductDto.getImage());
//        product.setDescription(fakeStoreProductDto.getDescription());
//        product.setTitle(fakeStoreProductDto.getTitle());
//        product.setPrice(fakeStoreProductDto.getPrice());
//        product.setCategory(fakeStoreProductDto.getCategory());
//
//        return product;
//    }
    public FakeStoreProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response=restTemplate.postForEntity(productRequestBaseUrl,product
                , FakeStoreProductDto.class);
        return response.getBody();
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response=restTemplate.getForEntity(specificCategoryUrl,FakeStoreProductDto.class,id);

        FakeStoreProductDto fakeStoreProductDto=response.getBody();

        //In the service we had the business logic so at there  we had real exception,
        // at controller end we had exception like "404" not found
        //so se can handle it by using exception handling
        if(fakeStoreProductDto==null){
            throw new NotFoundException("product with id: "+ id +" doesn't exixt");
        }
        return fakeStoreProductDto;
    }


    public List<FakeStoreProductDto> getAllProduct() {
        RestTemplate restTemplate=restTemplateBuilder.build();
        //this will not work becoz at runtime type of arratlist is earsed
        // so compiler don't even know this arraylist is of which type
        //so the solution for this is to make array of your object type
//       // ResponseEntity<List<FakeStoreProductDto>> response=restTemplate.getForEntity(getAllProductRequestUrl,
//                List<FakeStoreProductDto>.class);
        ResponseEntity<FakeStoreProductDto[]> response=restTemplate.getForEntity(productRequestBaseUrl,
                FakeStoreProductDto[].class);


        return Arrays.stream(response.getBody()).toList();
    }


    //here fakestore delete api want to return every information after deletion,
    // so we have to wirte the code for delete by self

    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate=restTemplateBuilder.build();

        RequestCallback requestCallback=restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor=
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(specificCategoryUrl, HttpMethod.DELETE,
                requestCallback,responseExtractor,id);

//        FakeStoreProductDto fakeStoreProductDto=response.getBody();

        return response.getBody();

    }

    public FakeStoreProductDto updateProductById(Long id,GenericProductDto product){
        RestTemplate restTemplate=restTemplateBuilder.build();

        RequestCallback requestCallback=restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor=restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> response=restTemplate.execute(specificProductRequestUrl,HttpMethod.PUT,requestCallback,responseExtractor,id,product);
        return response.getBody();
    }
    public List<String> getAllCategories(){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<List> response=restTemplate.getForEntity(allCategoriesUrl,List.class);
        return response.getBody().stream().toList();
    }

    public List<FakeStoreProductDto> getByCategory(String category){
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response=restTemplate.getForEntity(specificCategoryUrl,FakeStoreProductDto[].class,category);

        return Arrays.stream(response.getBody()).toList();

    }
}
