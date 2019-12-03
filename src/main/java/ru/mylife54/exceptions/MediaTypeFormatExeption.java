package ru.mylife54.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class MediaTypeFormatExeption extends Exception {

    public MediaTypeFormatExeption(String message) {
        super(message);
    }
}
