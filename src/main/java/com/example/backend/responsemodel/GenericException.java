package com.example.backend.responsemodel;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class GenericException extends RuntimeException {
    private String message;

    public GenericException(String message) {
        super(message);
        this.message = message;
    }
}
