package tech.leonam.openmarket.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import tech.leonam.openmarket.model.dto.ErroDto;
import tech.leonam.openmarket.model.dto.ErroListDto;

@ControllerAdvice
@RestController
public class ErrorController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroDto> handleException(Exception e) {
        return ResponseEntity.badRequest().body(new ErroDto(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroListDto> argumentInvalid(MethodArgumentNotValidException e) {
        var bindingResult = e.getBindingResult();
        var errors = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();

        errors.forEach(System.out::println);

        return ResponseEntity.badRequest().body(new ErroListDto(HttpStatus.BAD_REQUEST, errors));
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErroDto> tokenInvalid(JWTVerificationException e){
        var error = new ErroDto();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.FORBIDDEN);

        return ResponseEntity.badRequest().body(error);
    }

}
