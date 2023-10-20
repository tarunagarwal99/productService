package dev.tarun.productservice.thirdpartyclients.fakestore;

import dev.tarun.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String image;
    private String description;
}
