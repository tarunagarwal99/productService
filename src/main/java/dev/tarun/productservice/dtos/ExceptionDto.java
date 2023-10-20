package dev.tarun.productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class ExceptionDto {
    private HttpStatus errorCode;
    private String message;
    //here we are creating constructor here

    public ExceptionDto(HttpStatus status,String message){
        this.errorCode=status;
        this.message=message;
    }
}
