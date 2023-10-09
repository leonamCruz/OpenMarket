package tech.leonam.openmarket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.leonam.openmarket.exception.*;
import tech.leonam.openmarket.model.dto.ErroDto;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({CategoryEnumException.class,
            CpfExistsExecption.class,
            IdBrandNotFoundExpection.class,
            IdProductNotFoundExpection.class,
            IdSupplierNotFoundExpection.class,
            PasswordFormatInvalid.class,
            UserOrPasswordException.class})
    public ResponseEntity<ErroDto> customExceptions(Exception e) {
        System.out.println(e.getLocalizedMessage());

        return ResponseEntity.badRequest().body(new ErroDto(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

}
