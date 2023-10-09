package tech.leonam.openmarket.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ErroDto {
    private HttpStatus status;
    private String message;

}
