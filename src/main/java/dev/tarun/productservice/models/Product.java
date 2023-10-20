package dev.tarun.productservice.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
//@AllArgsConstructor --this will basically helps us to write code in very efficient way
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    private Category category;
    private  double price;
}
